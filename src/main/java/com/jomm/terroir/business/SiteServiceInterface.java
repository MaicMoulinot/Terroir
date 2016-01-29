package com.jomm.terroir.business;

import java.util.ArrayList;

/**
 * This Interface describes all logic operations for {@link SiteEntity}.
 * @author Maic
 */
public interface SiteServiceInterface {
	
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
