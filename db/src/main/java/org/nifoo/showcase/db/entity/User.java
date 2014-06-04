package org.nifoo.showcase.db.entity;

public class User {

	private Long id;
	private Long organizationId;
	private String username;
	private String password;
	private String salt;
	private String roleIds;
	private Boolean locked;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(Long id, Long organizationId, String username, String password, String salt, String roleIds,
			Boolean locked) {
		super();
		this.id = id;
		this.organizationId = organizationId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.roleIds = roleIds;
		this.locked = locked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

}
