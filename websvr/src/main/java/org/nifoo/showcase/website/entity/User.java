package org.nifoo.showcase.website.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

	private Long id;
	
	private Long organizationId;
	
	@NotNull(message="{username.reqired}")
	private String username;
	
	@Size(min=4, message="{password.length}")
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		User rhs = (User) obj;
		return new EqualsBuilder().append(username, rhs.username).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(username).toHashCode();
	}

}
