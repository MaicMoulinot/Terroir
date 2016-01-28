package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.SellerEntity;

@Stateless
public class SellerDaoJpa extends DaoJpa<Long, SellerEntity> implements SellerDaoInterface {
	
}
