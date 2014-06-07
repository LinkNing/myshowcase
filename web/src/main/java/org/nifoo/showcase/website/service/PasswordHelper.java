package org.nifoo.showcase.website.service;

import java.util.UUID;

import org.nifoo.showcase.website.entity.User;

public class PasswordHelper {
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(User user) {
		user.setSalt(UUID.randomUUID().toString());
//		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user
//				.getCredentialsSalt()), hashIterations).toHex();
//		user.setPassword(newPassword);
	}
}