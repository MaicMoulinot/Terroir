package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jomm.terroir.business.Product;

/**
 * This Class is a Junit test case testing {@link ProductDaoJpa}.
 * It verifies that the <code>entityClass</code> is an {@link Product}.
 * @author Maic
 */
public class ProductDaoJpaTest {

	/**
	 * Test method for {@link AdminDaoJpa}.
	 */
	@Test
	public final void testMatch() {
		ProductDaoJpa dao = new ProductDaoJpa();
		assertEquals(dao.getEntityClass(), Product.class);
	}
}
