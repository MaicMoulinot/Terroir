package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Designation;

/**
 * This Class is a Junit test case testing the contract of {@link DaoDesignation}.
 * It extends {@link TestDao} with the parameter {@link Designation}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoDesignation}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoDesignation extends TestDao<Designation> {
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link DesignationDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link DaoDesignation}.
	 */
    public TestDaoDesignation(DaoDesignation dao) {
        this.dao = dao;
        entityClass = Designation.class;
    }
    
    // Test methods //--------------------------------------------
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all implementations of {@link DaoDesignation} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoDesignationJpa()}
			}
		);
	}
}