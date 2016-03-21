package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Administrator;

/**
 * This Class defines all CRUD operations involving a {@link Administrator}.
 * It implements {@link DaoAdministrator}, and it extends {@link DaoGenericJpa} using a {@link Administrator} parameter.
 * @author Maic
 */
@Stateless
public class DaoAdministratorJpa extends DaoGenericJpa<Administrator> implements DaoAdministrator {
	
}
