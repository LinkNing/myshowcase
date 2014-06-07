package org.nifoo.showcase.website.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.nifoo.showcase.website.entity.User;

public class UserData {

	public static User randomNewUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setUsername(RandomStringUtils.random(5));
		user.setPassword(RandomStringUtils.random(16));
		user.setOrganizationId(RandomUtils.nextLong(1, 100));

		return user;
	}
}
