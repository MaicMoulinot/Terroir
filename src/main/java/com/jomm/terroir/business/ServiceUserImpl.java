package com.jomm.terroir.business;

import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ENTITY_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NOT_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NULL;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Administrator;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.dao.DaoAdministrator;
import com.jomm.terroir.dao.DaoCustomer;
import com.jomm.terroir.dao.DaoSeller;
import com.jomm.terroir.dao.DaoUser;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link AbstractUser} and its children {@link Administrator}, 
 * {@link Customer} and {@link Seller}.
 * It implements {@link ServiceUser} and defines all its business methods.
 * It relates to {@link DaoUser} for general persistence operation on {@link AbstractUser},
 * to {@link DaoAdministrator} for persistence operation on {@link Administrator},
 * to {@link DaoCustomer} for persistence operation on {@link Customer},
 * and to {@link DaoSeller} for persistence operation on {@link Seller}.
 * @author Maic
 */
@Stateless
public class ServiceUserImpl implements ServiceUser {
	
	@Inject
	private DaoUser daoUser;
	
	@Inject
	private DaoAdministrator daoAdmin;
	
	@Inject
	private DaoCustomer daoCustomer;
	
	@Inject
	private DaoSeller daoSeller;

	@Override
	public AbstractUser create(AbstractUser user) throws ExceptionService {
		if (user == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (user.getId() != null) {
			throw new ExceptionService(ID_NOT_NULL);
		}
		if (user instanceof Customer) {
			((Customer) user).setRegistrationDate(ZonedDateTime.now());
		}
		return daoUser.create(user);
	}
	
	@Override
	public AbstractUser update(AbstractUser user) throws ExceptionService {
		if (user == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (user.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		return daoUser.update(user);
	}
	
	@Override
	public void delete(AbstractUser user) throws ExceptionService {
		if (user == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (user.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		daoUser.delete(user);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Administrator> getAllAdmins() {
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
	void setDaoAdmin(DaoAdministrator daoAdmin) {
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