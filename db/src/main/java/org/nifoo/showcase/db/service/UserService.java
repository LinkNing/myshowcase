package org.nifoo.showcase.db.service;

import java.util.List;

import org.nifoo.showcase.db.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

	public User save(User user);

	public void delete(Long id);
	
	public void deleteAll();

	public User get(Long id);

	public List<User> getAll();

}