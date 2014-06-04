package org.nifoo.showcase.db.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.db.entity.User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/spring-mybatis.xml" })
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, //
		TransactionDbUnitTestExecutionListener.class })
@Transactional
public class UserMapperTest {

	@Resource
	private UserMapper userDao;

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGet() {
		User user = userDao.get(1L);
		assertNotNull(user);
		assertEquals("admin1", user.getUsername());
		assertEquals("123", user.getPassword());

		user = userDao.get(0L);
		assertNull("it should get nothing.", user);
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDelete() {
		User user = userDao.get(1L);
		assertNotNull(user);

		userDao.delete(1L);

		user = userDao.get(1L);
		assertNull("it should not exist anymore.", user);
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testList() {
		List<User> users = userDao.list();
		assertNotNull(users);
		assertEquals(4, users.size());
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testSave() {
		User user = new User("testAdmin", "testPassword");
		userDao.save(user);

		Long id = user.getId();

		assertNotNull(id);
		User dbUser = userDao.get(id);
		assertNotNull(dbUser);
		assertEquals("testAdmin", dbUser.getUsername());
		assertEquals("testPassword", dbUser.getPassword());
	}
}
