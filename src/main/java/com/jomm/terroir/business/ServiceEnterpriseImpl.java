package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.dao.DaoEnterprise;

/**
 * This Class is the Service relating to {@link Enterprise}.
 * It implements {@link ServiceEnterprise} and defines all its business methods.
 * It relates to {@link DaoEnterprise} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceEnterpriseImpl implements ServiceEnterprise {
	
	@Inject
	private DaoEnterprise enterpriseDao;

	@Override
	public Enterprise create(Enterprise enterprise) {
		enterpriseDao.create(enterprise);
		return enterprise;
	}
	
	@Override
	public Enterprise update(Enterprise enterprise) {
		enterpriseDao.update(enterprise);
		return enterprise;
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
	public void delete(Enterprise enterprise) {
		enterpriseDao.delete(enterprise);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param enterpriseDao the enterpriseDao to set
	 */
	void setEnterpriseDao(DaoEnterprise enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}
}
