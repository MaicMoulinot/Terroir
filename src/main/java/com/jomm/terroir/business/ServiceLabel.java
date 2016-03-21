package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Label}.
 * @author Maic
 */
public interface ServiceLabel {
	
	/**
	 * Create an label.
	 * @param label the {@link Label} to create.
	 * @return the persisted label.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Label create(Label label) throws ExceptionService;
	
	/**
	 * Update an label.
	 * @param label the {@link Label} to update.
	 * @return the updated label.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Label update(Label label) throws ExceptionService;
	
	/**
	 * Fetch the list of all labels.
	 * @return a list of all {@link Label}.
	 */
	List<Label> getAllLabels();
	
	/**
	 * Delete an label.
	 * @param label the {@link Label} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Label label) throws ExceptionService;
}
