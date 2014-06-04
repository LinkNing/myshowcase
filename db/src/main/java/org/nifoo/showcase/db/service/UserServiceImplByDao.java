package org.nifoo.showcase.db.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class UserServiceImplByDao implements UserService {
	private final Logger log = LoggerFactory.getLogger(UserServiceImplByDao.class);

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}

	@CachePut(value = "user", key = "#user.id")
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

	@CacheEvict(value = "user", key="#id")
	public void delete(Long id) {
		userDao.delete(id);
	}
	
	@CacheEvict(value = "user", allEntries = true)
	public void deleteAll() {
		// 暂时用来测试清缓存
	}

	@Cacheable(value = "user", key="#id") //如果有@CachePut操作，即使有@Cacheable也不会从缓存中读取；
	public User get(Long id) {
		log.debug("cache miss, invoke find by user.id, id:" + id);  
		return userDao.get(id);
	}

	@Cacheable(value = "user") //如果有@CachePut操作，即使有@Cacheable也不会从缓存中读取；
	public List<User> getAll() {
		return userDao.list();
	}

}