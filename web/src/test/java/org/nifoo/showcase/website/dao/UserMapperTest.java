package org.nifoo.showcase.website.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.website.entity.User;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringDbunitTest
public class UserMapperTest {

	@Resource
	private UserMapper userDao;

	@Test
	@DatabaseSetup(value = "UserMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGet() {
		User user = userDao.get(1L);
		assertNotNull(user);
		assertEquals("admin1", user.getUsername());
		assertEquals("123", user.getPassword());

		user = userDao.get(0L);
		assertNull("it should get nothing.", user);
	}

	@Test
	@DatabaseSetup(value = "UserMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	@ExpectedDatabase(value = "UserMapperTest.testDelete.expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testDelete() {
		User user = userDao.get(1L);
		assertNotNull(user);

		userDao.delete(1L);

		user = userDao.get(1L);
		assertNull("it should not exist anymore.", user);
	}

	@Test
	@DatabaseSetup(value = "UserMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testList() {
		List<User> users = userDao.list();
		assertNotNull(users);
		assertEquals(4, users.size());
	}

	@Test
	@DatabaseSetup(value = "UserMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testSave() {
		User user = new User("testAdmin", "testPassword");
		userDao.add(user);

		Long id = user.getId();

		assertNotNull(id);
		User dbUser = userDao.get(id);
		assertNotNull(dbUser);
		assertEquals("testAdmin", dbUser.getUsername());
		assertEquals("testPassword", dbUser.getPassword());
	}
}
