package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link SiteEntity}.
 * @author Maic
 */
public interface SiteService {
	
	/**
	 * Create a site.
	 * @param site the {@link SiteEntity} to create.
	 */
	public void create(SiteEntity site);
	
	/**
	 * Update a site.
	 * @param site the {@link SiteEntity} to update.
	 */
	public void update(SiteEntity site);
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all {@link SiteEntity}.
	 */
	public ArrayList<SiteEntity> getAllSites();
	
	/**
	 * Delete a site.
	 * @param site the {@link SiteEntity} to delete.
	 */
	public void delete(SiteEntity site);
}
