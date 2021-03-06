package org.nifoo.showcase.db.cache;

import static org.mockito.Mockito.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.data.UserData;
import org.nifoo.showcase.db.entity.User;
import org.nifoo.showcase.db.service.UserService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.google.common.collect.Lists;

//@RunWith(MockitoJUnit44Runner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class })
@ContextConfiguration(classes = { CacheConfig.class, CacheBeansConfig.class })
@ActiveProfiles("test")
@DirtiesContext
public class UserServiceWithCacheTest {

	@InjectMocks
	@Resource
	private UserService userService;

	@Mock
	private UserDao userDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		userService.deleteAll(); // 清空缓存
	}

	@Test
	public final void testUserCachable() throws Exception {

		List<User> users = Lists.newArrayList();
		User user1 = UserData.randomNewUser(1L);
		users.add(user1);
		User user2 = UserData.randomNewUser(2L);
		users.add(user2);
		User user3 = UserData.randomNewUser(3L);

		when(userDao.list()).thenReturn(users);
		when(userDao.get(1L)).thenReturn(user1).thenThrow(IllegalStateException.class);
		when(userDao.get(2L)).thenReturn(user2);
		when(userDao.update(user3)).thenReturn(user3);

		userService.getAll(); // get from db
		userService.get(1L); // get from db
		userService.get(2L); // get from db

		userService.get(1L); // get from cache
		userService.get(2L); // get from cache

		userService.delete(1L); // delete the recode, clear the cache
		try {
			userService.get(1L); // get nothing, throw an Exception
			Assert.fail("the recode has been deleted, it should get nothing!");
		} catch (IllegalStateException e) {
			// nothing need to do
		}

		userService.save(user3);
		userService.get(3L); // get from cache
		userService.get(2L); // get from cache

		verify(userDao).list();
		verify(userDao, times(2)).get(1L);
		verify(userDao, times(1)).get(2L);
		verify(userDao, times(0)).get(3L);
	}

	/**
	 * Cache注解运行流程（同一方法上的注解）:
	 * 1、首先执行@CacheEvict（如果beforeInvocation=true且condition 通过），如果allEntries=true，则清空所有
	 * 2、接着收集@Cacheable（如果condition 通过，且key对应的数据不在缓存），放入cachePutRequests（也就是说如果cachePutRequests为空，则数据在缓存中）
	 * 3、如果cachePutRequests为空且没有@CachePut操作，那么将查找@Cacheable的缓存，否则result=缓存数据（也就是说只要当没有cache put请求时才会查找缓存）
	 * 4、如果没有找到缓存，那么调用实际的API，把结果放入result
	 * 5、如果有@CachePut操作(如果condition 通过)，那么放入cachePutRequests
	 * 6、执行cachePutRequests，将数据写入缓存（unless为空或者unless解析结果为false）；
	 * 7、执行@CacheEvict（如果beforeInvocation=false 且 condition 通过），如果allEntries=true，则清空所有
	 * 
	 * 流程中需要注意的就是2/3/4步：如果有@CachePut操作，即使有@Cacheable也不会从缓存中读取；问题很明显，如果要混合多个注解使用，不能组合使用@CachePut和@Cacheable；
	 * 
	 * @author Nifoo Ning
	 *
	 */
	public void myDoc1() {
		// 此方法只是用注释来记文档
	}

}
