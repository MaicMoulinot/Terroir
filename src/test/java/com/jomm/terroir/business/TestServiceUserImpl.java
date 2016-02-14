package com.jomm.terroir.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.TestAbstractUser;
import com.jomm.terroir.dao.DaoAdmin;
import com.jomm.terroir.dao.DaoCustomer;
import com.jomm.terroir.dao.DaoSeller;
import com.jomm.terroir.dao.DaoUser;

/**
 * This class is a Junit test case testing the methods of {@link ServiceUserImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceUserImpl {

	@Mock(name = "dao")
	private DaoUser dao;
	
	@Mock(name = "daoAdmin")
	private DaoAdmin daoAdmin;
	
	@Mock(name = "daoCustomer")
	private DaoCustomer daoCustomer;
	
	@Mock(name = "daoSeller")
	private DaoSeller daoSeller;

	@InjectMocks
	private ServiceUserImpl service;

	/**
	 * Test method for {@link ServiceUserImpl#create(AbstractUser)}.
	 */
	@Test
	public final void testCreate() {
		service.create(TestAbstractUser.generateAbstractUser());
		verify(dao).create(any(AbstractUser.class)); // validate that dao.create() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#update(AbstractUser)}.
	 */
	@Test
	public final void testUpdate() {
		service.update(TestAbstractUser.generateAbstractUser());
		verify(dao).update(any(AbstractUser.class)); // validate that dao.update() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#delete(AbstractUser)}.
	 */
	@Test
	public final void testDelete() {
		service.delete(TestAbstractUser.generateAbstractUser());
		verify(dao).delete(any(AbstractUser.class)); // validate that dao.delete() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#getAllAdmins()}.
	 */
	@Test
	public final void testGetAllAdmins() {
		service.getAllAdmins();
		verify(daoAdmin).findAll(); // validate that daoAdmin.findAll() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#getAllCustomers()}.
	 */
	@Test
	public final void testGetAllCustomers() {
		service.getAllCustomers();
		verify(daoCustomer).findAll(); // validate that daoCustomer.findAll() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#getAllSellers()}.
	 */
	@Test
	public final void testGetAllSellers() {
		service.getAllSellers();
		verify(daoSeller).findAll(); // validate that daoSeller.findAll() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#isExistingUserName(String)}.
	 */
	@Test
	public final void testIsExistingUserName() {
		service.isExistingUserName("UserName");
		verify(dao).isExistingUserName(any(String.class)); // validate that dao.isExistingUserName() was called
	}

	/**
	 * Test method for {@link ServiceUserImpl#isExistingEmail(String)}.
	 */
	@Test
	public final void testIsExistingEmail() {
		service.isExistingEmail("Email");
		verify(dao).isExistingEmail(any(String.class)); // validate that dao.isExistingEmail() was called
	}
}