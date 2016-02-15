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

@RunWith(Suite.class)
@SuiteClasses({TestAbstractUser.class, TestAddress.class, TestAdmin.class, TestCustomer.class, 
	TestEnterprise.class, TestServiceEnterprise.class, TestServiceEnterpriseImpl.class, TestImage.class, 
	TestProduct.class, TestServiceProduct.class, TestServiceProductImpl.class, TestSeller.class, 
	TestSite.class, TestServiceSite.class, TestServiceSiteImpl.class, TestServiceUser.class, TestServiceUserImpl.class,
	TestValidatorEmail.class, TestValidatorPassword.class, TestValidatorUsername.class, 
	TestDaoAdminJpa.class, TestDaoAdmin.class, TestDaoCustomerJpa.class, TestDaoCustomer.class,
	TestDaoEnterpriseJpa.class, TestDaoEnterprise.class, TestDaoProductJpa.class, TestDaoProduct.class, 
	TestDaoSiteJpa.class, TestDaoSite.class, TestDaoSellerJpa.class, TestDaoSeller.class,
	TestDaoUserJpa.class, TestDaoUser.class
	})
public class AllTests {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		UtilEntityManager.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UtilEntityManager.tearDown();
	}
}