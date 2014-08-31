package org.nifoo.showcase.website.web;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PhoneNumber {

	private String areaCode;
	private String phoneNumber;

	public PhoneNumber() {

	}

	public PhoneNumber(String areaCode, String phoneNumber) {
		super();
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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

		PhoneNumber rhs = (PhoneNumber) obj;
		return EqualsBuilder.reflectionEquals(this, rhs, false);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

}
