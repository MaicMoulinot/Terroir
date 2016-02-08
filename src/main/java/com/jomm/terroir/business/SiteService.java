package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link Site}.
 * @author Maic
 */
public interface SiteService {
	
	/**
	 * Create a site.
	 * @param site the {@link Site} to create.
	 * @return the persisted site.
	 */
	public Site create(Site site);
	
	/**
	 * Update a site.
	 * @param site the {@link Site} to update.
	 * @return the updated site.
	 */
	public Site update(Site site);
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all {@link Site}.
	 */
	public ArrayList<Site> getAllSites();
	
	/**
	 * Delete a site.
	 * @param site the {@link Site} to delete.
	 */
	public void delete(Site site);
}
