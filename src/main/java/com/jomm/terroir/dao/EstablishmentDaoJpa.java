package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.EstablishmentEntity;

@Stateless
public class EstablishmentDaoJpa extends DaoJpa<Long, EstablishmentEntity> implements EstablishmentDaoInterface {
	
}
