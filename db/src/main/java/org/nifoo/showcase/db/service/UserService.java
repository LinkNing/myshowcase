package org.nifoo.showcase.db.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {

	@Resource
	private UserDao userDao;

	public User save(User user) {
		User u = null;
		user.setSalt(UUID.randomUUID().toString());
		
		if (user.getId() == null) {
			userDao.save(user);
			u = user;
		} else {
			u = userDao.update(user);
		}
		return u;
	}

	public User add(User user) {
		userDao.save(user);
		return user;
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public User get(Long id) {
		return userDao.get(id);
	}

	public List<User> getAll() {
		return userDao.list();
	}

}