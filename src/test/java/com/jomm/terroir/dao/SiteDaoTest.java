package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.Site;
import com.jomm.terroir.business.SiteTest;

/**
 * This Class is a Junit test case testing the contract of {@link SiteDao}.
 * It extends {@link DaoTest} with the parameter {@link Site}, and implements <code>testEntityClassMatch()</code>.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link SiteDao}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class SiteDaoTest extends DaoTest<Site> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link SiteDaooTest#implementationToTest()}.
	 * @param dao the implementation of {@link SiteDao}.
	 */
    public SiteDaoTest(SiteDao dao) {
        this.dao = dao;
        this.entity = SiteTest.generateSite();
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all {@link SiteDao}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new SiteDaoJpa()}
			}
		);
	}
}