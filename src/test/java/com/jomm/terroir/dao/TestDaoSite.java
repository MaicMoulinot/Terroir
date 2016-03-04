package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Site;

/**
 * This Class is a Junit test case testing the contract of {@link DaoSite}.
 * It extends {@link TestDao} with the parameter {@link Site}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoSite}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoSite extends TestDao<Site> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link SiteDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link DaoSite}.
	 */
    public TestDaoSite(DaoSite dao) {
        this.dao = dao;
        entityClass = Site.class;
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all {@link DaoSite}'s concrete children to be used as parameter on constructor.
	 * @return {@code Iterable<Object[]>}.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoSiteJpa()}
			}
		);
	}
}