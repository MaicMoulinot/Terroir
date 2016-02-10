package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.ProductTest;

/**
 * This Class is a Junit test case testing the contract of {@link ProductDao}.
 * It extends {@link DaoTest} with the parameter {@link Product}, and implements <code>testEntityClass()</code>.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ProductDao}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class ProductDaoTest extends DaoTest<Product> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link ProductDaooTest#implementationToTest()}.
	 * @param dao the implementation of {@link ProductDao}.
	 */
    public ProductDaoTest(ProductDao dao) {
        this.dao = dao;
        this.entity = ProductTest.generateProduct();
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all {@link ProductDao}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new ProductDaoJpa()}
			}
		);
	}
}