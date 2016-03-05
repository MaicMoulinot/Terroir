package com.jomm.terroir.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Enterprise;

/**
 * This Class is a Junit test case testing the contract of {@link DaoEnterprise}.
 * It extends {@link TestDao} with the parameter {@link Enterprise}, and implements {@code testEntityClassMatch()}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link DaoEnterprise}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestDaoEnterprise extends TestDao<Enterprise> {
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link EnterpriseDaoTest#implementationToTest()}.
	 * @param dao the implementation of {@link DaoEnterprise}.
	 */
    public TestDaoEnterprise(DaoEnterprise dao) {
        this.dao = dao;
        entityClass = Enterprise.class;
    }
    
	@Override
	@Test
	public final void testEntityClassMatch() {
		super.testEntityClassMatch();
	}
    
	/**
	 * Reference a list of all implementations of {@link DaoEnterprise} to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{new DaoEnterpriseJpa()}
			}
		);
	}
}