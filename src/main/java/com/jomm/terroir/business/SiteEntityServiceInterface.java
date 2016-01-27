package com.jomm.terroir.business;

import java.util.ArrayList;


public interface SiteEntityServiceInterface {
	
	/**
	 * Save a site.
	 * @param site SiteEntity the site to save.
	 * @see com.jomm.terroir.business.SiteEntity
	 */
	public void persistSite(SiteEntity site);
	
	/**
	 * Fetch the list of all sites.
	 * @return a list of all sites.
	 * @see com.jomm.terroir.business.SiteEntity
	 */
	public ArrayList<SiteEntity> getAllSites();
	
	/**
	 * Delete a site.
	 * @param site SiteEntity.
	 * @see com.jomm.terroir.business.SiteEntity
	 */
	public void deleteSite(SiteEntity site);
}
