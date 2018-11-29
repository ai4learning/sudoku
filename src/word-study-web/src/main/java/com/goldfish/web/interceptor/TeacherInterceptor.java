package com.goldfish.web.interceptor;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.domain.LoginRecord;
import com.goldfish.domain.User;
import com.goldfish.service.LoginRecordService;
import com.goldfish.service.UserService;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangjingtao
 * @date 2018/11/29 0029.
 */
public class TeacherInterceptor extends HandlerInterceptorAdapter {

    @Resource
    protected LoginRecordService loginRecordService;
    @Resource(name="userService")
    private UserService userService;

    private String loginUri = "/login/login";

    private final static Logger log = LoggerFactory
            .getLogger(TeacherInterceptor.class);

    @Override
    public final boolean preHandle(HttpServletRequest request,
                                   HttpServletResponse response, Object handler)
            throws IOException {
        LoginRecord loginRecord = getLoginRecord();
        if (loginRecord == null) {
            LogTypeEnum.DEFAULT.error("未获取到用户信息");
            response.sendRedirect(loginUri);
            return false;
        }
        Integer userId = loginRecord.getUserId();
        User userQuery = new User();
        userQuery.setId(Long.valueOf(String.valueOf(userId)));
        userQuery.setState(State.VALID.getState());
        User user = userService.getUnique(userQuery).getDefaultModel();
        if (user.getRoleType()==1){
            response.sendRedirect(loginUri);
            return false;
        }else{
            return true;
        }
    }

    protected LoginRecord getLoginRecord() {
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null) {
            return null;
        }
        try {
            // 获取用户登录信息
            return loginRecordService.getLoginRecordByToken(loginContext.getUserName(),loginContext.getToken());
        } catch (Exception e) {
            LogTypeEnum.DEFAULT.error(e, "获取登录信息失败");
            return null;
        }
    }
}
