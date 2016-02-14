package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.AbstractUser;

/**
 * This Class defines all CRUD operations involving a {@link AbstractUser}.
 * It implements {@link DaoUser}, and it extends {@link DaoGenericJpa} using a {@link AbstractUser} parameter.
 * @author Maic
 */
@Stateless
public class DaoUserJpa extends DaoGenericJpa<AbstractUser> implements DaoUser {

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
