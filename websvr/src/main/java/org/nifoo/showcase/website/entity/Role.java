package org.nifoo.showcase.website.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Role {
	private Long id;
	private String role;
	private String description;
	private String resourceIds;
	private Boolean available;

	public Role() {
	}

	public Role(String role, String description, String resourceIds) {
		super();
		this.role = role;
		this.description = description;
		this.resourceIds = resourceIds;
		this.available = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
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

		Role rhs = (Role) obj;
		return new EqualsBuilder().append(role, rhs.role).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(role).toHashCode();
	}

}
