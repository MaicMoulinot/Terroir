package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.SiteDaoInterface;

/**
 * This Class is the Service relating to {@link SiteEntity}.
 * It implements {@link SiteServiceInterface} and defines all its business methods.
 * It relates to {@link SiteDaoInterface} for all persistence operations.
 * @author Maic
 */
@Stateless
public class SiteService implements SiteServiceInterface {
	
	@Inject
	private SiteDaoInterface siteDao;

	@Override
	public void persistSite(SiteEntity site) {
		// Call Service to persist
		siteDao.persist(site);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<SiteEntity> getAllSites() {
		ArrayList<SiteEntity> result = new ArrayList<>();
		for (SiteEntity site : siteDao.findAll()) {
			result.add(site);
		}
		return result;
	}
	
	@Override
	public void deleteSite(SiteEntity site) {
		// Call Service to remove
		siteDao.remove(site);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param siteDao the siteDao to set
	 */
	void setSiteDao(SiteDaoInterface siteDao) {
		this.siteDao = siteDao;
	}
}
