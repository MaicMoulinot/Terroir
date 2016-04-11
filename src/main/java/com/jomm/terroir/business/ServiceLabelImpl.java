package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.dao.DaoLabel;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Label}.
 * It implements {@link ServiceLabel} and defines all its business methods.
 * It relates to {@link DaoLabel} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceLabelImpl implements ServiceLabel {
	
	// Injected Fields //-----------------------------------------
	@Inject
	private DaoLabel daoLabel;
	
	// Methods //-------------------------------------------------
	@Override
	public Label create(Label label) throws ExceptionService {
		if (label == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (label.getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		}
		return daoLabel.create(label);
	}
	
	@Override
	public Label update(Label label) throws ExceptionService {
		if (label == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (label.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		return daoLabel.update(label);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Label> getAllLabels() {
		return daoLabel.findAll();
	}	
	
	@Override
	public Label getLabel(Long id) throws ExceptionService {
		if (id == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		return daoLabel.find(id);
	}
	
	@Override
	public void delete(Label label) throws ExceptionService {
		if (label == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (label.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoLabel.delete(label);
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoLabel the daoLabel to set.
	 */
	void setTestDaoLabel(DaoLabel daoLabel) {
		this.daoLabel = daoLabel;
	}
}