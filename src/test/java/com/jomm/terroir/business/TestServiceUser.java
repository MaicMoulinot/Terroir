package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.Admin;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestAdmin;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.business.model.TestSeller;
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
	
	/** A mocked list to simulate calls to <code>getAllAdmins()</code>. */
	private ArrayList<Admin> mockedAdminList;
	/** A mocked list to simulate calls to <code>getAllCustomers()</code>. */
	private ArrayList<Customer> mockedCustomerList;
	/** A mocked list to simulate calls to <code>getAllSellers()</code>. */
	private ArrayList<Seller> mockedSellerList;
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
	 * Test contract for {@link ServiceUser#isExistingEmail(String)}
	 * and {@link ServiceUser#isExistingUserName(String)}.
	 */
	@Test
	public final void testExistingUserNameAndEmail() {
		String email = "Email";
		String username = "UserName";
		mockedAdminList = new ArrayList<Admin>();
		
		// Before persistence, email and username are not existing (free to use)
		when(service.isExistingEmail(email)).thenReturn(isExistingEmail(email));
		assertFalse(service.isExistingEmail(email));
		when(service.isExistingUserName(username)).thenReturn(isExistingUsername(username));
		assertFalse(service.isExistingUserName(username));
		
		// Create User
		Admin admin = TestAdmin.generateAdmin();
		service.create(admin);
		mockedAdminList.add(admin); // MOCK: simulate create into mockedAdminList
		
		// After persistence, email and username are existing (not free to use)
		when(service.isExistingEmail(email)).thenReturn(isExistingEmail(email));
		assertTrue(service.isExistingEmail(email));
		when(service.isExistingUserName(username)).thenReturn(isExistingUsername(username));
		assertTrue(service.isExistingUserName(username));
		
		// Delete User
		service.delete(admin);
		mockedAdminList.remove(0); // MOCK: simulate delete into mockedAdminList
		
		// After delete, email and username are not existing (free to use)
		when(service.isExistingEmail(email)).thenReturn(isExistingEmail(email));
		assertFalse(service.isExistingEmail(email));
		when(service.isExistingUserName(username)).thenReturn(isExistingUsername(username));
		assertFalse(service.isExistingUserName(username));
	}
	
	/**
	 * Test contract for {@link ServiceUser#create(AbstractUser)}, {@link ServiceUser#update(AbstractUser)},
	 * {@link ServiceUser#delete(AbstractUser)}, {@link ServiceUser#getAllAdmins()},
	 * {@link ServiceUser#getAllCustomers()} and {@link ServiceUser#getAllSellers()}.
	 */
	@Test
	public final void testContract() {
		// MOCK: service.getAllAdmins() with mockedAdminList
		String messageAdmin = "service.getAllAdmins()";
		mockedAdminList = new ArrayList<Admin>();
		when(service.getAllAdmins()).thenReturn(mockedAdminList);
		
		// MOCK: service.getAllCustomers() with mockedCustomerList
		String messageCustomer = "service.getAllCustomers()";
		mockedCustomerList = new ArrayList<Customer>();
		when(service.getAllCustomers()).thenReturn(mockedCustomerList);
		
		// MOCK: service.getAllSellers() with mockedSellerList
		String messageSeller = "service.getAllSellers()";
		mockedSellerList = new ArrayList<Seller>();
		when(service.getAllSellers()).thenReturn(mockedSellerList);
		
		// Before any persistence, all lists are not null and are empty
		assertNotNull(messageAdmin + " should not be null", service.getAllAdmins());
		assertTrue(messageAdmin + " should be empty", service.getAllAdmins().isEmpty());
		assertNotNull(messageCustomer + " should not be null", service.getAllCustomers());
		assertTrue(messageCustomer + " should be empty", service.getAllCustomers().isEmpty());
		assertNotNull(messageSeller + " should not be null", service.getAllSellers());
		assertTrue(messageSeller + " should be empty", service.getAllSellers().isEmpty());
		
		testOnAdmin(messageAdmin);
		testOnCustomer(messageCustomer);
		testOnSeller(messageSeller);
	}
	
	/**
	 * Test create, update and delete on {@link Admin}.
	 * @param message String to clarify errors on test.
	 */
	private void testOnAdmin(String message) {
		// Create Admin
		Admin admin = TestAdmin.generateAdmin();
		service.create(admin);
		mockedAdminList.add(admin); // MOCK: simulate create into mockedAdminList

		int nbCustomers = service.getAllCustomers().size();
		int nbSellers = service.getAllSellers().size();
		// After persistence, the admin list is not empty and its size is now 1
		assertFalse(message + " should not be empty", service.getAllAdmins().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllAdmins().size(), 1);
		// After persistence, the other lists are unchanged
		assertEquals(nbCustomers, service.getAllCustomers().size());
		assertEquals(nbSellers, service.getAllSellers().size());

		// Update
		admin = mockedAdminList.get(0);
		String initialUserName = admin.getUserName();
		admin.setUserName("updatedUserName");
		service.update(admin);
		mockedAdminList.set(0, admin); // MOCK: simulate update into mockedAdminList
		String updatedUserName = service.getAllAdmins().get(0).getUserName();
		assertNotEquals("UserNames should not match", initialUserName, updatedUserName);

		// Delete
		service.delete(admin);
		mockedAdminList.remove(admin); // MOCK: simulate delete into mockedAdminList
		
		// After delete, the admin list is back to empty
		assertTrue(message + " should be empty", service.getAllAdmins().isEmpty());
		// After delete, the other lists are unchanged
		assertEquals(nbCustomers, service.getAllCustomers().size());
		assertEquals(nbSellers, service.getAllSellers().size());
	}
	
	/**
	 * Test create, update and delete on {@link Customer}.
	 * @param message String to clarify errors on test.
	 */
	private void testOnCustomer(String message) {
		// Create Customer
		Customer customer = TestCustomer.generateCustomer();
		service.create(customer);
		mockedCustomerList.add(customer); // MOCK: simulate create into mockedCustomerList

		int nbAdmins = service.getAllAdmins().size();
		int nbSellers = service.getAllSellers().size();
		// After persistence, the Customer list is not empty and its size is now 1
		assertFalse(message + " should not be empty", service.getAllCustomers().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllCustomers().size(), 1);
		// After persistence, the other lists are unchanged
		assertEquals(nbAdmins, service.getAllAdmins().size());
		assertEquals(nbSellers, service.getAllSellers().size());

		// Update
		customer = mockedCustomerList.get(0);
		String initialUserName = customer.getUserName();
		customer.setUserName("updatedUserName");
		service.update(customer);
		mockedCustomerList.set(0, customer); // MOCK: simulate update into mockedCustomerList
		String updatedUserName = service.getAllCustomers().get(0).getUserName();
		assertNotEquals("UserNames should not match", initialUserName, updatedUserName);

		// Delete
		service.delete(customer);
		mockedCustomerList.remove(customer); // MOCK: simulate delete into mockedCustomerList
		
		// After delete, the Customer list is back to empty
		assertTrue(message + " should be empty", service.getAllCustomers().isEmpty());
		// After delete, the other lists are unchanged
		assertEquals(nbAdmins, service.getAllAdmins().size());
		assertEquals(nbSellers, service.getAllSellers().size());
	}
	
	/**
	 * Test create, update and delete on {@link Seller}.
	 * @param message String to clarify errors on test.
	 */
	private void testOnSeller(String message) {
		// Create Seller
		Seller seller = TestSeller.generateSeller();
		service.create(seller);
		mockedSellerList.add(seller); // MOCK: simulate create into mockedSellerList

		int nbCustomers = service.getAllCustomers().size();
		int nbAdmins = service.getAllAdmins().size();
		// After persistence, the Seller list is not empty and its size is now 1
		assertFalse(message + " should not be empty", service.getAllSellers().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllSellers().size(), 1);
		// After persistence, the other lists are unchanged
		assertEquals(nbCustomers, service.getAllCustomers().size());
		assertEquals(nbAdmins, service.getAllAdmins().size());

		// Update
		seller = mockedSellerList.get(0);
		String initialUserName = seller.getUserName();
		seller.setUserName("updatedUserName");
		service.update(seller);
		mockedSellerList.set(0, seller); // MOCK: simulate update into mockedSellerList
		String updatedUserName = service.getAllSellers().get(0).getUserName();
		assertNotEquals("UserNames should not match", initialUserName, updatedUserName);

		// Delete
		service.delete(seller);
		mockedSellerList.remove(seller); // MOCK: simulate delete into mockedSellerList
		
		// After delete, the Seller list is back to empty
		assertTrue(message + " should be empty", service.getAllSellers().isEmpty());
		// After delete, the other lists are unchanged
		assertEquals(nbCustomers, service.getAllCustomers().size());
		assertEquals(nbAdmins, service.getAllAdmins().size());
	}
	
	/**
	 * Reference a list of all {@link ServiceUser}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with <code>testContract()</code>.
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
		impl.setUserDao(Mockito.mock(DaoUser.class));
		impl.setAdminDao(Mockito.mock(DaoAdmin.class));
		impl.setCustomerDao(Mockito.mock(DaoCustomer.class));
		impl.setSellerDao(Mockito.mock(DaoSeller.class));
		return impl;
	}
	
	/**
	 * Determine if the email is existing. Uses the admin mocked list.
	 * @param email String to test.
	 * @return true if the email was found, false otherwise.
	 */
	private boolean isExistingEmail(String email) {
		boolean existingEmail = false;
		if (!mockedAdminList.isEmpty() && mockedAdminList.get(0) != null) {
			existingEmail = Objects.equals(email, mockedAdminList.get(0).getEmail());
		}
		return existingEmail;
	}
	
	/**
	 * Determine if the user name is existing. Uses the admin mocked list.
	 * @param username String to test.
	 * @return true if the user name was found, false otherwise.
	 */
	private boolean isExistingUsername(String username) {
		boolean existingUsername = false;
		if (!mockedAdminList.isEmpty() && mockedAdminList.get(0) != null) {
			existingUsername = Objects.equals(username, mockedAdminList.get(0).getUserName());
		}
		return existingUsername;
	}
}