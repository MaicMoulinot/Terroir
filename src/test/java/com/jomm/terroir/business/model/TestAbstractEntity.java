package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link AbstractEntity}.
 * @author Maic
 */
public class TestAbstractEntity {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 33L;
	private AbstractEntity entity;
	
	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = TestAdministrator.generateAdministratorWithIdNull(); // a concrete child of AbstractEntity
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		entity = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for {@link AbstractEntity#getId()}.
	 */
	@Test
	public final void testGetId() {
		// Id null
		assertNull(entity.getId());
		entity.setId(ID);
		// Id not null
		assertNotNull(entity.getId());
		assertEquals(ID, entity.getId());
	}
	
	/**
	 * Test method for {@link AbstractEntity#hashCode()}.
	 */
	@Test
	public final void testHashCode() {
		// Id null
		int hashCodeWithoutId = entity.hashCode();
		entity.setId(ID);
		// Id not null
		assertNotEquals(hashCodeWithoutId, entity.hashCode());
		assertEquals(entity.getClass().getSimpleName().hashCode() + entity.getId().hashCode(), entity.hashCode());
	}
	
	/**
	 * Test method for {@link AbstractEntity#toString()}.
	 */
	@Test
	public final void testToString() {
		// Id null
		assertEquals(entity.getClass().getSimpleName() + "[id=null]", entity.toString());
		entity.setId(ID);
		// Id not null
		assertEquals(entity.getClass().getSimpleName() + "[id=" + entity.getId() + "]", entity.toString());
	}
	
	/**
	 * Test method for {@link AbstractEntity#equals(Object)}.
	 */
	@Test
	public final void testEquals() {
		// Id null
		AbstractEntity sameReference = entity;
		AbstractEntity sameClass = TestAdministrator.generateAdministratorWithIdNull();
		AbstractEntity otherClass = TestStock.generateStockWithIdNull();
		assertTrue(entity.equals(entity));
		assertTrue(entity.equals(sameReference));
		assertFalse(entity.equals(sameClass));
		assertFalse(entity.equals(otherClass));
		entity.setId(ID);
		// Id not null
		assertTrue(entity.equals(entity));
		assertTrue(entity.equals(sameReference));
		assertFalse(entity.equals(sameClass));
		sameClass.setId(ID);
		assertTrue(entity.equals(sameClass));
		otherClass.setId(ID);
		assertFalse(entity.equals(otherClass));
	}
}