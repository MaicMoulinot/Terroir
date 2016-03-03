package com.jomm.terroir.business;

import java.time.ZonedDateTime;
import java.util.List;

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
	
	@Inject
	private DaoEnterprise daoEnterprise;

	@Override
	public Enterprise create(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		enterprise.setSignUpDate(ZonedDateTime.now());
		return daoEnterprise.create(enterprise);
	}
	
	@Override
	public Enterprise update(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return daoEnterprise.update(enterprise);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Enterprise> getAllEnterprises() {
		return daoEnterprise.findAll();
	}
	
	@Override
	public void delete(Enterprise enterprise) throws ExceptionNullEntity, ExceptionInvalidId {
		if (enterprise == null) {
			throw new ExceptionNullEntity();
		} else if (enterprise.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		daoEnterprise.delete(enterprise);
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoEnterprise the daoEnterprise to set.
	 */
	void setDaoEnterprise(DaoEnterprise daoEnterprise) {
		this.daoEnterprise = daoEnterprise;
	}
}