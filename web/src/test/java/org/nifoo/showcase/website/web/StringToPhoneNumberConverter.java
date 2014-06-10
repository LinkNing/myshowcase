package org.nifoo.showcase.website.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToPhoneNumberConverter implements Converter<String, PhoneNumber> {
	Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

	@Override
	public PhoneNumber convert(String source) {
		if (StringUtils.isBlank(source)) {
			// ①如果source为空 返回null
			return null;
		}
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			// ②如果匹配 进行转换
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setAreaCode(matcher.group(1));
			phoneNumber.setPhoneNumber(matcher.group(2));
			return phoneNumber;
		} else {
			// ③如果不匹配 转换失败
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", source));
		}
	}
}
