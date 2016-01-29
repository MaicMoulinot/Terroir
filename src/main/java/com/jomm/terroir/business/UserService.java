package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link AbstractUser} and its children.
 * @author Maic
 */
public interface UserService {
	
	/**
	 * Create an user.
	 * @param user the {@link AbstractUser} to create.
	 */
	public void create(AbstractUser user);
	
	/**
	 * Update an user.
	 * @param user the {@link AbstractUser} to update.
	 */
	public void update(AbstractUser user);
	
	/**
	 * Fetch the list of all admins.
	 * @return a list of {@link Admin}.
	 */
	public ArrayList<Admin> getAllAdmins();
	
	/**
	 * Fetch the list of all customers.
	 * @return a list of {@link Customer}.
	 */
	public ArrayList<Customer> getAllCustomers();
	
	/**
	 * Fetch the list of all sellers.
	 * @return a list of {@link Seller}.
	 */
	public ArrayList<Seller> getAllSellers();
	
	/**
	 * Delete an user.
	 * @param user the {@link AbstractUser} to delete.
	 */
	public void delete(AbstractUser user);
}
