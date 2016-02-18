package com.jomm.terroir.business;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.dao.DaoEnterprise;

/**
 * This class is a Junit test case testing the methods of {@link ServiceEnterpriseImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceEnterpriseImpl {
	
	@Mock(name = "dao")
	private DaoEnterprise dao;
	
	@InjectMocks
	private ServiceEnterpriseImpl service;
	
	/**
	 * Test method for {@link ServiceEnterpriseImpl#create(Enterprise)}.
	 */
	@Test
	public final void testCreate() {
		try {
			service.create(TestEnterprise.generateEnterpriseWithIdNull());
			verify(dao).create(any(Enterprise.class)); // validate that dao.create() was called
		} catch (IllegalStateException | NullPointerException unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceEnterpriseImpl#update(Enterprise)}.
	 */
	@Test
	public final void testUpdate() {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 200);
		try {
			service.update(enterprise);
			verify(dao).update(any(Enterprise.class)); // validate that dao.update() was called
		} catch (IllegalStateException | NullPointerException unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceEnterpriseImpl#delete(Enterprise)}.
	 */
	@Test
	public final void testDelete() {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 200);
		try {
			service.delete(enterprise);
			verify(dao).delete(any(Enterprise.class)); // validate that dao.delete() was called
		} catch (NullPointerException | IllegalStateException unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceEnterpriseImpl#getAllEnterprises()}.
	 */
	@Test
	public final void testGetAllEnterprises() {
		service.getAllEnterprises();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
}