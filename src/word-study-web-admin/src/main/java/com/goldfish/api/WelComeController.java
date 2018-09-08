package com.goldfish.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("//")
public class WelComeController {

	@RequestMapping(value = {"", "index" }, method = RequestMethod.GET)
	public String index() {

		return "index/index";
	}
	
	@RequestMapping(value = {"logout"}, method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
}
