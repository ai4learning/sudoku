package com.goldfish.web.interceptor;

import com.goldfish.common.CommonResult;
import com.goldfish.constant.State;
import com.goldfish.constant.UserState;
import com.goldfish.dao.cache.local.LoginRecordContext;
import com.goldfish.domain.LoginRecord;
import com.goldfish.domain.User;
import com.goldfish.service.LoginRecordService;
import com.goldfish.service.UserService;
import com.goldfish.web.interceptor.servlet.CookieUtils;
import com.goldfish.web.interceptor.servlet.context.ExcludeReqstPath;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截
 * Created by hellosscat on 2018/5/6 0006.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final static Logger log = LoggerFactory
            .getLogger(LoginInterceptor.class);
    protected CookieUtils cookieUtils;
    private LoginRecordService loginRecordService;
    private UserService userService;
    /**
     * 判断session有效时间，单位：秒 1800 为 30 * 60 。30分钟
     */
    private int sessionTimeout = 1800;

    /**
     * 写入cookie的时机
     */
    private int rate = 2;
    private String doLoginUri = "/login/doLogin";
    private String loginUri = "/login/login";


    @Override
    public final boolean preHandle(HttpServletRequest request,
                                   HttpServletResponse response, Object handler)
            throws ServletException,
            IOException {
        String uri= request.getRequestURI();
        // 1.部分URI不用做拦截，比如登录页
        if(ExcludeReqstPath.isInclude(uri)){
            return true;
        }
        // 2.登录处理
        boolean isLogin = false;
        try {
            isLogin = doLogin(request, response);
        } catch (Exception e) {
            log.warn("do login error!", e);
        }
        if (isLogin) {
            return true;
        }
        log.debug("not login, redirect to login");
        response.sendRedirect(loginUri);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext != null && modelAndView != null) {
            modelAndView.addObject("userId", loginContext.getUserName());
        }
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex)
            throws Exception {
        LoginContext.remove();
    }

    private boolean doLogin(HttpServletRequest request,
                           HttpServletResponse response) {

        // 学生端换成trainingid和trainingcode
        String trainingId = cookieUtils.getCookieValue(request, LoginContext.TRAINING_ID);
        String trainingCode = cookieUtils.getCookieValue(request, LoginContext.TRAINING_CODE);

        // 1.为空，则说明未登录 或正在登录
        if(StringUtils.isEmpty(trainingId) || StringUtils.isEmpty(trainingCode)){
            log.debug("no login context info, trainingId={}, trainingCode={}", trainingId,trainingCode);
            // 判断是否是用户登录请求，若不是，则返回登录验证失败；
            String requestURI = request.getRequestURI();
            if (!doLoginUri.equals(requestURI)) {
                return false;
            }
            // 从请求参数中确认是否含有用户名、密码
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                log.debug("用户名密码为空");
                return false;
            }
            // 查询数据库判断用户是否存在
            User query = new User();
            query.setUserId(userName);
            query.setPasswd(password);
            query.setState(State.VALID.getState());
            CommonResult<User> result = userService.getUnique(query);
            User user = result.getDefaultModel();
            if (!result.isSuccess() || user == null) {

                return false;
            }
            // 用户存在，则添加登录记录，创建LoginContext
            createLoginContext(response, user);
            // 写入Cookie
            writeCookie(response);
            return true;
        }
        // 2.从请求中解析出LoginConext
        LoginContext loginContext = instanceLoginContext(request, trainingId, trainingCode);

        // 2.从LoginRecord判断请求是否合法
        LoginRecord loginRecord = null;
        try {
            loginRecord = loginRecordService.getLoginRecordByTraining(loginContext.getTrainingId(), loginContext.getTrainingCode());
        } catch (Exception e) {
            log.error("查询登录信息失败，trainingId={}, trainingCode={}", loginContext.getTrainingId(),loginContext.getTrainingCode());
            return false;
        }
        if (loginRecord == null) {
            log.info("系统中不存在该登录记录，trainingId={}, trainingCode={}", loginContext.getUserName(), loginContext.getToken());
            cookieUtils.invalidate(request, response);
            return false;
        }
        // 4.判断session是否超期
        long current = System.currentTimeMillis();
        long created = loginContext.getCreated();
        long expires = loginContext.getExpires();
        long timeout = expires == 0 ? sessionTimeout * 1000
                : expires - created;// 如果没有设置过期时间，则使用默认的
        if (current - created < timeout) { // 如果没有过期
            if ((current - created) * rate > timeout) {// 如果剩下的时间只有2/3，就需要重新派发cookie
                log.debug("session cookie rewrite!");
                // 写最后一次访问的cookie
                loginContext.setCreated(current);
                loginContext.setExpires(current + timeout);
                writeCookie(response);
            }
        } else {
            log.debug("session cookie is timeout");
            // 超时后，要清空
            cookieUtils.invalidate(request, response);
        }
        return true;
    }

    /**
     * 从请求参数中创建LoginContext
     * @param request
     * @param traningId
     * @param trainingCode
     */
    private LoginContext instanceLoginContext(HttpServletRequest request, String traningId, String trainingCode) {
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null) {
            loginContext = LoginContext.parse(LoginContext.TRAINING_ID, traningId);
            loginContext.setTrainingCode(LoginContext.parse(LoginContext.TRAINING_CODE, trainingCode).getTrainingCode());
            // 通过training_id和training_code获取用户信息
            LoginContext.setLoginContext(loginContext);
        }
        return loginContext;
    }

    /**
     * 创建LoginContext
     * @param response
     * @param user
     * @return
     */
    private LoginContext createLoginContext(HttpServletResponse response, User user) {
        // 插入登录记录
        LoginRecord loginRecord = null;
        loginRecord = loginRecordService.refreshLoginRecord(user);
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null) {
            // 创建 新的LoginContext
            loginContext = new LoginContext();
            loginContext.setPin(loginRecord.getUserName());
            loginContext.setUserName(loginRecord.getUserName());
            loginContext.setToken(loginRecord.getStudyToken());
            loginContext.setTrainingId(loginRecord.getWordTrainingId());
            loginContext.setTrainingCode(loginRecord.getWordTrainingCode());

            // 设置session过期时间
            long curr = System.currentTimeMillis();
            loginContext.setCreated(curr);
            long timeout = sessionTimeout * 1000;
            loginContext.setExpires(curr+timeout);
            LoginContext.setLoginContext(loginContext);
        }
        return loginContext;
    }

    /**
     * 写入Cookie
     * @param response
     */
    private void writeCookie(HttpServletResponse response) {
        LoginContext context = LoginContext.getLoginContext();

        cookieUtils.setCookie(response, LoginContext.TRAINING_ID,
                context.toCookieValue(LoginContext.TRAINING_ID));
        cookieUtils.setCookie(response, LoginContext.TRAINING_CODE,
                context.toCookieValue(LoginContext.TRAINING_CODE));



    }

    public void setCookieUtils(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }
    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public void setLoginRecordService(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    public void setLoginUri(String loginUri) {
        this.loginUri = loginUri;
    }

    public void setDoLoginUri(String doLoginUri) {
        this.doLoginUri = doLoginUri;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
