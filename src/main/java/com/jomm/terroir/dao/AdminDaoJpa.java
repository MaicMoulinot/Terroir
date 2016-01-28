package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.AdminEntity;

@Stateless
public class AdminDaoJpa extends DaoJpa<Long, AdminEntity> implements AdminDaoInterface {
	
}
