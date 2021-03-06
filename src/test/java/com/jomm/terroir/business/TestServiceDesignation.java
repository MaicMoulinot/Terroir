package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceDesignation}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceDesignation}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceDesignation {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Variables //-----------------------------------------------
	/** An implementation of {@link ServiceDesignation}. */
	private ServiceDesignation service;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceDesignation}.
	 */
	public TestServiceDesignation(ServiceDesignation service) {
		this.service = service;
	}
	
	// Test methods //--------------------------------------------
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
	public final void testCreateWithIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId(ID);
		service.create(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#create(Designation)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateWithIdNull() throws ExceptionService {
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
	public final void testUpdateWithIdNull() throws ExceptionService {
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
	public final void testUpdateWithIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId(ID);
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
	public final void testDeleteWithIdNull() throws ExceptionService {
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
	public final void testDeleteWithIdNotNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setId(ID);
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
	 * Test that {@link ServiceDesignation#getDesignation(Long)} throws an {@link ExceptionService}
	 * when the id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetWithIdNull() throws ExceptionService {
		service.getDesignation(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#getDesignation(Long)} does not throw an {@link ExceptionService}
	 * when the id is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testGetWithIdNotNull() throws ExceptionService {
		service.getDesignation(ID);
		assertTrue("ExceptionService should not be thrown", true);
	}
	
	/**
	 * Test that {@link ServiceDesignation#getPriceRange(Designation)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetPriceRangeWithEntityNull() throws ExceptionService {
		service.getPriceRange(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#getPriceRange(Designation)} throws an {@link ExceptionService}
	 * when entity's unit is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetPriceRangeWithUnitNotValid() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setUnit(null);
		service.getPriceRange(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#getPriceRange(Designation)} throws an {@link ExceptionService}
	 * when entity's median price is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetPriceRangeWithMedianPriceNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setMedianPrice(null);
		service.getPriceRange(designation);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#getPriceRange(Designation)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testGetPriceRangeWithCorrectState() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		assertNotNull("ExceptionService should not be thrown", service.getPriceRange(designation));
	}
	
	/**
	 * Test that {@link ServiceDesignation#validatePrice(Designation, BigDecimal, Unit)} 
	 * throws an {@link ExceptionService} when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testValidatePriceWithEntityNull() throws ExceptionService {
		service.validatePrice(null, new BigDecimal("10.36"), Unit.KILOGRAM);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#validatePrice(Designation, BigDecimal, Unit)} 
	 * throws an {@link ExceptionService} when entity's unit is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testValidatePriceWithDesignationUnitNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setUnit(null);
		service.validatePrice(designation, new BigDecimal("10.36"), Unit.KILOGRAM);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#validatePrice(Designation, BigDecimal, Unit)} 
	 * throws an {@link ExceptionService} when entity's median price is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testValidatePriceWithMedianPriceNull() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setMedianPrice(null);
		service.validatePrice(designation, new BigDecimal("10.36"), Unit.KILOGRAM);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceDesignation#validatePrice(Designation, BigDecimal, Unit)} 
	 * does not throw an {@link ExceptionService} when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testValidatePriceWithCorrectState() throws ExceptionService {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		assertTrue("ExceptionService should not be thrown", 
				service.validatePrice(designation, new BigDecimal("10.36"), Unit.KILOGRAM));
	}
	
	// Static methods //------------------------------------------
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
	
	// Helpers //-------------------------------------------------
	/**
	 * Construct a {@link ServiceDesignationImpl} with a mocked DAO.
	 * @return the {@link ServiceDesignationImpl}
	 */
	private static ServiceDesignationImpl generateMockedDesignationServiceImpl() {
		ServiceDesignationImpl impl = new ServiceDesignationImpl();
		impl.setTestDaoDesignation(mock(DaoDesignation.class));
		return impl;
	}
}