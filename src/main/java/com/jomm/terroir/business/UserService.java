package com.jomm.terroir.business;

import java.util.List;

/**
 * This Class is the Service relating to {@link UserEntity} and its children {@link AdminEntity}, 
 * {@link CustomerEntity} and {@link SellerEntity}.
 * It implements {@link UserServiceInterface} and defines all its business methods.
 * It relates to {@link UserDaoInterface} for general persistence operation on {@link UserEntity},
 * to {@link AdminDaoInterface} for persistence operation on {@link AdminEntity},
 * to {@link CustomerDaoInterface} for persistence operation on {@link CustomerEntity},
 * and to {@link SellerDaoInterface} for persistence operation on {@link SellerEntity}.
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
