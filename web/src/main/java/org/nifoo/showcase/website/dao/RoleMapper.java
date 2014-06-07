package org.nifoo.showcase.website.dao;

import java.util.List;

import org.nifoo.showcase.website.entity.Role;

public interface RoleMapper {

	public int add(Role role);

	public int update(Role role);

	public void delete(Long id);

	public Role get(Long id);

	public Role getByName(String role);

	public List<Role> list();

}