package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Category;

/**
 * This Class is a Junit test case testing the contract of {@link DaoCategory}.
 * It extends {@link TestDao} with the parameter {@link Category}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoCategory}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoCategory extends TestDao<Category> {
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link CategoryDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link DaoCategory}.
	 */
    public TestDaoCategory(DaoCategory dao) {
        this.dao = dao;
        entityClass = Category.class;
    }
    
    // Test methods //--------------------------------------------
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all implementations of {@link DaoCategory} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoCategoryJpa()}
			}
		);
	}
}