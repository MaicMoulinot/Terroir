package com.jomm.terroir.business;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This Class is the Service relating to {@link Site}.
 * It implements {@link ServiceSite} and defines all its business methods.
 * It relates to {@link DaoSite} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceSiteImpl implements ServiceSite {
	
	@Inject
	private DaoSite siteDao;

	@Override
	public Site create(Site site) throws NullPointerException, InvalidEntityException {
		if (site == null) {
			throw new NullPointerException();
		} else if (site.getId() != null) {
			throw new InvalidEntityException();
		}
		return siteDao.update(site);
	}
	
	@Override
	public Site update(Site site) throws NullPointerException, InvalidEntityException {
		if (site == null) {
			throw new NullPointerException();
		} else if (site.getId() == null) {
			throw new InvalidEntityException();
		}
		return siteDao.update(site);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Site> getAllSites() {
		ArrayList<Site> result = new ArrayList<>();
		for (Site site : siteDao.findAll()) {
			result.add(site);
		}
		return result;
	}
	
	@Override
	public void delete(Site site) throws NullPointerException, InvalidEntityException {
		if (site == null) {
			throw new NullPointerException();
		} else if (site.getId() == null) {
			throw new InvalidEntityException();
		}
		siteDao.delete(site);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param siteDao the siteDao to set
	 */
	void setSiteDao(DaoSite siteDao) {
		this.siteDao = siteDao;
	}
}
