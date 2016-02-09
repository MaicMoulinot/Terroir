package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;

/**
 * This abstract Class defines the contract of {@link Dao}.
 * Each System Under Test (SUT) should have its own test class implementing <code>testEntityClass()</code>.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class DaoTest<E extends Serializable> {
	
	/** An implementation of {@link Dao}. */
	protected Dao<E> dao;
	/** An {@link javax.persistence.Entity} that extends {@link Serializable}. */
	protected E entity;
	
	/**
	 * Validate the entityClass was properly initialized.
	 */
	public void testEntityClass() {
		assertNotNull("EntityClass should not be null", dao.getEntityClass());
		assertEquals("EntityClass should be " + entity, entity.getClass(), dao.getEntityClass());
	}
}