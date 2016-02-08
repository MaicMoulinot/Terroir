package com.jomm.terroir.business;

import java.util.List;

/**
 * This Interface describes all logic operations for {@link AbstractUser}.
 * @author Maic
 */
public interface UserService {
	
	/**
	 * Create an user.
	 * @param user the {@link AbstractUser} to create.
	 * @return the persisted AbstractUser.
	 */
	public AbstractUser create(AbstractUser user);
	
	/**
	 * Update an user.
	 * @param user the {@link AbstractUser} to update.
	 * @return the updated AbstractUser.
	 */
	public AbstractUser update(AbstractUser user);
	
	/**
	 * Fetch the list of all admins.
	 * @return a list of {@link Admin}.
	 */
	public List<Admin> getAllAdmins();
	
	/**
	 * Fetch the list of all customers.
	 * @return a list of {@link Customer}.
	 */
	public List<Customer> getAllCustomers();
	
	/**
	 * Fetch the list of all sellers.
	 * @return a list of {@link Seller}.
	 */
	public List<Seller> getAllSellers();

	/**
	 * Delete an user.
	 * @param user the {@link AbstractUser} to delete.
	 */
	public void delete(AbstractUser user);
	
	/**
	 * Check if the user name already exists.
	 * @param userName String the user name to check.
	 * @return true if the user name is already in use, false otherwise.
	 */
	public boolean isExistingUserName(String userName);
	
	/**
	 * Check if the email already exists.
	 * @param email String the email to test.
	 * @return true if email was found, false otherwise.
	 */
	public boolean isExistingEmail(String email);
}
