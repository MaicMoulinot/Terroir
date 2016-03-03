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
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

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
	
	@Inject
	private DaoUser daoUser;
	
	@Inject
	private DaoAdmin daoAdmin;
	
	@Inject
	private DaoCustomer daoCustomer;
	
	@Inject
	private DaoSeller daoSeller;

	@Override
	public AbstractUser create(AbstractUser user) throws ExceptionNullEntity, ExceptionInvalidId {
		if (user == null) {
			throw new ExceptionNullEntity();
		} else if (user.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		if (user instanceof Customer) {
			((Customer) user).setSignUpDate(ZonedDateTime.now());
		}
		return daoUser.create(user);
	}
	
	@Override
	public AbstractUser update(AbstractUser user) throws ExceptionNullEntity, ExceptionInvalidId {
		if (user == null) {
			throw new ExceptionNullEntity();
		} else if (user.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return daoUser.update(user);
	}
	
	@Override
	public void delete(AbstractUser user) throws ExceptionNullEntity, ExceptionInvalidId {
		if (user == null) {
			throw new ExceptionNullEntity();
		} else if (user.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		daoUser.delete(user);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Admin> getAllAdmins() {
		return daoAdmin.findAll();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Customer> getAllCustomers() {
		return daoCustomer.findAll();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Seller> getAllSellers() {
		return daoSeller.findAll();
	}
	
	@Override
	public boolean isExistingUserName(String userName) {
		if (userName == null) {
			throw new NullPointerException();
		}
		return daoUser.isExistingUserName(userName);
	}
	
	@Override
	public boolean isExistingEmail(String email) {
		if (email == null) {
			throw new NullPointerException();
		}
		return daoUser.isExistingEmail(email);
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoAdmin the daoAdmin to set.
	 */
	void setDaoAdmin(DaoAdmin daoAdmin) {
		this.daoAdmin = daoAdmin;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoCustomer the daoCustomer to set.
	 */
	void setDaoCustomer(DaoCustomer daoCustomer) {
		this.daoCustomer = daoCustomer;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoSeller the daoSeller to set.
	 */
	void setDaoSeller(DaoSeller daoSeller) {
		this.daoSeller = daoSeller;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoUser the daoUser to set.
	 */
	void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}
}