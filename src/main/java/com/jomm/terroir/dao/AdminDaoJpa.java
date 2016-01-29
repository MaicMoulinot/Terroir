package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.Admin;

@Stateless
public class AdminDaoJpa extends GenericDao<Admin> implements AdminDao {
	
}
