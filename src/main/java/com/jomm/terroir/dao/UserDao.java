package com.jomm.terroir.dao;

import com.jomm.terroir.business.UserEntity;

public interface UserDaoInterface extends Dao<Long, UserEntity> {
	
	/**
	 * Check if the email already exists.
	 * @param email String the email to test.
	 * @return true if email was found, false otherwise.
	 */
	public boolean isExistingEmail(String email);
	
	/**
	 * Check if the username already exists.
	 * @param userName String the userName to test.
	 * @return true if username was found, false otherwise.
	 */
	public boolean isExistingUserName(String userName);

}
