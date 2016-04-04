package com.jomm.terroir;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jomm.terroir.business.TestServiceDesignation;
import com.jomm.terroir.business.TestServiceDesignationImpl;
import com.jomm.terroir.business.TestServiceEnterprise;
import com.jomm.terroir.business.TestServiceEnterpriseImpl;
import com.jomm.terroir.business.TestServiceLabel;
import com.jomm.terroir.business.TestServiceLabelImpl;
import com.jomm.terroir.business.TestServiceProduct;
import com.jomm.terroir.business.TestServiceProductImpl;
import com.jomm.terroir.business.TestServiceSite;
import com.jomm.terroir.business.TestServiceSiteImpl;
import com.jomm.terroir.business.TestServiceUser;
import com.jomm.terroir.business.TestServiceUserImpl;
import com.jomm.terroir.business.model.TestAbstractEntity;
import com.jomm.terroir.business.model.TestAbstractUser;
import com.jomm.terroir.business.model.TestAddress;
import com.jomm.terroir.business.model.TestAdministrator;
import com.jomm.terroir.business.model.TestCategory;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.business.model.TestImage;
import com.jomm.terroir.business.model.TestLabel;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.business.model.TestSeller;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.business.model.TestStock;
import com.jomm.terroir.business.validator.TestValidatorEmail;
import com.jomm.terroir.business.validator.TestValidatorPassword;
import com.jomm.terroir.business.validator.TestValidatorUsername;
import com.jomm.terroir.dao.TestDaoAdministrator;
import com.jomm.terroir.dao.TestDaoAdministratorJpa;
import com.jomm.terroir.dao.TestDaoCategory;
import com.jomm.terroir.dao.TestDaoCategoryJpa;
import com.jomm.terroir.dao.TestDaoCustomer;
import com.jomm.terroir.dao.TestDaoCustomerJpa;
import com.jomm.terroir.dao.TestDaoDesignation;
import com.jomm.terroir.dao.TestDaoDesignationJpa;
import com.jomm.terroir.dao.TestDaoEnterprise;
import com.jomm.terroir.dao.TestDaoEnterpriseJpa;
import com.jomm.terroir.dao.TestDaoImage;
import com.jomm.terroir.dao.TestDaoImageJpa;
import com.jomm.terroir.dao.TestDaoLabel;
import com.jomm.terroir.dao.TestDaoLabelJpa;
import com.jomm.terroir.dao.TestDaoProduct;
import com.jomm.terroir.dao.TestDaoProductJpa;
import com.jomm.terroir.dao.TestDaoSeller;
import com.jomm.terroir.dao.TestDaoSellerJpa;
import com.jomm.terroir.dao.TestDaoSite;
import com.jomm.terroir.dao.TestDaoSiteJpa;
import com.jomm.terroir.dao.TestDaoStock;
import com.jomm.terroir.dao.TestDaoStockJpa;
import com.jomm.terroir.dao.TestDaoUser;
import com.jomm.terroir.dao.TestDaoUserJpa;
import com.jomm.terroir.dao.UtilEntityManager;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.converter.TestAttributeConverterLocalDate;
import com.jomm.terroir.util.converter.TestAttributeConverterUnit;
import com.jomm.terroir.util.converter.TestAttributeConverterZonedDateTime;
import com.jomm.terroir.util.converter.TestConverterLocalDate;
import com.jomm.terroir.util.converter.TestConverterZonedDateTime;
import com.jomm.terroir.util.exception.TestExceptionService;
import com.jomm.terroir.web.TestBackingBean;
import com.jomm.terroir.web.TestBeanListCustomer;
import com.jomm.terroir.web.TestBeanListSeller;
import com.jomm.terroir.web.TestBeanListUser;
import com.jomm.terroir.web.TestBeanRegistrationCustomer;
import com.jomm.terroir.web.TestBeanRegistrationSeller;
import com.jomm.terroir.web.TestBeanRegistrationUser;

/**
 * This Class is a Junit Suite Case launching all Junit test cases.
 * It is annotated {@link RunWith} {@link Suite} to allow the launch of multiple tests.
 * It is annotated {@link SuiteClasses} to explicit each test case being run.
 * @author Maic
 */
@RunWith(Suite.class)
@SuiteClasses({
	// Model
	TestAbstractEntity.class, TestAbstractUser.class, TestAddress.class, TestAdministrator.class, TestCategory.class, 
	TestCustomer.class, TestDesignation.class, TestEnterprise.class, TestImage.class, TestLabel.class, 
	TestProduct.class, TestSeller.class, TestSite.class, TestStock.class, 
	// Service
	TestServiceDesignation.class, TestServiceDesignationImpl.class, 
	TestServiceEnterprise.class, TestServiceEnterpriseImpl.class, 
	TestServiceLabel.class, TestServiceLabelImpl.class, 
	TestServiceProduct.class, TestServiceProductImpl.class, 
	TestServiceSite.class, TestServiceSiteImpl.class, 
	TestServiceUser.class, TestServiceUserImpl.class,
	// Validator
	TestValidatorEmail.class, TestValidatorPassword.class, TestValidatorUsername.class, 
	// DAO
	TestDaoAdministrator.class, TestDaoAdministratorJpa.class, TestDaoCategory.class, TestDaoCategoryJpa.class, 
	TestDaoCustomer.class, TestDaoCustomerJpa.class, TestDaoDesignation.class, TestDaoDesignationJpa.class, 
	TestDaoEnterprise.class, TestDaoEnterpriseJpa.class, TestDaoImage.class, TestDaoImageJpa.class, 
	TestDaoLabel.class, TestDaoLabelJpa.class, TestDaoProduct.class, TestDaoProductJpa.class, 
	TestDaoSeller.class, TestDaoSellerJpa.class, TestDaoSite.class, TestDaoSiteJpa.class, 
	TestDaoStockJpa.class, TestDaoStock.class, TestDaoUserJpa.class, TestDaoUser.class, 
	// Util
	TestResources.class,
	// Converter
	TestAttributeConverterLocalDate.class, TestAttributeConverterUnit.class, 
	TestAttributeConverterZonedDateTime.class, 
	TestConverterLocalDate.class, TestConverterZonedDateTime.class, 
	// Exception
	TestExceptionService.class, 
	// Web
	TestBackingBean.class, 
	TestBeanListCustomer.class, TestBeanListSeller.class, TestBeanListUser.class, 
	TestBeanRegistrationCustomer.class, TestBeanRegistrationSeller.class, TestBeanRegistrationUser.class
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