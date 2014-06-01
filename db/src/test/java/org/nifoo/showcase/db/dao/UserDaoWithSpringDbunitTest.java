package org.nifoo.showcase.db.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.db.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * spring-test-dbunit的使用,以注解的方式使得DbUnit.
 * <p>
 * <a href='http://rockingware.com/2014/01/spring-test-dbunit.html'>使用Spring Test DbUnit进行数据库集成测试</a>
 * </p>
 * 
 * <p>
 * 核心部分:
 * <li>@DatabaseSetup指定测试方法执行之前数据库的初始化状态。
 * <li>@ExpectedDatabase指定测试方法执行之后期望的数据库状态。assertionMode = DatabaseAssertionMode.NON_STRICT表示只有XXXTest.testXXX.expected.xml中指定的表和列才会被断言，未指定的表和列将会被忽视。
 * <li>@DatabaseTearDown用于将数据库恢复到测试执行方法之前的状态。上面的例子，@DatabaseTearDown(value = "XXXTest.testXXX.expected.xml", type = DatabaseOperation.DELETE)将根据XXXTest.testXXX.expected.xml中的主键，从数据库删除相应数据。
 * </p>
 * 
 * <p>
 * Spring Test DbUnit的执行过程如下：<br/>
 * <code>@DatabaseSetup -> Test Method执行 -> @ExpectedDatabase -> @DatabaseTearDown</code>
 * </p>
 * 
 * <p>
 * Spring Test框架本身提供的 TransactionalTestExecutionListener 和 @Transactional 增强后事务边界范围仅限于在测试方法
 * Test Method 内。 Spring Teset DbUnit提供了 TransactionDbUnitTestExecutionListener 会将事务边界扩大到
 * Spring Test DbUnit执行的整个过程。
 * </p>
 * @author Nifoo Ning
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback = true)
// 注册DbUnitTestExecutionListener后，Spring Test DbUnit提供的注解就可以被Spring Test处理
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, //
		DirtiesContextTestExecutionListener.class, //
		// DbUnitTestExecutionListener.class
		TransactionDbUnitTestExecutionListener.class })
@Transactional
public class UserDaoWithSpringDbunitTest {

	@Resource
	private UserDao userDao;

	@Test
	//@DatabaseSetup(value = "XXXTest.testXXX.setUp.xml")
	//@ExpectedDatabase(value = "XXXTest.testXXX.expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml", type= DatabaseOperation.CLEAN_INSERT)
	@DatabaseTearDown(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.DELETE_ALL)
	public void testGet() {
		User user = userDao.get(1L);
		assertNotNull(user);
		assertEquals("admin1", user.getUsername());
		assertEquals("123", user.getPassword());

		try {
			userDao.get(0L);
			fail("it should get nothing.");
		} catch (EmptyResultDataAccessException e) {
			// correct, nothing need to do.
		}
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml")
	//@ExpectedDatabase(value = "XXXTest.testXXX.expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@ExpectedDatabase(value = "/data/dbunitdemo-user001.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.DELETE_ALL)
	public void testDelete() throws Exception {
		User user = userDao.get(1L);
		assertNotNull(user);

		userDao.delete(1L);

		try {
			userDao.get(1L);
			fail("it should not exist anymore.");
		} catch (EmptyResultDataAccessException e) {
			// correct, nothing need to do.
		}
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml")
	@DatabaseTearDown(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.DELETE_ALL)
	public void testList() throws Exception {
		List<User> users = userDao.list();
		assertNotNull(users);
		assertEquals(4, users.size());
	}

	@Test
	@DatabaseSetup(value = "/data/dbunitdemo-seed.xml")
	@DatabaseTearDown(value = "/data/dbunitdemo-seed.xml", type = DatabaseOperation.DELETE_ALL)
	public void testSave() throws Exception {
		User user = new User();
		user.setPassword("testPassword");
		user.setUsername("testAdmin");
		Long id = userDao.save(user);

		assertNotNull(id);
		User dbUser = userDao.get(id);
		assertNotNull(dbUser);
		assertEquals("testAdmin", dbUser.getUsername());
		assertEquals("testPassword", dbUser.getPassword());
	}
}
