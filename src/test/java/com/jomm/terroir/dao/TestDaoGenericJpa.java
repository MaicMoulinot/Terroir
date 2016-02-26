package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This abstract Class defines the contract of {@link DaoGenericJpa}.
 * It extends {@link UtilData} and thus has access to its <code>dbSetupTracker.skipNextLaunch()</code> method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * Each System Under Test (SUT) should extends this class implementing the methods 
 * <code>testState()</code> and <code>testBehavior()</code>.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class TestDaoGenericJpa<E extends Serializable> extends UtilData {

	/** An implementation of {@link DaoGenericJpa}. */
	protected DaoGenericJpa<E> dao;
	/** An {@link javax.persistence.Entity} that extends {@link Serializable}. */
	protected E entity;
	@Mock
	protected TypedQuery<E> mockedQuery;

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
		dbSetupTracker.skipNextLaunch();
		testCreate();		
		testUpdate();		
		testDelete();		
		testDeleteById();		
		testFind();
		testFindAll();
	}

	/**
	 * Test method for {@link DaoGenericJpa#create(Serializable)}.
	 */
	private void testCreate() {
		dao.create(entity);
		// validate that entityManager.persist() was called
		verify(dao.getEntityManager()).persist(any(Serializable.class));
	}

	/**
	 * Test method for {@link DaoGenericJpa#update(Serializable)}.
	 */
	private void testUpdate() {
		dao.update(entity);
		// validate that entityManager.merge() was called
		verify(dao.getEntityManager()).merge(any(Serializable.class));
	}

	/**
	 * Test method for {@link DaoGenericJpa#delete(Serializable)}.
	 */
	private void testDelete() {
		dao.delete(entity);
		// validate that entityManager.remove() was called
		verify(dao.getEntityManager()).remove(any(Serializable.class));
	}

	/**
	 * Test method for {@link DaoGenericJpa#deleteById(Long)}.
	 */
	private void testDeleteById() {
		dao.deleteById((long) 0);
		// validate that entityManager.getReference() was called
		verify(dao.getEntityManager()).getReference(dao.getEntityClass(), (long) 0);
		verify(dao.getEntityManager(), times(2)).remove(any(Serializable.class));
	}

	/**
	 * Test method for {@link DaoGenericJpa#find(java.lang.Long)}.
	 */
	private void testFind() {
		dao.find((long) 0);
		// validate that entityManager.find() was called
		verify(dao.getEntityManager()).find(dao.getEntityClass(), (long) 0);
	}

	/**
	 * Test method for {@link DaoGenericJpa#findAll()}.
	 */
	private void testFindAll() {
		// stub the EntityManager to return a mocked Query
		when(dao.getEntityManager().createNamedQuery(anyString(), eq(dao.getEntityClass()))).thenReturn(mockedQuery);
		// stub the Query to return a dummy List
		List<E> dummyList = new ArrayList<>();
		when(mockedQuery.getResultList()).thenReturn(dummyList);

		List<E> result = dao.findAll();
		
		// validate that entityManager.createNamedQuery() was called
		verify(dao.getEntityManager()).createNamedQuery(anyString(), eq(dao.getEntityClass()));
		// validate that mockedQuery.getResultList() was called
		verify(mockedQuery).getResultList();
		// validate that the result is a List
		assertEquals(dummyList, result);
	}
}