package com.jomm.terroir.business;

import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ENTITY_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NOT_NULL;
import static com.jomm.terroir.util.exception.ExceptionService.TypeException.ID_NULL;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Designation}.
 * It implements {@link ServiceDesignation} and defines all its business methods.
 * It relates to {@link DaoDesignation} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceDesignationImpl implements ServiceDesignation {
	
	@Inject
	private DaoDesignation daoDesignation;

	@Override
	public Designation create(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (designation.getId() != null) {
			throw new ExceptionService(ID_NOT_NULL);
		}
		return daoDesignation.create(designation);
	}
	
	@Override
	public Designation update(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		return daoDesignation.update(designation);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Designation> getAllDesignations() {
		return daoDesignation.findAll();
	}
	
	@Override
	public void delete(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_NULL);
		}
		daoDesignation.delete(designation);
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoDesignation the daoDesignation to set.
	 */
	void setDaoDesignation(DaoDesignation daoDesignation) {
		this.daoDesignation = daoDesignation;
	}
}