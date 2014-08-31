package org.nifoo.showcase.website.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.nifoo.showcase.website.entity.User;
import org.nifoo.showcase.website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users", produces = "text/html")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("user/welcome");
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Long id) {
		User user = userService.get(id);

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user/showuser");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listUsers(@RequestParam(value = "id", required = false) List<Long> ids) {
		List<User> users = Lists.newArrayList();

		if (ids == null || ids.size() == 0) {
			users = userService.getAll();
		} else {
			for (Long id : ids) {
				User user = userService.get(id);
				if (user != null)
					users.add(user);
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("user/userlist");
		return mv;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView edit() {
		User user = new User();

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user/edituser");
		return mv;
	}

	@RequestMapping(value = "/{id}/editor", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id) {

		User user = userService.get(id);

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user/edituser");
		return mv;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView save(@Valid @ModelAttribute("user") User user, Errors errors) {
		ModelAndView mv = new ModelAndView();
		if (errors.hasErrors()) {
			return new ModelAndView("user/edituser");
		}

		userService.save(user);

		// mv.addObject("user", user); // it is not necessary
		mv.setViewName("redirect:/users/" + user.getId()); // 保存后重定向
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

	@RequestMapping("/searcher")
	@ResponseBody
	public ModelAndView searchUser(@RequestParam(value = "username", required = false) String username) {
		List<User> users = Lists.newArrayList();

		if (StringUtils.isBlank(username)) {
			users = userService.getAll();
		} else {
			users = userService.searchByName(username);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("user/searchuser");
		return mv;
	}

}
