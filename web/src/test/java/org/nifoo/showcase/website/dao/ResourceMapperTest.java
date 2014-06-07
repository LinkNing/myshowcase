package org.nifoo.showcase.website.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nifoo.showcase.website.entity.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringDbunitTest
public class ResourceMapperTest {

	@javax.annotation.Resource
	private ResourceMapper resourceMapper;

	@Test
	@DatabaseSetup(value = "ResourceMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGet() {
		Resource resource = resourceMapper.get(1L);
		assertNotNull(resource);
		assertEquals("资源", resource.getName());

		resource = resourceMapper.get(0L);
		assertNull("it should get nothing.", resource);
	}

	@Test
	@DatabaseSetup(value = "ResourceMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDelete() {
		Resource resource = resourceMapper.get(1L);
		assertNotNull(resource);

		resourceMapper.delete(1L);

		resource = resourceMapper.get(1L);
		assertNull("it should not exist anymore.", resource);
	}

	@Test
	@DatabaseSetup(value = "ResourceMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testList() {
		List<Resource> resources = resourceMapper.list();
		assertNotNull(resources);
		assertEquals(6, resources.size());
	}

	@Test
	@DatabaseSetup(value = "ResourceMapperTest.seed.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testSave() {
		Resource resource = new Resource("resource", "menu", "", 0L, "xx");
		resourceMapper.add(resource);

		Long id = resource.getId();
		assertNotNull(id);

		Resource dbResource = resourceMapper.get(id);
		assertNotNull(dbResource);
		assertEquals("resource", dbResource.getName());
	}
}
