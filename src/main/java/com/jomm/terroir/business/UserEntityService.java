package com.jomm.terroir.business;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.dao.UserDaoInterface;

@Stateless
public class UserEntityService implements UserEntityServiceInterface {
	
	@Inject
	private UserDaoInterface userDao;

	@Override
	public void persistUser(UserEntity user) {
		// Call Service to save
		userDao.persist(user);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<UserEntity> getAllUsers() {
		ArrayList<UserEntity> result = new ArrayList<>();
		for (UserEntity userEntity : userDao.findAll()) {
			result.add(userEntity);
		}
		return result;
	}
	
	@Override
	public void deleteUser(UserEntity user) {
		// Call Service to save
		userDao.remove(user);
	}

	/**
	 * This method is used for Junit testing only.
	 * @param userDao the userDao to set
	 */
	void setUserDao(UserDaoInterface userDao) {
		this.userDao = userDao;
	}
}
