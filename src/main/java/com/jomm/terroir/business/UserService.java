package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.AdminDaoInterface;
import com.jomm.terroir.dao.CustomerDaoInterface;
import com.jomm.terroir.dao.SellerDaoInterface;
import com.jomm.terroir.dao.UserDaoInterface;

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
@Stateless
public class UserService implements UserServiceInterface {
	
	@Inject
	private UserDaoInterface userDao;
	
	@Inject
	private AdminDaoInterface adminDao;
	
	@Inject
	private CustomerDaoInterface customerDao;
	
	@Inject
	private SellerDaoInterface sellerDao;

	@Override
	public void persistUser(UserEntity user) {
		userDao.persist(user);
	}
	
	@Override
	public void deleteUser(UserEntity user) {
		userDao.remove(user);
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
	void setUserDao(UserDaoInterface userDao) {
		this.userDao = userDao;
	}
}
