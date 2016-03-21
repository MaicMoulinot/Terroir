package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link AbstractEntity}.
 * @author Maic
 */
public class TestAbstractEntity {

	/**
	 * Test method for {@link AbstractEntity#getId()}.
	 */
	@Test
	public final void testGetId() {
		Long id = (long) 33;
		AbstractEntity entity = TestAdministrator.generateAdministratorWithIdNull(); // a concrete child of AbstractEntity
		assertNull(entity.getId());
		((Administrator) entity).setId(id);
		assertNotNull(entity.getId());
		assertEquals(id, entity.getId());
	}
}