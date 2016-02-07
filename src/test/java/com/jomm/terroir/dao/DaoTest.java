package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract Class is testing the contract of {@link Dao} thanks to the tests <code>testContract()</code> 
 * and <code>testUpdate()</code>.
 * It uses a mock list to simulate calls.
 * Each child of this Class must implement <code>testUpdate()</code> with appropriate code.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class DaoTest<E extends Serializable> {
	
	/** A mocked list to simulate calls to <code>findAll()</code>. */
	protected List<E> mockedList;
	/** An implementation of {@link Dao}. */
	protected Dao<E> dao;
	/** An {@link javax.persistence.Entity} that extends {@link Serializable}. */
	protected E entity;
	
	/**
	 * Test contract for {@link Dao#update(Serializable)}.
	 */
	public abstract void testUpdate();
	
	/**
	 * Test contract for {@link Dao#create(Serializable)}, {@link Dao#find(Long)}, {@link Dao#findAll()},
	 * {@link Dao#delete(Serializable)}, and {@link Dao#deleteById(Long)}.
	 */
	public void testContract() {
		String message = "dao.findAll()";
		long index = 0;
		mockedList = new ArrayList<E>();
		when(dao.findAll()).thenReturn(mockedList); // MOCK: dao.findAll() with mockedList
		
		// Before any persistence, the list is not null and is empty
		assertNotNull(message + " should not be null", dao.findAll());
		assertTrue(message + "  should be empty", dao.findAll().isEmpty());
		
		// Create
		dao.create(entity);
		mockedList.add((int) index, entity); // MOCK: simulate create into mockedList
		mockFind(index); // MOCK: dao.find() with mockedList

		// After persistence, the list is not empty and its size is 1
		assertFalse(message + "  should not be empty", dao.findAll().isEmpty());
		assertEquals(message + " ' size should be 1", dao.findAll().size(), 1);
		
		// FindById
		assertNotNull("The entity should not be null after persistence", dao.find(index));
		assertEquals("The entity should be found by id", dao.find(index), entity);
		
		// DeleteById
		dao.deleteById(index);
		mockedList.remove((int) index); // MOCK: simulate delete into mockedList
		mockFind(index);
		assertNull("The entity should be null after deleting", dao.find(index));

		// Create then Delete
		dao.create(entity);
		mockedList.add((int) index, entity); // MOCK: simulate create into mockedList
		mockFind(index);
		assertNotNull("The entity should not be null before delete", dao.find(index));
		dao.delete(entity);
		mockedList.remove((int) index); // MOCK: simulate delete into mockedList
		mockFind(index);
		assertNull("The entity should be null after delete", dao.find(index));
		
		// After delete, the list is back to empty
		assertTrue(message + " should be empty", dao.findAll().isEmpty());
	}
	
	/**
	 * Mock the method {@link Dao#find(Long)} with the <code>mockedList.get(int)</code>.
	 * @param index long the index.
	 */
	private void mockFind(long index) {
		when(dao.find(index)).thenReturn(mockedList.isEmpty() ? null : mockedList.get((int)index));
	}
}