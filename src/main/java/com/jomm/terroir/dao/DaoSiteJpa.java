package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Site;

/**
 * This Class defines all CRUD operations involving a {@link Site}.
 * It implements {@link DaoSite}, and it extends {@link DaoGenericJpa} using a {@link Site} parameter.
 * @author Maic
 */
@Stateless
public class DaoSiteJpa extends DaoGenericJpa<Site> implements DaoSite {
	
}
