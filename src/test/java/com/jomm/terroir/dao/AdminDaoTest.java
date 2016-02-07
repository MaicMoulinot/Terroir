package com.jomm.terroir.dao;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Admin;

/**
 * This Class is a Junit test case testing the contract of {@link AdminDao}.
 * It extends {@link DaoTest} with the parameter {@link Admin}.
 * @author Maic
 */
public class AdminDaoTest extends DaoTest<Admin> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(AdminDao.class);
		entity = generateAdmin();
	}
	
	@Override
	@Test
	public final void testContract() {
		super.testContract();
	}
	
	@Override
	@Test
	public final void testUpdate() {
		mockedList = new ArrayList<Admin>();
		when(dao.findAll()).thenReturn(mockedList); // MOCK: dao.findAll() with mockedList

		// Create
		entity = generateAdmin();
		dao.create(entity);
		mockedList.add(entity); // MOCK: simulate create into mockedList
		
		// Update
		entity = mockedList.get(0);
		String initialFirstName = entity.getFirstName();
		entity.setFirstName("UpdatedFirstName");
		dao.update(entity);
		mockedList.set(0, entity); // MOCK: simulate update into mockedList
		
		String updatedFirstName = dao.findAll().get(0).getFirstName();
		assertNotEquals("FirstNames should not match", initialFirstName, updatedFirstName);
	}
	
	/**
	 * Generate a simple {@link Admin} usable for tests.
	 * @return a {@link Admin}.
	 */
	private Admin generateAdmin() {
		Admin admin = new Admin();
		admin.setId((long) 0);
		admin.setEmail("Email");
		admin.setFirstName("FirstName");
		admin.setLastName("LastName");
		admin.setUserName("UserName");
		admin.setUserPassword("UserPassword");
		return admin;
	}
}