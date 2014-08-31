package org.nifoo.showcase.website.web.rest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.nifoo.showcase.website.entity.User;
import org.nifoo.showcase.website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User get(@PathVariable(value = "id") Long id) {
		User user = userService.get(id);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = {"", "/{id}"}, method = { RequestMethod.POST, RequestMethod.PUT })
	public User save(@RequestBody User user) {
		User u = user;
		userService.save(u);
		return u;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable(value = "id") Long id) {
		userService.delete(id);
	}

	@ResponseBody
	@RequestMapping
	public List<User> listUsers(@RequestParam(value = "id", required = false) List<Long> ids) {
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

		return users;
	}
	
	@RequestMapping(params="username")
	@ResponseBody
	public List<User> searchUser(@RequestParam("username") String username) {
		List<User> users = Lists.newArrayList();

		if (StringUtils.isBlank(username)) {
			users = userService.getAll();
		} else {
			users = userService.searchByName(username);
		}
		return users;
	}

}