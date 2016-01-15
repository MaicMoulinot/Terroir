package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.UserEntity;

@Stateless
public class UserDaoJpa extends DaoJpa<Long, UserEntity> implements UserDaoInterface {
	
}
