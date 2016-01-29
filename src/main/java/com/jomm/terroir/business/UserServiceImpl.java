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
 * This Class is the Service relating to {@link UserEntity} and its children {@link AdminEntity}, 
 * {@link CustomerEntity} and {@link SellerEntity}.
 * It implements {@link UserService} and defines all its business methods.
 * It relates to {@link UserDao} for general persistence operation on {@link UserEntity},
 * to {@link AdminDao} for persistence operation on {@link AdminEntity},
 * to {@link CustomerDao} for persistence operation on {@link CustomerEntity},
 * and to {@link SellerDao} for persistence operation on {@link SellerEntity}.
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
	public void create(UserEntity user) {
		userDao.create(user);
	}
	
	@Override
	public void update(UserEntity user) {
		userDao.update(user);
	}
	
	@Override
	public void delete(UserEntity user) {
		userDao.delete(user);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<AdminEntity> getAllAdmins() {
		ArrayList<AdminEntity> result = new ArrayList<>();
		for (AdminEntity adminEntity : adminDao.findAll()) {
			result.add(adminEntity);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<CustomerEntity> getAllCustomers() {
		ArrayList<CustomerEntity> result = new ArrayList<>();
		for (CustomerEntity customerEntity : customerDao.findAll()) {
			result.add(customerEntity);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<SellerEntity> getAllSellers() {
		ArrayList<SellerEntity> result = new ArrayList<>();
		for (SellerEntity sellerEntity : sellerDao.findAll()) {
			result.add(sellerEntity);
		}
		return result;
	}

	/**
	 * This method is used for Junit testing only.
	 * @param userDao the userDao to set
	 */
	void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
