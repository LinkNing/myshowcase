package org.nifoo.showcase.website.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
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

	@RequestMapping("/list")
	public ModelAndView listAllUser() {
		List<User> users = userService.getAll();

		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("user/userlist");
		return mv;
	}

	@RequestMapping(value = { "/edit" }, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "id", required = false) Long id) {
		ModelAndView mv = new ModelAndView();

		if (id != null) {
			User user = userService.get(id);
			mv.addObject("user", user);
		}

		mv.setViewName("user/edituser");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("user") User user, Errors errors) {
		if (errors.hasErrors()) {
			return new ModelAndView("user/edituser");
		}
		
		userService.save(user);

		ModelAndView mv = new ModelAndView();
		// mv.addObject("user", user); // it is not necessary
		mv.setViewName("redirect:/user/" + user.getId()); // 保存后重定向
		return mv;
	}

	@RequestMapping(value = "/list.json")
	@ResponseBody
	public void getUserJson(@RequestParam(value = "id", required = false) List<Long> ids, OutputStream out) {
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

		// render to JSON
		renderToJson(users, out);
	}

	private void renderToJson(List<User> users, OutputStream out) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(out, users);
		} catch (IOException e) {
			throw new IllegalStateException("转换用户信息出错！", e);
		}
	}
}
