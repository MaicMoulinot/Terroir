package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Product;

/**
 * This Class is a Junit test case testing the contract of {@link DaoProduct}.
 * It extends {@link TestDao} with the parameter {@link Product}, and implements <code>testEntityClassMatch()</code>.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoProduct}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoProduct extends TestDao<Product> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link ProductDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link DaoProduct}.
	 */
    public TestDaoProduct(DaoProduct dao) {
        this.dao = dao;
        entityClass = Product.class;
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all {@link DaoProduct}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoProductJpa()}
			}
		);
	}
}