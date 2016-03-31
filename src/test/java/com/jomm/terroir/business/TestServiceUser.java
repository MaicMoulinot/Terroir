package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Administrator;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestAbstractUser;
import com.jomm.terroir.business.model.TestAdministrator;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.dao.DaoAdministrator;
import com.jomm.terroir.dao.DaoCustomer;
import com.jomm.terroir.dao.DaoSeller;
import com.jomm.terroir.dao.DaoUser;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceUser {	
	
	/** An implementation of {@link ServiceUser}. */
	private ServiceUser service;

	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceUser}.
	 */
	public TestServiceUser(ServiceUser service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceUser#create(AbstractUser)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceUser#create(AbstractUser)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityIdNotNull() throws ExceptionService {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		user.setId((long) 52);
		service.create(user);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceUser#create(Administrator)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateEntityNotCustomer() throws ExceptionService {
		Administrator admin = TestAdministrator.generateAdministratorWithIdNull();
		service.create(admin);
		assertTrue("ExceptionService should not be thrown", true);
	}
	
	/**
	 * Test that {@link ServiceUser#create(Customer)} does not throw an {@link ExceptionService}
	 * when entity's state is correct, and properly generates the RegistrationDate.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateCustomerSetRegistrationDate() throws ExceptionService {
		Customer customer = TestCustomer.generateCustomerWithIdNull();
		assertNull("RegistrationDate should not yet be initialized", customer.getRegistrationDate());
		ZonedDateTime now = ZonedDateTime.now();
		service.create(customer);
		ZonedDateTime entityDate = customer.getRegistrationDate();
		assertNotNull("RegistrationDate should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("RegistrationDate should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));
	}

	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithEntityIdNotNull() throws ExceptionService {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		user.setId((long) 52);
		service.update(user);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityIdNull() throws ExceptionService {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		service.update(user);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceUser#delete(AbstractUser)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceUser#delete(AbstractUser)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithEntityIdNotNull() throws ExceptionService {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		user.setId((long) 52);
		service.delete(user);
		assertTrue("ExceptionService should not be thrown", true);
	}
	
	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityIdNull() throws ExceptionService {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		service.delete(user);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that list from {@link ServiceUser#getAllAdmins()} is not null.
	 */
	@Test
	public final void testgetAllAdminsListIsNotNull() {
		assertNotNull(service.getAllAdmins());
	}
	
	/**
	 * Test that list from {@link ServiceUser#getAllCustomers()} is not null.
	 */
	@Test
	public final void testgetAllCustomersListIsNotNull() {
		assertNotNull(service.getAllCustomers());
	}
	
	/**
	 * Test that list from {@link ServiceUser#getAllSellers()} is not null.
	 */
	@Test
	public final void testGetAllSellersListIsNotNull() {
		assertNotNull(service.getAllSellers());
	}
	
	/**
	 * Test that {@link ServiceUser#isExistingUserName(String)} throws an {@link ExceptionService}
	 * when user name is null.
	 * @throws NullPointerException is expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testExistingUserNameWithUserNameNull() throws NullPointerException {
		service.isExistingUserName(null);
	}
	
	/**
	 * Test that {@link ServiceUser#isExistingUserName(String)} does not throw an {@link NullPointerException}
	 * when user name is not null.
	 */
	@Test
	public final void testExistingUserNameWithUserNameNotNull() {
		String test = "test";
		try {
			service.isExistingUserName(test);
			assertNotNull(test);
		} catch (NullPointerException unexpectedException) {
			assertNull("A NullPointerException was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test that {@link ServiceUser#isExistingEmail(String)} throws an {@link NullPointerException}
	 * when email is null.
	 * @throws NullPointerException is expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testExistingEmailWithEmailNull() throws NullPointerException {
		service.isExistingEmail(null);
	}
	
	/**
	 * Test that {@link ServiceUser#isExistingEmail(String)} does not throw an {@link ExceptionService}
	 * when email is not null.
	 */
	@Test
	public final void testExistingEmailWithEmailNotNull() {
		String test = "test";
		try {
			service.isExistingEmail(test);
			assertNotNull(test);
		} catch (NullPointerException unexpectedException) {
			assertNull("A NullPointerException was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Reference a list of all {@link ServiceUser}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedUserServiceImpl()}
			}
		);
	}
	
	/**
	 * Construct a {@link ServiceUserImpl} with a mocked DAO.
	 * @return the {@link ServiceUserImpl}
	 */
	private static ServiceUserImpl generateMockedUserServiceImpl() {
		ServiceUserImpl impl = new ServiceUserImpl();
		impl.setTestDaoAdmin(mock(DaoAdministrator.class));
		impl.setTestDaoCustomer(mock(DaoCustomer.class));
		impl.setTestDaoSeller(mock(DaoSeller.class));
		impl.setTestDaoUser(mock(DaoUser.class));
		return impl;
	}
}