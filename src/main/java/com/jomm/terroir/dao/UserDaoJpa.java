package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.AbstractUser;

/**
 * This Class defines all CRUD operations involving a {@link AbstractUser}.
 * It implements {@link UserDao}, and it extends {@link GenericDao} using a {@link AbstractUser} parameter.
 * @author Maic
 */
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
