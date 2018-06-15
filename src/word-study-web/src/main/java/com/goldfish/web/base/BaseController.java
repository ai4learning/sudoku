package com.goldfish.web.base;

import com.goldfish.common.CommonResult;
import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.State;
import com.goldfish.domain.LoginRecord;
import com.goldfish.domain.User;
import com.goldfish.manager.LoginRecordManager;
import com.goldfish.service.UserService;
import com.goldfish.web.interceptor.servlet.context.LoginContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyong on 2015/10/9.
 */
public class BaseController {

    @Resource
    private LoginRecordManager loginRecordManager;
    @Resource
    private UserService userService;

    public void toVm(CommonResult result, ModelMap context) {
        this.toVm(result, context, null);
    }


    public void toVm(CommonResult result, ModelMap context, HttpServletRequest request) {
        context.putAll(result.getReturnMap());
    }


    public int getPageSize(HttpServletRequest request, int defaultPageSize, int max) {
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = defaultPageSize;
        if (StringUtils.isNumeric(pageSizeStr)) {
            try {
                pageSize = Integer.valueOf(pageSizeStr);
            } catch (Exception e) {
            }
            //不能超过最大值
            pageSize = pageSize > max ? max : pageSize;
        }
        return pageSize;
    }

    protected LoginRecord getLoginRecord() {
        LoginContext loginContext = LoginContext.getLoginContext();
        if (loginContext == null || org.apache.commons.lang3.StringUtils.isEmpty(loginContext.getTrainingId()) ||
                org.apache.commons.lang3.StringUtils.isEmpty(loginContext.getTrainingCode())) {
            return null;
        }
        LoginRecord query = new LoginRecord();
        query.setWordTrainingId(loginContext.getTrainingId());
        query.setWordTrainingCode(loginContext.getTrainingCode());
        return loginRecordManager.getUnique(query);
    }

    protected User getUserInfo()
    {
        // 1.根据登录获取用户信息
        LoginRecord loginRecord = this.getLoginRecord();
        if (loginRecord == null) {
            LogTypeEnum.DEFAULT.error("未获取到用户信息");
            return null;
        }
        Integer userId = loginRecord.getUserId();
        User userQuery = new User();
        userQuery.setId(Long.valueOf(String.valueOf(userId)));
        userQuery.setState(State.VALID.getState());
        CommonResult<User> userResult = userService.getUnique(userQuery);
        if (userResult == null || !userResult.isSuccess()) {
            LogTypeEnum.DEFAULT.error("未获取到用户信息");
            return null;
        }
        return userResult.getDefaultModel();
    }
}
