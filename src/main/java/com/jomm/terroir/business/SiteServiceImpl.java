package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.SiteDao;

/**
 * This Class is the Service relating to {@link SiteEntity}.
 * It implements {@link SiteService} and defines all its business methods.
 * It relates to {@link SiteDao} for all persistence operations.
 * @author Maic
 */
@Stateless
public class SiteServiceImpl implements SiteService {
	
	@Inject
	private SiteDao siteDao;

	@Override
	public void create(SiteEntity site) {
		siteDao.create(site);
	}
	
	@Override
	public void update(SiteEntity site) {
		siteDao.update(site);
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
	public void delete(SiteEntity site) {
		siteDao.delete(site);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param siteDao the siteDao to set
	 */
	void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
}
