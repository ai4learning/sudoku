package com.goldfish.api.admin;

import com.goldfish.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 管理主页
 * @author david
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@RequestMapping("/main")
	public String main(HttpServletRequest request, ModelMap context){
//		context.addAttribute("userId", LoginContext.getLoginContext().getUserName());
		return "admin/main/admin";
	}



	@RequestMapping(value = "/dialog",method = {RequestMethod.GET,RequestMethod.POST})
	public String dialog(HttpServletRequest request, ModelMap context){
		return "admin/main/dialog";
	}
	
	@RequestMapping(value="logout",method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpServletRequest request, HttpServletResponse response){
	//	cookieUtils.invalidate(request, response);
		return "redirect:/";
	}

}
