package com.jomm.terroir.business;

import java.util.List;

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
	public AbstractUser create(AbstractUser user) {
		userDao.create(user);
		return user;
	}
	
	@Override
	public AbstractUser update(AbstractUser user) {
		userDao.update(user);
		return user;
	}
	
	@Override
	public void delete(AbstractUser user) {
		userDao.delete(user);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Admin> getAllAdmins() {
		return adminDao.findAll();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Seller> getAllSellers() {
		return sellerDao.findAll();
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
	
	/**
	 * This method is used for Junit testing only.
	 * @param adminDao the adminDao to set
	 */
	void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	/**
	 * This method is used for Junit testing only.
	 * @param customerDao the customerDao to set
	 */
	void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	/**
	 * This method is used for Junit testing only.
	 * @param sellerDao the sellerDao to set
	 */
	void setSellerDao(SellerDao sellerDao) {
		this.sellerDao = sellerDao;
	}
}
