package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.EnterpriseEntity;

@Stateless
public class EnterpriseDaoJpa extends DaoJpa<Long, EnterpriseEntity> implements EnterpriseDaoInterface {
	
}
