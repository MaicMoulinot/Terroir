package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.UserEntity;

@Stateless
public class UserDaoJpa extends DaoJpa<Long, UserEntity> implements UserDaoInterface {

	@Override
	public boolean isExistingEmail(String email) {
		boolean result = false;
		for (UserEntity user : findAll()) {
			if(user.getEmail().matches(email)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	@Override
	public boolean isExistingUserName(String userName) {
		boolean result = false;
		for (UserEntity user : findAll()) {
			if(user.getUserName().matches(userName)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
