/**
 * 
 */
package com.jomm.terroir.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.dao.SiteDao;

/**
 * This class is a Junit test case testing the methods of {@link SiteServiceImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class SiteServiceImplTest {

	@Mock(name = "dao")
	private SiteDao dao;

	@InjectMocks
	private SiteServiceImpl service;

	/**
	 * Test method for {@link SiteServiceImpl#create(Site)}.
	 */
	@Test
	public final void testCreate() {
		service.create(generateSite());
		verify(dao).create(any(Site.class)); // validate that dao.create() was called
	}

	/**
	 * Test method for {@link SiteServiceImpl#update(Site)}.
	 */
	@Test
	public final void testUpdate() {
		service.update(generateSite());
		verify(dao).update(any(Site.class)); // validate that dao.update() was called
	}

	/**
	 * Test method for {@link SiteServiceImpl#getAllSites()}.
	 */
	@Test
	public final void testGetAllSites() {
		service.getAllSites();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}

	/**
	 * Test method for {@link SiteServiceImpl#delete(Site)}.
	 */
	@Test
	public final void testDelete() {
		service.delete(generateSite());
		verify(dao).delete(any(Site.class)); // validate that dao.delete() was called
	}
	
	/**
	 * Generate a simple {@link Site} usable for tests.
	 * @return a {@link Site}.
	 */
	private Site generateSite() {
		Site site = new Site();
		site.setId((long) 0);
		site.setAddress(new Address());
		site.setEnterprise(new Enterprise());
		site.setLegalIdentification("LegalIdentification");
		site.setListProducts(new ArrayList<Product>());
		site.setSiteName("SiteName");
		return site;
	}
}
