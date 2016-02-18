package com.jomm.terroir.business;

import java.util.ArrayList;

import com.jomm.terroir.business.model.Site;

/**
 * This Interface describes all logic operations for {@link Site}.
 * @author Maic
 */
public interface ServiceSite {
	
	/**
	 * Create a site.
	 * @param site the {@link Site} to create.
	 * @return the persisted site.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is not null.
	 */
	public Site create(Site site) throws NullPointerException, IllegalStateException;
	
	/**
	 * Update a site.
	 * @param site the {@link Site} to update.
	 * @return the updated site.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is null.
	 */
	public Site update(Site site) throws NullPointerException, IllegalStateException;
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all {@link Site}.
	 */
	public ArrayList<Site> getAllSites();
	
	/**
	 * Delete a site.
	 * @param site the {@link Site} to delete.
	 * @throws NullPointerException if the entity is null.
	 * @throws IllegalStateException if the id is null.
	 */
	public void delete(Site site) throws NullPointerException, IllegalStateException;
}
