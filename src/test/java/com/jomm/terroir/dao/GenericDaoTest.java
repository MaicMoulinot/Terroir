package com.jomm.terroir.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.io.Serializable;

/**
 * This abstract Class defines the contract of {@link GenericDao}.
 * Each System Under Test (SUT) should extends this class implementing the methods 
 * <code>testState()</code> and <code>testBehavior()</code>.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class GenericDaoTest<E extends Serializable> {
	
	/** An implementation of {@link GenericDao}. */
	protected GenericDao<E> dao;
	/** An {@link javax.persistence.Entity} that extends {@link Serializable}. */
	protected E entity;
	
	/**
	 * State verification of DAO's methods.
	 * This test determines if the methods under test worked correctly by examining 
	 * the state of the System Under Test (SUT) and its collaborators after the methods are exercised.
	 */
	public abstract void testState();
	
	/**
	 * Behavior verification of DAO's methods.
	 * This test determines if the methods under test worked correctly by checking to see
	 * if the System Under Test (SUT) made the correct calls when the methods are exercised.
	 */
	public void testBehavior() {
		testCreate();		
		testUpdate();		
		testDelete();		
		testDeleteById();		
		testFind();
		testFindAll();
	}
	
	/**
	 * Test method for {@link GenericDao#create(Serializable)}.
	 */
	private void testCreate() {
		dao.create(entity);
		// validate that entityManager.persist() was called
		verify(dao.getEntityManager()).persist(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#update(Serializable)}.
	 */
	private void testUpdate() {
		dao.update(entity);
		// validate that entityManager.merge() was called
		verify(dao.getEntityManager()).merge(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#delete(Serializable)}.
	 */
	private void testDelete() {
		dao.delete(entity);
		// validate that entityManager.remove() was called
		verify(dao.getEntityManager()).remove(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#deleteById(Long)}.
	 */
	private void testDeleteById() {
		dao.deleteById((long) 0);
		// validate that entityManager.getReference() was called
		verify(dao.getEntityManager()).getReference(dao.getEntityClass(), (long) 0);
	}

	/**
	 * Test method for {@link GenericDao#find(java.lang.Long)}.
	 */
	private void testFind() {
		dao.find((long) 0);
		// validate that entityManager.find() was called
		verify(dao.getEntityManager()).find(dao.getEntityClass(), (long) 0);
	}

	/**
	 * Test method for {@link GenericDao#findAll()}.
	 */
	private void testFindAll() {
		dao.findAll();
		// validate that entityManager.createNamedQuery() was called
		verify(dao.getEntityManager()).createNamedQuery(any(String.class));
	}
}