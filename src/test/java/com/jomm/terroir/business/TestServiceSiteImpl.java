/**
 * 
 */
package com.jomm.terroir.business;

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
		service.create(TestSite.generateSite());
		verify(dao).create(any(Site.class)); // validate that dao.create() was called
	}

	/**
	 * Test method for {@link ServiceSiteImpl#update(Site)}.
	 */
	@Test
	public final void testUpdate() {
		service.update(TestSite.generateSite());
		verify(dao).update(any(Site.class)); // validate that dao.update() was called
	}

	/**
	 * Test method for {@link ServiceSiteImpl#getAllSites()}.
	 */
	@Test
	public final void testGetAllSites() {
		service.getAllSites();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}

	/**
	 * Test method for {@link ServiceSiteImpl#delete(Site)}.
	 */
	@Test
	public final void testDelete() {
		service.delete(TestSite.generateSite());
		verify(dao).delete(any(Site.class)); // validate that dao.delete() was called
	}
}