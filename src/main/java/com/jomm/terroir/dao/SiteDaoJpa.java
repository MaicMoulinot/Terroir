package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Site;

/**
 * This Class defines all CRUD operations involving a {@link Site}.
 * It implements {@link SiteDao}, and it extends {@link GenericDao} using a {@link Site} parameter.
 * @author Maic
 */
@Stateless
public class SiteDaoJpa extends GenericDao<Site> implements SiteDao {
	
}
