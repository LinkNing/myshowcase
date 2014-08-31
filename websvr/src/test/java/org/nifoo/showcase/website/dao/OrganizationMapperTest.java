package org.nifoo.showcase.website.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.website.entity.Organization;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringDbunitTest
public class OrganizationMapperTest {

	@Resource
	private OrganizationMapper orgnazitionMapper;

	@Test
	@DatabaseSetup(value = "OrganizationMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGet() {
		Organization organization = orgnazitionMapper.get(1L);
		assertNotNull(organization);
		assertEquals("总公司", organization.getName());

		organization = orgnazitionMapper.get(0L);
		assertNull("it should get nothing.", organization);
	}

	@Test
	@DatabaseSetup(value = "OrganizationMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDelete() {
		Organization organization = orgnazitionMapper.get(1L);
		assertNotNull(organization);

		orgnazitionMapper.delete(1L);

		organization = orgnazitionMapper.get(1L);
		assertNull("it should not exist anymore.", organization);
	}

	@Test
	@DatabaseSetup(value = "OrganizationMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testList() {
		List<Organization> organizations = orgnazitionMapper.list();
		assertNotNull(organizations);
		assertEquals(3, organizations.size());
	}

	@Test
	@DatabaseSetup(value = "OrganizationMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testSave() {
		Organization organization = new Organization("organization", 0L);
		orgnazitionMapper.add(organization);

		Long id = organization.getId();
		assertNotNull(id);
		
		Organization dbOrganization = orgnazitionMapper.get(id);
		assertNotNull(dbOrganization);
		assertEquals("organization", dbOrganization.getName());
	}
}
