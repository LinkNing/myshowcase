package org.nifoo.showcase.website.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		//添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面 
		mv.setViewName("user/welcome");
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView listAllUser() {
		ModelAndView mv = new ModelAndView();

		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面 
		mv.setViewName("user/list");
		return mv;
	}
}
