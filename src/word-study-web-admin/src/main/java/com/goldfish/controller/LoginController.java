package com.goldfish.controller;

import com.goldfish.common.CommonResult;
import com.goldfish.domain.ActivateCode;
import com.goldfish.web.interceptor.servlet.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private CookieUtils cookieUtils;

	@RequestMapping(value = {"", "login" }, method = RequestMethod.GET)
	public String index() {

		return "login/login";
	}

	@RequestMapping(value="doLogin",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String,Object> doLogin(ModelMap context) {
		CommonResult result = new CommonResult(true);
		return result.getReturnMap();
	}


	@RequestMapping(value = {"logout"}, method = {RequestMethod.GET,RequestMethod.POST})
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		cookieUtils.invalidate(request,response);
	}
	
}
