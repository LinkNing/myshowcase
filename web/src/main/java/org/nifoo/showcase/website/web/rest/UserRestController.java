package org.nifoo.showcase.website.web.rest;

import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Lists;
import org.nifoo.showcase.website.entity.User;
import org.nifoo.showcase.website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users", produces = "application/json")
public class UserRestController {

	@Resource
	private UserService userService;

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

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User get(@PathVariable(value = "id") Long id) {
		User user = userService.get(id);
		return user;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
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

}