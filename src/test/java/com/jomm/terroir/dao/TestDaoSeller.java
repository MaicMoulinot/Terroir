package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Seller;

/**
 * This Class is a Junit test case testing the contract of {@link DaoSeller}.
 * It extends {@link TestDao} with the parameter {@link Seller}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoSeller}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoSeller extends TestDao<Seller> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestDaoSeller#implementationToTest()}.
	 * @param dao the implementation of {@link DaoSeller}.
	 */
    public TestDaoSeller(DaoSeller dao) {
        this.dao = dao;
        entityClass = Seller.class;
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all implementations of {@link DaoSeller} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoSellerJpa()}
			}
		);
	}
}