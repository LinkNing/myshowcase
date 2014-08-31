package org.nifoo.showcase.website.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.website.entity.Role;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringDbunitTest
public class RoleMapperTest {

	@Resource
	private RoleMapper roleDao;

	@Test
	@DatabaseSetup(value = "RoleMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGet() {
		Role role = roleDao.get(1L);
		assertNotNull(role);
		assertEquals("admin", role.getRole());

		role = roleDao.get(0L);
		assertNull("it should get nothing.", role);
	}

	@Test
	@DatabaseSetup(value = "RoleMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDelete() {
		Role role = roleDao.get(1L);
		assertNotNull(role);

		roleDao.delete(1L);

		role = roleDao.get(1L);
		assertNull("it should not exist anymore.", role);
	}

	@Test
	@DatabaseSetup(value = "RoleMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testList() {
		List<Role> roles = roleDao.list();
		assertNotNull(roles);
		assertEquals(2, roles.size());
	}

	@Test
	@DatabaseSetup(value = "RoleMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testSave() {
		Role role = new Role("testRole", "testRole", "resources");
		roleDao.add(role);

		Long id = role.getId();
		assertNotNull(id);
		
		Role dbRole = roleDao.get(id);
		assertNotNull(dbRole);
		assertEquals("testRole", dbRole.getRole());
		assertEquals("testRole", dbRole.getDescription());
	}
}
