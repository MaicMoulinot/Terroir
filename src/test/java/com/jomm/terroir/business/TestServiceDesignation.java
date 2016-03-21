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

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceDesignation}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceDesignation}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceDesignation {
	
	/** An implementation of {@link ServiceDesignation}. */
	private ServiceDesignation service;

	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceDesignation}.
	 */
	public TestServiceDesignation(ServiceDesignation service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceDesignation#create(Designation)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceDesignation#create(Designation)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId((long) 52);
		service.create(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#create(Designation)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateWithEntityNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		service.create(designation);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceDesignation#update(Designation)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#update(Designation)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityIdNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		service.update(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#update(Designation)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithEntityIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId((long) 52);
		service.update(designation);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceDesignation#delete(Designation)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#delete(Designation)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityIdNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		service.delete(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#delete(Designation)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithEntityIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId((long) 52);
		service.delete(designation);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that list from {@link ServiceDesignation#getAllDesignations()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllDesignations());
	}
	
	/**
	 * Reference a list of all {@link ServiceDesignation}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedDesignationServiceImpl()}
			}
		);
	}
	
	/**
	 * Construct a {@link ServiceDesignationImpl} with a mocked DAO.
	 * @return the {@link ServiceDesignationImpl}
	 */
	private static ServiceDesignationImpl generateMockedDesignationServiceImpl() {
		ServiceDesignationImpl impl = new ServiceDesignationImpl();
		impl.setDaoDesignation(mock(DaoDesignation.class));
		return impl;
	}
}