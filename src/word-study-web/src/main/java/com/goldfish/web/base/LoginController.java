package com.goldfish.web.base;

import com.goldfish.common.CommonResult;
import com.goldfish.vo.BasicVO;
import com.goldfish.vo.user.LoginVO;
import com.goldfish.web.interceptor.servlet.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	private static String KEY = "73A1FF014F78EDC194C99EB37ACFF3DF";
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

	/**
	 * /login/callback/73A1FF014F78EDC194C99EB37ACFF3DF
	 * @param code
	 * @param request
	 * @param response
	 * @param context
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "callback/{key}", method = RequestMethod.GET)
	public String callback(String code, HttpServletRequest request, HttpServletResponse response, ModelMap context, @PathVariable String key) {

		if(StringUtils.isBlank(key) || !KEY.equals(key)){
			return "error";
		}
		CommonResult result = new CommonResult();
		result.addDefaultModel("code", code);
		toVm(result, context, request);
		return "login/sandbox";
	}


	@RequestMapping(value = {"logout"}, method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	LoginVO logout(HttpServletRequest request, HttpServletResponse response) {
		cookieUtils.invalidate(request, response);
		LoginVO loginVO = new LoginVO();
		loginVO.setCondition(-1);
		loginVO.setMsg("未登录");
		return loginVO;
	}
	
}
