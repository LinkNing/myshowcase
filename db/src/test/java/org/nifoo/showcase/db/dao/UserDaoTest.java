package org.nifoo.showcase.db.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DefaultOperationListener;
import org.dbunit.IDatabaseTester;
import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.db.entity.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * DbUnit的使用.
 * <p>
 * <a href='http://www.shenyanchao.cn/blog/2013/06/27/usage-dbunit/'>DbUnit使用入门</a><br>

 * <p>
 * DbUnit的核心部分:
 * <ul>
 * <li>IDatabaseConnection ：描述DbUnit数据库连接接口；</li>
 * <li>IDataSet：数据集操作接口；</li>
 * <li>DatabaseOperation：描述测试用例测试方法执行前与执行后所做操作的抽象类；</li>
 * <li>Assertion: 唯一的方法，assertEqual，断言两个数据集或数据表相同。</li>
 * </ul>
 * <p>
 * 
 * @author Nifoo Ning
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/spring-beans.xml" })
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback = true)
// 配置事务是使用哪个事务管理器和默认是否回滚,通常继承AbstractTransactionalJUnit4SpringContextTests后不需配置 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, //
		//DirtiesContextTestExecutionListener.class, //
		TransactionalTestExecutionListener.class })
@Transactional
public class UserDaoTest {

	@Resource
	private UserDao userDao;

	IDatabaseTester databaseTester;

	@Resource
	public void setDataSource(final DataSource dataSource) {

		/*
		 * 如果要使用 Spring 的事务管理，需要：
		 *   1.通过 DataSourceUtils.getConnection 方法获取连接
		 *   2.要覆盖掉 DefaultOperationListener 的 operationSetUpFinished 方法和 operationTearDownFinished 方法,这两个
		 *     会在 setUp 和 tearDown 后关掉 connection, 造成数据库连接异常。
		 */
		databaseTester = new DataSourceDatabaseTester(dataSource) {

			@Override
			public IDatabaseConnection getConnection() throws Exception {

				// 用 DataSourceUtils.getConnection() 取得同一上下文中的 connection.
				IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource),
						getSchema());

				// 设置正确的数据源类型，避免WARN
				conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
				return conn;
			}
		};

		databaseTester.setOperationListener(getOperationListener());
	}

	protected IOperationListener getOperationListener() {
		return new DefaultOperationListener() {
			public void operationSetUpFinished(IDatabaseConnection connection) {
				// logger.debug("operationSetUpFinished(connection={}) - start", connection);
				// closeConnection(connection);
			}

			public void operationTearDownFinished(IDatabaseConnection connection) {
				// logger.debug("operationTearDownFinished(connection={}) - start", connection);
				// closeConnection(connection);
			}
		};
	}

	@Before
	public void setUp() throws Exception {
		//		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet("dbunitdemo-seed.xml"));

		databaseTester.setDataSet(getDataSet("/data/dbunitdemo-seed.xml"));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();

		//databaseTester.getConnection().getConnection().commit();

		//ScriptUtils.executeSqlScript(connection, resource);
	}

	@After
	public void tearDown() throws Exception {
		databaseTester.setDataSet(getDataSet("/data/dbunitdemo-seed.xml"));
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onTearDown();
	}

	protected IDataSet getDataSet(String fileName) throws Exception {
		File file = new ClassPathResource(fileName).getFile();

		FlatXmlDataSetBuilder dsBuilder = new FlatXmlDataSetBuilder();
		return dsBuilder.build(file);
	}

	public void testMe() throws Exception {
		// TODO: Execute the tested code that modify the database here

		// Fetch database data after executing your code
		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("TABLE_NAME");
		//ITable actualJoinData = getConnection().createQueryTable("RESULT_NAME", "SELECT * FROM TABLE1, TABLE2 WHERE ...");

		// Load expected data from an XML dataset
		IDataSet expectedDataSet = getDataSet("expectedDataSet.xml");
		ITable expectedTable = expectedDataSet.getTable("TABLE_NAME");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
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

		/* 下面验证数据集,注意数据的顺序也影响结果的正确  */
		QueryDataSet actual = new QueryDataSet(databaseTester.getConnection());
		actual.addTable("user", "select id, username, password, salt from user order by id");

		IDataSet expected = getDataSet("/data/dbunitdemo-user001.xml");

		Assertion.assertEquals(expected, actual);
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
