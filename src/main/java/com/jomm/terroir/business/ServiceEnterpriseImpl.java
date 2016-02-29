package com.jomm.terroir.business;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.dao.DaoEnterprise;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Class is the Service relating to {@link Enterprise}.
 * It implements {@link ServiceEnterprise} and defines all its business methods.
 * It relates to {@link DaoEnterprise} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceEnterpriseImpl implements ServiceEnterprise {
	
	// The visibility of DAO is set to default/package to be accessible in tests.
	@Inject
	DaoEnterprise enterpriseDao;

	@Override
	public Enterprise create(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		enterprise.setSignUpDate(ZonedDateTime.now());
		return enterpriseDao.create(enterprise);
	}
	
	@Override
	public Enterprise update(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return enterpriseDao.update(enterprise);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Enterprise> getAllEnterprises() {
		ArrayList<Enterprise> result = new ArrayList<>();
		for (Enterprise enterprise : enterpriseDao.findAll()) {
			result.add(enterprise);
		}
		return result;
	}
	
	@Override
	public void delete(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		enterpriseDao.delete(enterprise);
	}
}