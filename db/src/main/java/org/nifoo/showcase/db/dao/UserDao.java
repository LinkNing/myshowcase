package org.nifoo.showcase.db.dao;

import java.util.List;

import org.nifoo.showcase.db.entity.User;

public interface UserDao {

	public Long save(User user);
	
	public User update(User user);

	public User get(Long id);
	
	public User getByName(String username);

	public void delete(Long id);

	public List<User> list();

}