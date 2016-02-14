package com.jomm.terroir.dao;

import com.jomm.terroir.business.model.AbstractUser;

/**
 * This Interface is a DAO and manages CRUD operations involving a {@link AbstractUser}.
 * @author Maic
 */
public interface DaoUser extends Dao<AbstractUser> {
	
	/**
	 * Check if the email already exists.
	 * @param email String the email to test.
	 * @return true if email was found, false otherwise.
	 */
	public boolean isExistingEmail(String email);
	
	/**
	 * Check if the user name already exists.
	 * @param userName String the userName to test.
	 * @return true if user name was found, false otherwise.
	 */
	public boolean isExistingUserName(String userName);

}
