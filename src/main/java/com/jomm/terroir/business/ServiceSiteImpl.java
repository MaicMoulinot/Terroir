package com.jomm.terroir.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Class is the Service relating to {@link Site}.
 * It implements {@link ServiceSite} and defines all its business methods.
 * It relates to {@link DaoSite} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceSiteImpl implements ServiceSite {
	
	// The visibility of DAO is set to default/package to be accessible in tests.
	@Inject
	DaoSite siteDao;

	@Override
	public Site create(Site site) throws ExceptionNullEntity, ExceptionInvalidId {
		if (site == null) {
			throw new ExceptionNullEntity();
		} else if (site.getId() != null) {
			throw new ExceptionInvalidId(true);
		}
		return siteDao.create(site);
	}
	
	@Override
	public Site update(Site site) throws ExceptionNullEntity, ExceptionInvalidId {
		if (site == null) {
			throw new ExceptionNullEntity();
		} else if (site.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		return siteDao.update(site);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Site> getAllSites() {
		return siteDao.findAll();
	}
	
	@Override
	public void delete(Site site) throws ExceptionNullEntity, ExceptionInvalidId {
		if (site == null) {
			throw new ExceptionNullEntity();
		} else if (site.getId() == null) {
			throw new ExceptionInvalidId(false);
		}
		siteDao.delete(site);
	}
}
