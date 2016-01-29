package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Enterprise;

/**
 * This Class defines all CRUD operations involving a {@link Enterprise}.
 * It implements {@link EnterpriseDao}, and it extends {@link GenericDao} using a {@link Enterprise} parameter.
 * @author Maic
 */
@Stateless
public class EnterpriseDaoJpa extends GenericDao<Enterprise> implements EnterpriseDao {
	
}
