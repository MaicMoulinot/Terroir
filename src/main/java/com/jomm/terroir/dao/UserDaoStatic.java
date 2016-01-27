package com.jomm.terroir.dao;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import com.jomm.terroir.business.UserEntity;

@Stateless
@Alternative
public class UserDaoStatic extends DaoStatic <Long, UserEntity> implements UserDaoInterface {

	private static HashMap<Long, UserEntity> mapUsers;

	/** Constructor with no parameter. */
	public UserDaoStatic() {
		if (mapUsers == null) {
			mapUsers = new HashMap<Long, UserEntity>();
		}
	}

	@Override
	public void remove(UserEntity entity) {
		mapUsers.remove(entity);
	}

	@Override
	public UserEntity findById(Long id) {
		return mapUsers.get(id);
	}

	@Override
	public Collection<UserEntity> findAll() {
		return mapUsers.values();
	}

	@Override
	public void persist(UserEntity entity) {
		mapUsers.put(entity.getId(), entity);
	}

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
