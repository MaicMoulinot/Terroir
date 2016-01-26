package com.jomm.terroir.business;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.EnterpriseDaoInterface;

@Stateless
public class EnterpriseEntityService implements EnterpriseEntityServiceInterface {
	
	@Inject
	private EnterpriseDaoInterface enterpriseDao;

	@Override
	public void persistEnterprise(EnterpriseEntity enterprise) {
		// Call Service to persist
		enterpriseDao.persist(enterprise);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<EnterpriseEntity> getAllEnterprises() {
		ArrayList<EnterpriseEntity> result = new ArrayList<>();
		for (EnterpriseEntity enterprise : enterpriseDao.findAll()) {
			result.add(enterprise);
		}
		return result;
	}
	
	@Override
	public void deleteEnterprise(EnterpriseEntity enterprise) {
		// Call Service to remove
		enterpriseDao.remove(enterprise);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param enterpriseDao the enterpriseDao to set
	 */
	void setEnterpriseDao(EnterpriseDaoInterface enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}
}
