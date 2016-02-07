package com.jomm.terroir.dao;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Admin;
import com.jomm.terroir.business.AdminTest;

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
		entity = AdminTest.generateAdmin();
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
		dao.create(entity);
		mockedList.add(entity); // MOCK: simulate create into mockedList
		
		// Update
		entity = mockedList.get(0);
		String initialValue = entity.getFirstName();
		entity.setFirstName("UpdatedFirstName");
		dao.update(entity);
		mockedList.set(0, entity); // MOCK: simulate update into mockedList
		
		String updatedValue = dao.findAll().get(0).getFirstName();
		assertNotEquals("Values should not match", initialValue, updatedValue);
	}
}