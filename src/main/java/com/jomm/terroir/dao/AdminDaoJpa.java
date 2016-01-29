package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Admin;

/**
 * This Class defines all CRUD operations involving a {@link Admin}.
 * It implements {@link AdminDao}, and it extends {@link GenericDao} using a {@link Admin} parameter.
 * @author Maic
 */
@Stateless
public class AdminDaoJpa extends GenericDao<Admin> implements AdminDao {
	
}
