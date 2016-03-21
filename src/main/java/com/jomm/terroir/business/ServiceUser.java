package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Administrator;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link AbstractUser}.
 * @author Maic
 */
public interface ServiceUser {
	
	/**
	 * Create an user.
	 * If the user is a {@link Customer}, it calls {@link Customer#setRegistrationDate(java.time.ZonedDateTime)}.
	 * @param user the {@link AbstractUser} to create.
	 * @return the persisted AbstractUser.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	AbstractUser create(AbstractUser user) throws ExceptionService;
	
	/**
	 * Update an user.
	 * @param user the {@link AbstractUser} to update.
	 * @return the updated AbstractUser.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	AbstractUser update(AbstractUser user) throws ExceptionService;
	
	/**
	 * Fetch the list of all admins.
	 * @return a list of {@link Administrator}.
	 */
	List<Administrator> getAllAdmins();
	
	/**
	 * Fetch the list of all customers.
	 * @return a list of {@link Customer}.
	 */
	List<Customer> getAllCustomers();
	
	/**
	 * Fetch the list of all sellers.
	 * @return a list of {@link Seller}.
	 */
	List<Seller> getAllSellers();

	/**
	 * Delete an user.
	 * @param user the {@link AbstractUser} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(AbstractUser user) throws ExceptionService;
	
	/**
	 * Check if the user name already exists.
	 * @param userName String the user name to check.
	 * @return true if the user name is already in use, false otherwise.
	 * @throws NullPointerException if userName is null.
	 */
	boolean isExistingUserName(String userName);
	
	/**
	 * Check if the email already exists.
	 * @param email String the email to test.
	 * @return true if email was found, false otherwise.
	 * @throws NullPointerException if email is null.
	 */
	boolean isExistingEmail(String email);
}
