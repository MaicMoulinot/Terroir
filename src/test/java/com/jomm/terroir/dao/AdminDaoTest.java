package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.Admin;
import com.jomm.terroir.business.AdminTest;

/**
 * This Class is a Junit test case testing the contract of {@link AdminDao}.
 * It extends {@link DaoTest} with the parameter {@link Admin}, and implements <code>testEntityClass()</code>.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link AdminDao}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class AdminDaoTest extends DaoTest<Admin> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link AdminDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link AdminDao}.
	 */
    public AdminDaoTest(AdminDao dao) {
        this.dao = dao;
        this.entity = AdminTest.generateAdmin();
    }
    
	@Override
	@Test
	public final void testEntityClass() {
		super.testEntityClass();
	}
    
	/**
	 * Reference a list of all {@link AdminDao}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new AdminDaoJpa()}
			}
		);
	}
}