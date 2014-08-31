package org.nifoo.showcase.website.dao;

import java.util.List;

import org.nifoo.showcase.website.entity.Organization;

public interface OrganizationMapper {

	public Long add(Organization organization);

	public int update(Organization organization);

	public void delete(Long id);

	public Organization get(Long id);

	public List<Organization> list();

}