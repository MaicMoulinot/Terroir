package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link UserEntity} and its children.
 * @author Maic
 */
public interface UserServiceInterface {
	
	/**
	 * Save an user.
	 * @param user UserEntity the user to save.
	 * @see com.jomm.terroir.business.UserEntity
	 */
	public void persistUser(UserEntity user);
	
	/**
	 * Fetch the list of all admins.
	 * @return a list of all admins.
	 * @see com.jomm.terroir.business.AdminEntity
	 */
	public ArrayList<AdminEntity> getAllAdmins();
	
	/**
	 * Fetch the list of all customers.
	 * @return a list of all customers.
	 * @see com.jomm.terroir.business.CustomerEntity
	 */
	public ArrayList<CustomerEntity> getAllCustomers();
	
	/**
	 * Fetch the list of all sellers.
	 * @return a list of all sellers.
	 * @see com.jomm.terroir.business.SellerEntity
	 */
	public ArrayList<SellerEntity> getAllSellers();
	
	/**
	 * Delete an User.
	 * @param user UserEntity.
	 * @see com.jomm.terroir.business.UserEntity
	 */
	public void deleteUser(UserEntity user);
}
