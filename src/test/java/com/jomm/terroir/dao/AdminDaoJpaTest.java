package com.jomm.terroir.dao;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Admin;
import com.jomm.terroir.business.AdminTest;

/**
 * This Class is a Junit test case testing {@link AdminDaoJpa}.
 * It extends {@link GenericDaoTest} with {@link Admin} as parameter, and implements <code>testContract()</code>.
 * @author Maic
 */
public class AdminDaoJpaTest extends GenericDaoTest<Admin> {
	
	@Override
	@Test
	public void testContract() {
		dao = new AdminDaoJpa();
		dao.setEntityManager(Mockito.mock(EntityManager.class));
		entity = AdminTest.generateAdmin();
		super.testContract();
	}
}