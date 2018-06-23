package com.goldfish.api;

import com.goldfish.domain.User;
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

    @RequestMapping(value = "AjaxGetUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    UserVO doAjaxGetUserInfo() {
        UserVO userVO = new UserVO();
        User user = this.fillUserInfo(userVO);
        if(user == null) {
            return userVO;
        } else {
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
}
