package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;

import java.time.ZonedDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.dao.DaoEnterprise;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Enterprise}.
 * It implements {@link ServiceEnterprise} and defines all its business methods.
 * It relates to {@link DaoEnterprise} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceEnterpriseImpl implements ServiceEnterprise {
	
	// Injected Fields //-----------------------------------------
	@Inject
	private DaoEnterprise daoEnterprise;
	
	// Methods //-------------------------------------------------
	@Override
	public Enterprise create(Enterprise enterprise) throws ExceptionService {
		if (enterprise == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (enterprise.getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		}
		enterprise.setRegistrationDate(ZonedDateTime.now());
		return daoEnterprise.create(enterprise);
	}
	
	@Override
	public Enterprise update(Enterprise enterprise) throws ExceptionService {
		if (enterprise == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (enterprise.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		return daoEnterprise.update(enterprise);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Enterprise> getAllEnterprises() {
		return daoEnterprise.findAll();
	}
	
	@Override
	public void delete(Enterprise enterprise) throws ExceptionService {
		if (enterprise == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (enterprise.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoEnterprise.delete(enterprise);
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoEnterprise the daoEnterprise to set.
	 */
	void setTestDaoEnterprise(DaoEnterprise daoEnterprise) {
		this.daoEnterprise = daoEnterprise;
	}
}