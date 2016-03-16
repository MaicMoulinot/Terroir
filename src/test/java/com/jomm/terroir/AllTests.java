package com.jomm.terroir;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jomm.terroir.business.TestServiceEnterprise;
import com.jomm.terroir.business.TestServiceEnterpriseImpl;
import com.jomm.terroir.business.TestServiceProduct;
import com.jomm.terroir.business.TestServiceProductImpl;
import com.jomm.terroir.business.TestServiceSite;
import com.jomm.terroir.business.TestServiceSiteImpl;
import com.jomm.terroir.business.TestServiceUser;
import com.jomm.terroir.business.TestServiceUserImpl;
import com.jomm.terroir.business.model.TestAbstractEntity;
import com.jomm.terroir.business.model.TestAbstractUser;
import com.jomm.terroir.business.model.TestAddress;
import com.jomm.terroir.business.model.TestAdmin;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.business.model.TestImage;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.business.model.TestSeller;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.business.validator.TestValidatorEmail;
import com.jomm.terroir.business.validator.TestValidatorPassword;
import com.jomm.terroir.business.validator.TestValidatorUsername;
import com.jomm.terroir.dao.TestDaoAdmin;
import com.jomm.terroir.dao.TestDaoAdminJpa;
import com.jomm.terroir.dao.TestDaoCustomer;
import com.jomm.terroir.dao.TestDaoCustomerJpa;
import com.jomm.terroir.dao.TestDaoEnterprise;
import com.jomm.terroir.dao.TestDaoEnterpriseJpa;
import com.jomm.terroir.dao.TestDaoProduct;
import com.jomm.terroir.dao.TestDaoProductJpa;
import com.jomm.terroir.dao.TestDaoSeller;
import com.jomm.terroir.dao.TestDaoSellerJpa;
import com.jomm.terroir.dao.TestDaoSite;
import com.jomm.terroir.dao.TestDaoSiteJpa;
import com.jomm.terroir.dao.TestDaoUser;
import com.jomm.terroir.dao.TestDaoUserJpa;
import com.jomm.terroir.dao.UtilEntityManager;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.converter.TestAttributeConverterLocalDate;
import com.jomm.terroir.util.converter.TestAttributeConverterZonedDateTime;
import com.jomm.terroir.util.converter.TestConverterLocalDate;
import com.jomm.terroir.util.converter.TestConverterZonedDateTime;
import com.jomm.terroir.util.exception.TestExceptionService;
import com.jomm.terroir.web.TestBackingBean;
import com.jomm.terroir.web.TestBeanRegistrationCustomer;
import com.jomm.terroir.web.TestBeanListCustomer;
import com.jomm.terroir.web.TestBeanRegistrationSeller;
import com.jomm.terroir.web.TestBeanListSeller;
import com.jomm.terroir.web.TestBeanRegistrationUser;
import com.jomm.terroir.web.TestBeanListUser;

/**
 * This Class is a Junit Suite Case launching all Junit test cases.
 * It is annotated {@link RunWith} {@link Suite} to allow the launch of multiple tests.
 * It is annotated {@link SuiteClasses} to explicit each test case being run.
 * @author Maic
 */
@RunWith(Suite.class)
@SuiteClasses({
	// Model
	TestAbstractEntity.class, TestAbstractUser.class, TestAddress.class, TestAdmin.class, TestCustomer.class, 
	TestEnterprise.class, TestImage.class, TestProduct.class, TestSeller.class, TestSite.class, 
	// Service
	TestServiceEnterprise.class, TestServiceEnterpriseImpl.class, 
	TestServiceProduct.class, TestServiceProductImpl.class, 
	TestServiceSite.class, TestServiceSiteImpl.class, 
	TestServiceUser.class, TestServiceUserImpl.class,
	// Validator
	TestValidatorEmail.class, TestValidatorPassword.class, TestValidatorUsername.class, 
	// DAO
	TestDaoAdminJpa.class, TestDaoAdmin.class, TestDaoCustomerJpa.class, TestDaoCustomer.class,
	TestDaoEnterpriseJpa.class, TestDaoEnterprise.class, TestDaoProductJpa.class, TestDaoProduct.class, 
	TestDaoSiteJpa.class, TestDaoSite.class, TestDaoSellerJpa.class, TestDaoSeller.class,
	TestDaoUserJpa.class, TestDaoUser.class,
	// Util
	TestResources.class,
	// Converter
	TestAttributeConverterLocalDate.class, TestAttributeConverterZonedDateTime.class, 
	TestConverterLocalDate.class, TestConverterZonedDateTime.class, 
	// Exception
	TestExceptionService.class, 
	// Web
	TestBackingBean.class, 
	TestBeanRegistrationCustomer.class, TestBeanListCustomer.class, 
	TestBeanRegistrationSeller.class, TestBeanListSeller.class,
	TestBeanRegistrationUser.class, TestBeanListUser.class
	})
public class AllTests {
	
	/**
	 * Set the environment necessary for testing.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		UtilEntityManager.setUp();
	}

	/**
	 * Clean the environment after all tests have been run.
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UtilEntityManager.tearDown();
	}
}