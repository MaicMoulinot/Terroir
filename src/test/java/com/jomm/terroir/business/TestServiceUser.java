package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestAbstractUser;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.dao.DaoAdmin;
import com.jomm.terroir.dao.DaoCustomer;
import com.jomm.terroir.dao.DaoSeller;
import com.jomm.terroir.dao.DaoUser;

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
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link ServiceUser}.
	 */
	public TestServiceUser(ServiceUser service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceUser#create(AbstractUser)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testCreateWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.create(null);
	}

	/**
	 * Test that {@link ServiceUser#create(AbstractUser)} throws an {@link IllegalArgumentException}
	 * when entity's id is not null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateWithEntityIdNotNull() throws NullPointerException, IllegalArgumentException {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		user.setId((long) 52);
		service.create(user);
	}
	
	/**
	 * Test that {@link ServiceUser#create(Customer)} generate properly the sign up date.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test
	public final void testCreateCustomerGenerateSignUpDate() throws NullPointerException, IllegalArgumentException {
		Customer customer = TestCustomer.generateCustomerWithIdNull();
		assertNull("Sign Up Date should not yet be initialized", customer.getSignUpDate());
		ZonedDateTime now = ZonedDateTime.now();
		service.create(customer);
		ZonedDateTime entityDate = customer.getSignUpDate();
		assertNotNull("Sign Up Date should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("Sign Up Date should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));
	}

	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link IllegalArgumentException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testUpdateWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.update(null);
	}

	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link IllegalArgumentException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateWithEntityIdNull() throws NullPointerException, IllegalArgumentException {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		service.update(user);
	}

	/**
	 * Test that {@link ServiceUser#delete(AbstractUser)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testDeleteWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.delete(null);
	}
	
	/**
	 * Test that {@link ServiceUser#update(AbstractUser)} throws an {@link IllegalArgumentException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testDeleteWithEntityIdNull() throws NullPointerException, IllegalArgumentException {
		AbstractUser user = TestAbstractUser.generateAbstractUserWithIdNull();
		service.delete(user);
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
	 * Test that {@link ServiceUser#isExistingUserName(String)} throws an {@link NullPointerException}
	 * when user name is null.
	 * @throws NullPointerException is expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testExistingUserNameWithUserNameNull() throws NullPointerException {
		service.isExistingUserName(null);
	}
	
	/**
	 * Test that {@link ServiceUser#isExistingUserName(String)} do not throw an {@link NullPointerException}
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
	 * Test that {@link ServiceUser#isExistingEmail(String)} do not throw an {@link NullPointerException}
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
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
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
		impl.userDao = Mockito.mock(DaoUser.class);
		impl.adminDao = Mockito.mock(DaoAdmin.class);
		impl.customerDao = Mockito.mock(DaoCustomer.class);
		impl.sellerDao = Mockito.mock(DaoSeller.class);
		return impl;
	}
}