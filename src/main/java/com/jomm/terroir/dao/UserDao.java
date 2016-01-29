package com.jomm.terroir.dao;

import com.jomm.terroir.business.AbstractUser;

public interface UserDao extends Dao<AbstractUser> {
	
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
