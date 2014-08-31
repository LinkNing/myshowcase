package org.nifoo.showcase.website.service;

import java.util.List;

import javax.annotation.Resource;

import org.nifoo.showcase.website.dao.UserMapper;
import org.nifoo.showcase.website.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;

	PasswordHelper passwordHelper = new PasswordHelper();

	public User save(User user) {
		User u = user;
		passwordHelper.encryptPassword(u);

		if (u.getId() == null) {
			userMapper.add(u);
			log.info("add a new user:{}[{}]", u.getUsername(), u.getId());
		} else {
			userMapper.update(u);
			log.info("chang infomation of user:{}[{}]", u.getUsername(), u.getId());
		}
		return u;
	}

	public void delete(Long id) {
		userMapper.delete(id);
		log.info("delete user:{}", id);
	}

	public User get(Long id) {
		return userMapper.get(id);
	}

	public List<User> getAll() {
		return userMapper.list();
	}

	public List<User> searchByName(String username) {
		return userMapper.findByName(username + "%");
	}

}