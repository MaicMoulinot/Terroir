package com.jomm.terroir.business;

import java.util.ArrayList;


public interface UserEntityServiceInterface {
	
	/**
	 * Save an user.
	 * @param user UserEntity the user to save.
	 * @see com.jomm.terroir.business.UserEntity
	 */
	public void persistUser(UserEntity user);
	
	/**
	 * Fetch the list of all Users.
	 * @return a list of all users.
	 * @see com.jomm.terroir.business.UserEntity
	 */
	public ArrayList<UserEntity> getAllUsers();
	
	/**
	 * Delete an User.
	 * @param user UserEntity.
	 * @see com.jomm.terroir.business.UserEntity
	 */
	public void deleteUser(UserEntity user);
}
