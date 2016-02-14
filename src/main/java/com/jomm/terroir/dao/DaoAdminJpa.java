package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Admin;

/**
 * This Class defines all CRUD operations involving a {@link Admin}.
 * It implements {@link DaoAdmin}, and it extends {@link DaoGenericJpa} using a {@link Admin} parameter.
 * @author Maic
 */
@Stateless
public class DaoAdminJpa extends DaoGenericJpa<Admin> implements DaoAdmin {
	
}
