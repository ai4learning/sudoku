package com.goldfish.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value="index",method={RequestMethod.GET})
	public String index(){
		return "test/index";
	}
	
	
	@RequestMapping(value="/hello/{name}",method={RequestMethod.GET})
	@ResponseBody
	public	String helloWorld(@PathVariable String name){
		return "Hello world!"+name;
	}
	
	@RequestMapping(value="/helloJson/{name}",method={RequestMethod.GET})
	@ResponseBody
	public	Map<String,String> helloJson(@PathVariable String name){
		Map<String,String> map=new HashMap<String, String>();
		map.put("greeting", "welcome");
		map.put("name", name);
		return map;
	}

}
