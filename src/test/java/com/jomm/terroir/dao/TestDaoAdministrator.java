package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Administrator;

/**
 * This Class is a Junit test case testing the contract of {@link DaoAdministrator}.
 * It extends {@link TestDao} with the parameter {@link Administrator}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoAdministrator}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoAdministrator extends TestDao<Administrator> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestDaoAdministrator#implementationToTest()}.
	 * @param dao the implementation of {@link DaoAdministrator}.
	 */
    public TestDaoAdministrator(DaoAdministrator dao) {
        this.dao = dao;
        entityClass = Administrator.class;
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all implementations of {@link DaoAdministrator} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoAdministratorJpa()}
			}
		);
	}
}