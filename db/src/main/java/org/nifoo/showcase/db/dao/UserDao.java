package org.nifoo.showcase.db.dao;

import java.util.List;

import org.nifoo.showcase.db.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao {

	public Long save(User user) throws Exception;

	public User get(Long id);

	public void delete(Long id) throws Exception;

	public List<User> list() throws Exception;

}