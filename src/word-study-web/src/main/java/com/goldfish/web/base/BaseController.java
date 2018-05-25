package com.goldfish.web.base;

import com.goldfish.common.CommonResult;
import com.goldfish.domain.LoginRecord;
import com.goldfish.manager.LoginRecordManager;
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


}
