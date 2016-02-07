package com.jomm.terroir.dao;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.ProductTest;

/**
 * This Class is a Junit test case testing {@link ProductDaoJpa}.
 * It extends {@link GenericDaoTest} with {@link Product} as parameter, and implements <code>testContract()</code>.
 * @author Maic
 */
public class ProductDaoJpaTest extends GenericDaoTest<Product> {
	
	@Override
	@Test
	public void testContract() {
		dao = new ProductDaoJpa();
		dao.setEntityManager(Mockito.mock(EntityManager.class));
		entity = ProductTest.generateProduct();
		super.testContract();
	}
}