package com.jomm.terroir.business;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ServiceDesignationImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceDesignationImpl {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Injected Fields //-----------------------------------------
	@Mock(name = "dao")
	private DaoDesignation dao;
	
	@InjectMocks
	private ServiceDesignationImpl service;
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link ServiceDesignationImpl#create(Designation)}.
	 */
	@Test
	public final void testCreate() {
		try {
			service.create(TestDesignation.generateDesignationWithIdNull());
			verify(dao).create(any(Designation.class)); // validate that dao.create() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceDesignationImpl#update(Designation)}.
	 */
	@Test
	public final void testUpdate() {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId(ID);
		try {
			service.update(designation);
			verify(dao).update(any(Designation.class)); // validate that dao.update() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceDesignationImpl#delete(Designation)}.
	 */
	@Test
	public final void testDelete() {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId(ID);
		try {
			service.delete(designation);
			verify(dao).delete(any(Designation.class)); // validate that dao.delete() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceDesignationImpl#getAllDesignations()}.
	 */
	@Test
	public final void testGetAllDesignations() {
		service.getAllDesignations();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
	
	/**
	 * Test method for {@link ServiceDesignationImpl#getDesignation(Long)}.
	 */
	@Test
	public final void testGetDesignation() {
		try {
			service.getDesignation(ID);
			verify(dao).find(anyLong()); // validate that dao.find() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
}