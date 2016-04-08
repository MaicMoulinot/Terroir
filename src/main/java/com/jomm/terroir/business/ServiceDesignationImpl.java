package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_INVALID;	

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Designation}.
 * It implements {@link ServiceDesignation} and defines all its business methods.
 * It relates to {@link DaoDesignation} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceDesignationImpl implements ServiceDesignation {
	
	// Injected Fields //-----------------------------------------
	@Inject
	private DaoDesignation daoDesignation;

	// Methods //-------------------------------------------------
	@Override
	public Designation create(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
		}
		return daoDesignation.create(designation);
	}
	
	@Override
	public Designation update(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
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
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoDesignation.delete(designation);
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Determine is the designation's unit is valid, i.e. is either {@link Unit#KILOGRAM}, 
	 * {@link Unit#LITER} or {@link Unit#PIECE}.
	 * @param unit {@link Unit}.
	 * @return {@code true} if the unit is valid, {@code false} otherwise.
	 */
	private boolean isValidUnit(Unit unit) {
		return unit != null && (unit == Unit.KILOGRAM || unit == Unit.LITER || unit == Unit.PIECE);
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoDesignation the daoDesignation to set.
	 */
	void setTestDaoDesignation(DaoDesignation daoDesignation) {
		this.daoDesignation = daoDesignation;
	}
}