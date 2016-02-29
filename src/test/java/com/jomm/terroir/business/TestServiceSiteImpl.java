package com.jomm.terroir.business;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This class is a Junit test case testing the methods of {@link ServiceSiteImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceSiteImpl {

	@Mock(name = "dao")
	private DaoSite dao;

	@InjectMocks
	private ServiceSiteImpl service;

	/**
	 * Test method for {@link ServiceSiteImpl#create(Site)}.
	 */
	@Test
	public final void testCreate() {
		try {
			service.create(TestSite.generateSiteWithIdNull());
			verify(dao).create(any(Site.class)); // validate that dao.create() was called
		} catch (ExceptionInvalidId | ExceptionNullEntity unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceSiteImpl#update(Site)}.
	 */
	@Test
	public final void testUpdate() {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId((long) 200);
		try {
			service.update(site);
			verify(dao).update(any(Site.class)); // validate that dao.update() was called
		} catch (ExceptionInvalidId | ExceptionNullEntity unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceSiteImpl#delete(Site)}.
	 */
	@Test
	public final void testDelete() {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId((long) 200);
		try {
			service.delete(site);
			verify(dao).delete(any(Site.class)); // validate that dao.delete() was called
		} catch (ExceptionNullEntity | ExceptionInvalidId unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceSiteImpl#getAllSites()}.
	 */
	@Test
	public final void testGetAllSites() {
		service.getAllSites();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
}