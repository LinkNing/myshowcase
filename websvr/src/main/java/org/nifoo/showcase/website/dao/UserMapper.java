package org.nifoo.showcase.website.dao;

import java.util.List;

import org.nifoo.showcase.website.entity.User;

public interface UserMapper {

	/**
	 * 添加新的用户记录，返回插入DB的记录数.由于这里只插入一个用户，所以只会返回1.
	 */
	public int add(User user);
	
	public int update(User user);

	public User get(Long id);
	
	public User getByName(String username);

	public void delete(Long id);

	public List<User> list();
	
	public List<User> findByName(String username);

}