package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Customer;

/**
 * This Class is a Junit test case testing the contract of {@link DaoCustomer}.
 * It extends {@link TestDao} with the parameter {@link Customer}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoCustomer}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoCustomer extends TestDao<Customer> {
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestDaoCustomer#implementationToTest()}.
	 * @param dao the implementation of {@link DaoCustomer}.
	 */
    public TestDaoCustomer(DaoCustomer dao) {
        this.dao = dao;
        entityClass = Customer.class;
    }
    
    // Test methods //--------------------------------------------
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all implementations of {@link DaoCustomer} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoCustomerJpa()}
			}
		);
	}
}