package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Interface describes all logic operations for {@link Site}.
 * @author Maic
 */
public interface ServiceSite {
	
	/**
	 * Create a site.
	 * @param site the {@link Site} to create.
	 * @return the persisted site.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is not null.
	 */
	Site create(Site site) throws ExceptionNullEntity, ExceptionInvalidId;
	
	/**
	 * Update a site.
	 * @param site the {@link Site} to update.
	 * @return the updated site.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is null.
	 */
	Site update(Site site) throws ExceptionNullEntity, ExceptionInvalidId;
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all {@link Site}.
	 */
	ArrayList<Site> getAllSites();
	
	/**
	 * Delete a site.
	 * @param site the {@link Site} to delete.
	 * @throws ExceptionNullEntity if the entity is null.
	 * @throws ExceptionInvalidId if the id is null.
	 */
	void delete(Site site) throws ExceptionNullEntity, ExceptionInvalidId;
}
