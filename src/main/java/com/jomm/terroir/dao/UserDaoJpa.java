package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.AbstractUser;

@Stateless
public class UserDaoJpa extends GenericDao<AbstractUser> implements UserDao {

	@Override
	public boolean isExistingEmail(String email) {
		boolean result = false;
		for (AbstractUser user : findAll()) {
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
		for (AbstractUser user : findAll()) {
			if(user.getUserName().matches(userName)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
