package org.nifoo.showcase.website.web;

import java.beans.PropertyEditorSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class PhoneNumberEditor extends PropertyEditorSupport {
	Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text)) {
			setValue(null);// 如果没值，设值为null
		}
		Matcher matcher = pattern.matcher(text);
		if (matcher.matches()) {
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setAreaCode(matcher.group(1));
			phoneNumber.setPhoneNumber(matcher.group(2));
			setValue(phoneNumber);
		} else {
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
		}
	}

	@Override
	public String getAsText() {
		PhoneNumber phoneNumber = ((PhoneNumber) getValue());
		return phoneNumber == null ? "" : phoneNumber.getAreaCode() + "-" + phoneNumber.getPhoneNumber();
	}
}
