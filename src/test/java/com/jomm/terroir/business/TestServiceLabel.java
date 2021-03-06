package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.business.model.TestLabel;
import com.jomm.terroir.dao.DaoLabel;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceLabel}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceLabel}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceLabel {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Variables //-----------------------------------------------
	/** An implementation of {@link ServiceLabel}. */
	private ServiceLabel service;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceLabel}.
	 */
	public TestServiceLabel(ServiceLabel service) {
		this.service = service;
	}
	
	// Test methods //--------------------------------------------
	/**
	 * Test that {@link ServiceLabel#create(Label)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceLabel#create(Label)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithIdNotNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		label.setId(ID);
		service.create(label);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#create(Label)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateWithIdNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		service.create(label);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceLabel#update(Label)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#update(Label)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithIdNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		service.update(label);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#update(Label)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithIdNotNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		label.setId(ID);
		service.update(label);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceLabel#delete(Label)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#delete(Label)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithIdNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		service.delete(label);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#delete(Label)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws ExceptionService {
		Label label = TestLabel.generateLabelWithIdNull();
		label.setId(ID);
		service.delete(label);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that list from {@link ServiceLabel#getAllLabels()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllLabels());
	}
	
	/**
	 * Test that {@link ServiceLabel#getLabel(Long)} throws an {@link ExceptionService}
	 * when the id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetWithIdNull() throws ExceptionService {
		service.getLabel(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceLabel#getLabel(Long)} does not throw an {@link ExceptionService}
	 * when the id is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testGetWithIdNotNull() throws ExceptionService {
		service.getLabel(ID);
		assertTrue("ExceptionService should not be thrown", true);
	}
	
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all {@link ServiceLabel}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedLabelServiceImpl()}
			}
		);
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Construct a {@link ServiceLabelImpl} with a mocked DAO.
	 * @return the {@link ServiceLabelImpl}
	 */
	private static ServiceLabelImpl generateMockedLabelServiceImpl() {
		ServiceLabelImpl impl = new ServiceLabelImpl();
		impl.setTestDaoLabel(mock(DaoLabel.class));
		return impl;
	}
}