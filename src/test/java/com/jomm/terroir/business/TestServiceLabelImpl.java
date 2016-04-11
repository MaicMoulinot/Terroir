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

import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.business.model.TestLabel;
import com.jomm.terroir.dao.DaoLabel;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ServiceLabelImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceLabelImpl {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Injected Fields //-----------------------------------------
	@Mock(name = "dao")
	private DaoLabel dao;
	
	@InjectMocks
	private ServiceLabelImpl service;
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link ServiceLabelImpl#create(Label)}.
	 */
	@Test
	public final void testCreate() {
		try {
			service.create(TestLabel.generateLabelWithIdNull());
			verify(dao).create(any(Label.class)); // validate that dao.create() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceLabelImpl#update(Label)}.
	 */
	@Test
	public final void testUpdate() {
		Label label = TestLabel.generateLabelWithIdNull();
		label.setId(ID);
		try {
			service.update(label);
			verify(dao).update(any(Label.class)); // validate that dao.update() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceLabelImpl#delete(Label)}.
	 */
	@Test
	public final void testDelete() {
		Label label = TestLabel.generateLabelWithIdNull();
		label.setId(ID);
		try {
			service.delete(label);
			verify(dao).delete(any(Label.class)); // validate that dao.delete() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceLabelImpl#getAllLabels()}.
	 */
	@Test
	public final void testGetAllLabels() {
		service.getAllLabels();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
	
	/**
	 * Test method for {@link ServiceLabelImpl#getLabel(Long)}.
	 */
	@Test
	public final void testGetLabel() {
		try {
			service.getLabel(ID);
			verify(dao).find(anyLong()); // validate that dao.find() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
}