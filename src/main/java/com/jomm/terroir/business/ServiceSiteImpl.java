package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Site}.
 * It implements {@link ServiceSite} and defines all its business methods.
 * It relates to {@link DaoSite} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceSiteImpl implements ServiceSite {
	
	// Injected Fields //-----------------------------------------
	@Inject
	private DaoSite daoSite;

	// Methods //-------------------------------------------------
	@Override
	public Site create(Site site) throws ExceptionService {
		if (site == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (site.getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		}
		return daoSite.create(site);
	}
	
	@Override
	public Site update(Site site) throws ExceptionService {
		if (site == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (site.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		return daoSite.update(site);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Site> getAllSites() {
		return daoSite.findAll();
	}
	
	@Override
	public Site getSite(Long id) {
		Site site = daoSite.find(id);
		if (site != null) {
			site.getDesignations().size(); // Force the lazy loading
		}
		return site;
	}
	
	@Override
	public void delete(Site site) throws ExceptionService {
		if (site == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (site.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoSite.delete(site);
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoSite the daoSite to set.
	 */
	void setTestDaoSite(DaoSite daoSite) {
		this.daoSite = daoSite;
	}
}