package org.nifoo.showcase.website.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Organization {
	private Long id;
	private String name;
	private Long parentId;
	private String parentIds;
	private Boolean available;

	public Organization() {
	}

	public Organization(String name, Long parentId) {
		super();
		this.name = name;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
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

		Organization rhs = (Organization) obj;
		return new EqualsBuilder().append(parentIds, rhs.parentIds) //
				.append(name, rhs.name) //
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(parentIds).hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}