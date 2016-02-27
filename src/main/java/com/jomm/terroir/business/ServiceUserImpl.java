package com.jomm.terroir.business;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Admin;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.dao.DaoAdmin;
import com.jomm.terroir.dao.DaoCustomer;
import com.jomm.terroir.dao.DaoSeller;
import com.jomm.terroir.dao.DaoUser;

/**
 * This Class is the Service relating to {@link AbstractUser} and its children {@link Admin}, 
 * {@link Customer} and {@link Seller}.
 * It implements {@link ServiceUser} and defines all its business methods.
 * It relates to {@link DaoUser} for general persistence operation on {@link AbstractUser},
 * to {@link DaoAdmin} for persistence operation on {@link Admin},
 * to {@link DaoCustomer} for persistence operation on {@link Customer},
 * and to {@link DaoSeller} for persistence operation on {@link Seller}.
 * @author Maic
 */
@Stateless
public class ServiceUserImpl implements ServiceUser {
	
	// The visibility of DAO is set to default/package to be accessible in tests.
	@Inject
	DaoUser userDao;
	
	@Inject
	DaoAdmin adminDao;
	
	@Inject
	DaoCustomer customerDao;
	
	@Inject
	DaoSeller sellerDao;

	@Override
	public AbstractUser create(AbstractUser user) throws NullPointerException, IllegalArgumentException {
		if (user == null) {
			throw new NullPointerException();
		} else if (user.getId() != null) {
			throw new IllegalArgumentException();
		}
		if (user instanceof Customer) {
			((Customer) user).setSignUpDate(ZonedDateTime.now());
		}
		return userDao.create(user);
	}
	
	@Override
	public AbstractUser update(AbstractUser user) throws NullPointerException, IllegalArgumentException {
		if (user == null) {
			throw new NullPointerException();
		} else if (user.getId() == null) {
			throw new IllegalArgumentException();
		}
		return userDao.update(user);
	}
	
	@Override
	public void delete(AbstractUser user) throws NullPointerException, IllegalArgumentException {
		if (user == null) {
			throw new NullPointerException();
		} else if (user.getId() == null) {
			throw new IllegalArgumentException();
		}
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
	public boolean isExistingUserName(String userName) throws NullPointerException {
		if (userName == null) {
			throw new NullPointerException();
		}
		return userDao.isExistingUserName(userName);
	}
	
	@Override
	public boolean isExistingEmail(String email) throws NullPointerException {
		if (email == null) {
			throw new NullPointerException();
		}
		return userDao.isExistingEmail(email);
	}
}