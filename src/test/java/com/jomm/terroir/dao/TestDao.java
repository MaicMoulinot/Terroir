package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;

/**
 * This abstract Class defines the contract of {@link Dao}.
 * It extends {@link UtilData} and thus has access to its {@code dbSetupTracker.skipNextLaunch()} method.
 * Each System Under Test (SUT) should have its own test class with a call to {@code super.testEntityClassMatch()}.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class TestDao<E extends Serializable> extends UtilData {
	
	// Variables //-----------------------------------------------
	/** An implementation of {@link Dao}. */
	protected Dao<E> dao;
	/** A Class of {@link javax.persistence.Entity} that extends {@link Serializable}. */
	protected Class<E> entityClass;
	
	// Test methods //--------------------------------------------
	/**
	 * Validate the entityClass was properly initialized.
	 */
	public void testEntityClassMatch() {
		dbSetupTracker.skipNextLaunch();
		assertNotNull("entity should not be null", entityClass);
		assertNotNull("dao should not be null", dao);
		Class<E> entityClassFromDao = dao.getEntityClass();
		assertNotNull("EntityClassFromDao should not be null", entityClassFromDao);
		assertEquals("EntityClassFromDao should be " + entityClassFromDao, entityClassFromDao, entityClass);
	}
}