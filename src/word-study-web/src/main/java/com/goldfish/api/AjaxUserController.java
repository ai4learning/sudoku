package com.goldfish.api;

import com.goldfish.common.PageQuery;
import com.goldfish.domain.User;
import com.goldfish.service.LoginRecordService;
import com.goldfish.service.UserService;
import com.goldfish.vo.user.LoginVO;
import com.goldfish.vo.user.UserVO;
import com.goldfish.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wcf on 2018/6/21.
 */
@Controller
@RequestMapping("/api/Ajax")
public class AjaxUserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private LoginRecordService loginRecordService;

    @RequestMapping(value = "AjaxGetUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    UserVO doAjaxGetUserInfo() {
        UserVO userVO = new UserVO();
        User user = this.fillUserInfo(userVO);
        if(user == null) {
            return userVO;
        } else {
            userVO.setTotalLoginTimes((long)getLoginTimes(user));
            userVO.setSuccess(true);
            userVO.setMsg("成功");
            return userVO;
        }
    }

    @RequestMapping(value = "AjaxUpdatePassword", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    LoginVO doAjaxUpdatePassword(String txtOldPassword, String txtNewPassword, String txtComPassword) {
        User user = this.getUserInfo();
        LoginVO loginVO = new LoginVO();
        if(user.getPasswd().equals(txtOldPassword)) {
            if(txtComPassword.equals(txtNewPassword)) {
                user.setPasswd(txtNewPassword);
                userService.updateUser(user);
                loginVO.setSuccess(true);
                loginVO.setMsg("修改密码成功~");
            } else {
                loginVO.setSuccess(false);
                loginVO.setMsg("两次输入密码不一致~");
            }
        } else {
            loginVO.setSuccess(false);
            loginVO.setMsg("原密码错误~");
        }
        return loginVO;
    }

    //由于loginRecord数据库中state目前都是无效2，所以此处暂不用state判断
    private int getLoginTimes(User user)
    {
        PageQuery pageQuery = new PageQuery();
        pageQuery.addQueryParam("userId",user.getId().intValue());
        return loginRecordService.count(pageQuery);
    }
}
