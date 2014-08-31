package org.nifoo.showcase.website.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Resource {
	private Long id;
	private String name;
	private String type;
	private String url;
	private Long parentId;
	private String parentIds;
	private String permission;
	private Boolean available;

	public Resource() {
	}

	public Resource(String name, String type, String url, Long parentId, String permission) {
		this.name = name;
		this.type = type;
		this.url = url;
		this.parentId = parentId;
		this.permission = permission;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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

		Resource rhs = (Resource) obj;
		return new EqualsBuilder().append(parentIds, rhs.parentIds) //
				.append(name, rhs.name) //
				.append(type, rhs.type) //
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(parentIds).append(name).append(type).toHashCode();
	}

}
