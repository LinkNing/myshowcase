package org.nifoo.showcase.db.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.nifoo.showcase.db.dao.UserMapper;
import org.nifoo.showcase.db.entity.User;

public class UserServiceImplByMapper implements UserService {
	//private final Logger log = LoggerFactory.getLogger(UserServiceImplByMapper.class);

	@Resource
	private UserMapper userDao;
	
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

	public void delete(Long id) {
		userDao.delete(id);
	}
	
	public void deleteAll() {
		// 
	}

	public User get(Long id) {
		return userDao.get(id);
	}

	public List<User> getAll() {
		return userDao.list();
	}

}