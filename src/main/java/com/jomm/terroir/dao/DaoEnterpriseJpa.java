package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Enterprise;

/**
 * This Class defines all CRUD operations involving a {@link Enterprise}.
 * It implements {@link DaoEnterprise}, and it extends {@link DaoGenericJpa} using a {@link Enterprise} parameter.
 * @author Maic
 */
@Stateless
public class DaoEnterpriseJpa extends DaoGenericJpa<Enterprise> implements DaoEnterprise {
	
}
