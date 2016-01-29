package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.EnterpriseDaoInterface;

/**
 * This Class is the Service relating to {@link EnterpriseEntity}.
 * It implements {@link EnterpriseServiceInterface} and defines all its business methods.
 * It relates to {@link EnterpriseDaoInterface} for all persistence operations.
 * @author Maic
 */
@Stateless
public class EnterpriseService implements EnterpriseServiceInterface {
	
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
