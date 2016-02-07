package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jomm.terroir.business.Admin;

/**
 * This Class is a Junit test case testing {@link AdminDaoJpa}.
 * It verifies that the <code>entityClass</code> is an {@link Admin}.
 * @author Maic
 */
public class AdminDaoJpaTest {

	/**
	 * Test method for {@link AdminDaoJpa}.
	 */
	@Test
	public final void testMatch() {
		AdminDaoJpa dao = new AdminDaoJpa();
		assertEquals(dao.getEntityClass(), Admin.class);
	}
}