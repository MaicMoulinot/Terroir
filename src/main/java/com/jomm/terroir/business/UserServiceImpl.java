package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.AdminDao;
import com.jomm.terroir.dao.CustomerDao;
import com.jomm.terroir.dao.SellerDao;
import com.jomm.terroir.dao.UserDao;

/**
 * This Class is the Service relating to {@link AbstractUser} and its children {@link Admin}, 
 * {@link Customer} and {@link Seller}.
 * It implements {@link UserService} and defines all its business methods.
 * It relates to {@link UserDao} for general persistence operation on {@link AbstractUser},
 * to {@link AdminDao} for persistence operation on {@link Admin},
 * to {@link CustomerDao} for persistence operation on {@link Customer},
 * and to {@link SellerDao} for persistence operation on {@link Seller}.
 * @author Maic
 */
@Stateless
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private AdminDao adminDao;
	
	@Inject
	private CustomerDao customerDao;
	
	@Inject
	private SellerDao sellerDao;

	@Override
	public void create(AbstractUser user) {
		userDao.create(user);
	}
	
	@Override
	public void update(AbstractUser user) {
		userDao.update(user);
	}
	
	@Override
	public void delete(AbstractUser user) {
		userDao.delete(user);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Admin> getAllAdmins() {
		ArrayList<Admin> result = new ArrayList<>();
		for (Admin admin : adminDao.findAll()) {
			result.add(admin);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> result = new ArrayList<>();
		for (Customer customer : customerDao.findAll()) {
			result.add(customer);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Seller> getAllSellers() {
		ArrayList<Seller> result = new ArrayList<>();
		for (Seller seller : sellerDao.findAll()) {
			result.add(seller);
		}
		return result;
	}
	
	@Override
	public boolean isExistingUserName(String userName) {
		return userDao.isExistingUserName(userName);
	}
	
	@Override
	public boolean isExistingEmail(String email) {
		return userDao.isExistingEmail(email);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param userDao the userDao to set
	 */
	void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}