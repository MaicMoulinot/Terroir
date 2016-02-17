package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Admin;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This Interface describes all logic operations for {@link AbstractUser}.
 * @author Maic
 */
public interface ServiceUser {
	
	/**
	 * Create an user.
	 * @param user the {@link AbstractUser} to create.
	 * @return the persisted AbstractUser.
	 * @throws NullPointerException if the entity is null.
	 * @throws InvalidEntityException if the id is not null.
	 */
	public AbstractUser create(AbstractUser user) throws NullPointerException, InvalidEntityException;
	
	/**
	 * Update an user.
	 * @param user the {@link AbstractUser} to update.
	 * @return the updated AbstractUser.
	 * @throws NullPointerException if the entity is null.
	 * @throws InvalidEntityException if the id is null.
	 */
	public AbstractUser update(AbstractUser user) throws NullPointerException, InvalidEntityException;
	
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
	 * @throws NullPointerException if the entity is null.
	 */
	public void delete(AbstractUser user) throws NullPointerException;
	
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
