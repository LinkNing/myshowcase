package org.nifoo.showcase.db.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.db.entity.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用 Spring JDBC 辅助测试.
 * 
 * @author Nifoo Ning
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/spring-beans.xml" })
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, //
		//DirtiesContextTestExecutionListener.class, //
		TransactionalTestExecutionListener.class, })
@Transactional
public class UserDaoWithSpringUtilsTest {

	@Resource
	private UserDao userDao;

	private DataSource dataSource;

	@Resource
	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Before
	public void setUp() throws Exception {
		DatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("/data/dbunitdemo-data.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}

	@After
	public void tearDown() throws Exception {
		//		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("/data/dbunitdemo-clear.sql"));
		ScriptUtils.executeSqlScript(DataSourceUtils.getConnection(dataSource), new ClassPathResource(
				"/data/dbunitdemo-clear.sql"));
	}

	protected IDataSet getDataSet(String fileName) throws Exception {
		File file = new ClassPathResource(fileName).getFile();

		FlatXmlDataSetBuilder dsBuilder = new FlatXmlDataSetBuilder();
		return dsBuilder.build(file);
	}

	@Test
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
	public void testList() throws Exception {
		List<User> users = userDao.list();
		assertNotNull(users);
		assertEquals(4, users.size());
	}

	@Test
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
