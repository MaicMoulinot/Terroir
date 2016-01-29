package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link UserEntity} and its children.
 * @author Maic
 */
public interface UserService {
	
	/**
	 * Create an user.
	 * @param user the {@link UserEntity} to create.
	 */
	public void create(UserEntity user);
	
	/**
	 * Update an user.
	 * @param user the {@link UserEntity} to update.
	 */
	public void update(UserEntity user);
	
	/**
	 * Fetch the list of all admins.
	 * @return a list of {@link AdminEntity}.
	 */
	public ArrayList<AdminEntity> getAllAdmins();
	
	/**
	 * Fetch the list of all customers.
	 * @return a list of {@link CustomerEntity}.
	 */
	public ArrayList<CustomerEntity> getAllCustomers();
	
	/**
	 * Fetch the list of all sellers.
	 * @return a list of {@link SellerEntity}.
	 */
	public ArrayList<SellerEntity> getAllSellers();
	
	/**
	 * Delete an user.
	 * @param user the {@link UserEntity} to delete.
	 */
	public void delete(UserEntity user);
}
