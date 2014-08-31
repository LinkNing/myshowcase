package org.nifoo.showcase.website.dao;

import java.util.List;

import org.nifoo.showcase.website.entity.Resource;

public interface ResourceMapper {

	public Long add(Resource resource);

	public int update(Resource resource);

	public void delete(Long id);

	public Resource get(Long id);

	public List<Resource> list();

}