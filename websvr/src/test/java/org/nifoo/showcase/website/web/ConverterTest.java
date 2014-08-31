package org.nifoo.showcase.website.web;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConverterTest {

	@Test
	public void testStringToPhoneNumberConvert() {
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new StringToPhoneNumberConverter());

		PhoneNumber phoneNumber = conversionService.convert("0756-2260272", PhoneNumber.class);
		assertThat(phoneNumber).isEqualTo(new PhoneNumber("0756", "2260272"));
	}

	@Test
	public void testOtherConvert() {
		DefaultConversionService conversionService = new DefaultConversionService();

		// "1"--->true（字符串“1”可以转换为布尔值true）
		assertThat(conversionService.convert("1", Boolean.class)).isTrue();

		// "1,2,3,4"--->List（转换完毕的集合大小为4）
		assertThat((List<Integer>) conversionService.convert("1,2,3,4", List.class)).hasSize(4);
	}

}