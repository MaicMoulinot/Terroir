package com.jomm.terroir.business;

import java.util.List;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Interface describes all logic operations for {@link Site}.
 * @author Maic
 */
public interface ServiceSite {
	
	/**
	 * Create a site.
	 * @param site the {@link Site} to create.
	 * @return the persisted site.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Site create(Site site) throws ExceptionService;
	
	/**
	 * Update a site.
	 * @param site the {@link Site} to update.
	 * @return the updated site.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	Site update(Site site) throws ExceptionService;
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all {@link Site}.
	 */
	List<Site> getAllSites();
	
	/**
	 * Fetch the {@link Site} with the provided identifier, and 
	 * load its {@link com.jomm.terroir.business.model.Designation}s' list.
	 * @param id Long the unique identifier.
	 * @return the {@link Site}, or {@code null} if it was not found.
	 * @throws ExceptionService when the {@code id} is {@code null}.
	 */
	Site getSite(Long id) throws ExceptionService;
	
	/**
	 * Delete a site.
	 * @param site the {@link Site} to delete.
	 * @throws ExceptionService when the entity is not in a valid state.
	 */
	void delete(Site site) throws ExceptionService;
}