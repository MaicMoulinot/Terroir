package com.jomm.terroir.dao;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.ProductTest;

/**
 * This Class is a Junit test case testing the contract of {@link ProductDao}.
 * It extends {@link DaoTest} with the parameter {@link Product}.
 * @author Maic
 */
public class ProductDaoTest extends DaoTest<Product> {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(ProductDao.class);
		entity = ProductTest.generateProduct();
	}
	
	@Override
	@Test
	public final void testContract() {
		super.testContract();
	}
	
	@Override
	@Test
	public final void testUpdate() {
		mockedList = new ArrayList<Product>();
		when(dao.findAll()).thenReturn(mockedList); // MOCK: dao.findAll() with mockedList

		// Create
		dao.create(entity);
		mockedList.add(entity); // MOCK: simulate create into mockedList
		
		// Update
		entity = mockedList.get(0);
		String initialValue = entity.getDescription();
		entity.setDescription("UpdatedDescription");
		dao.update(entity);
		mockedList.set(0, entity); // MOCK: simulate update into mockedList
		
		String updatedValue = dao.findAll().get(0).getDescription();
		assertNotEquals("Values should not match", initialValue, updatedValue);
	}
}