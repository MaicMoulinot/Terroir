package com.jomm.terroir.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.dao.AdminDao;
import com.jomm.terroir.dao.CustomerDao;
import com.jomm.terroir.dao.SellerDao;
import com.jomm.terroir.dao.UserDao;

/**
 * This class is a Junit test case testing the methods of {@link UserServiceImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock(name = "dao")
	private UserDao dao;
	
	@Mock(name = "daoAdmin")
	private AdminDao daoAdmin;
	
	@Mock(name = "daoCustomer")
	private CustomerDao daoCustomer;
	
	@Mock(name = "daoSeller")
	private SellerDao daoSeller;

	@InjectMocks
	private UserServiceImpl service;

	/**
	 * Test method for {@link UserServiceImpl#create(AbstractUser)}.
	 */
	@Test
	public final void testCreate() {
		service.create(AbstractUserTest.generateAbstractUser());
		verify(dao).create(any(AbstractUser.class)); // validate that dao.create() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#update(AbstractUser)}.
	 */
	@Test
	public final void testUpdate() {
		service.update(AbstractUserTest.generateAbstractUser());
		verify(dao).update(any(AbstractUser.class)); // validate that dao.update() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#delete(AbstractUser)}.
	 */
	@Test
	public final void testDelete() {
		service.delete(AbstractUserTest.generateAbstractUser());
		verify(dao).delete(any(AbstractUser.class)); // validate that dao.delete() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#getAllAdmins()}.
	 */
	@Test
	public final void testGetAllAdmins() {
		service.getAllAdmins();
		verify(daoAdmin).findAll(); // validate that daoAdmin.findAll() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#getAllCustomers()}.
	 */
	@Test
	public final void testGetAllCustomers() {
		service.getAllCustomers();
		verify(daoCustomer).findAll(); // validate that daoCustomer.findAll() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#getAllSellers()}.
	 */
	@Test
	public final void testGetAllSellers() {
		service.getAllSellers();
		verify(daoSeller).findAll(); // validate that daoSeller.findAll() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#isExistingUserName(String)}.
	 */
	@Test
	public final void testIsExistingUserName() {
		service.isExistingUserName("UserName");
		verify(dao).isExistingUserName(any(String.class)); // validate that dao.isExistingUserName() was called
	}

	/**
	 * Test method for {@link UserServiceImpl#isExistingEmail(String)}.
	 */
	@Test
	public final void testIsExistingEmail() {
		service.isExistingEmail("Email");
		verify(dao).isExistingEmail(any(String.class)); // validate that dao.isExistingEmail() was called
	}
}