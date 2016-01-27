package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.SiteEntity;

@Stateless
public class SiteDaoJpa extends DaoJpa<Long, SiteEntity> implements SiteDaoInterface {
	
}
