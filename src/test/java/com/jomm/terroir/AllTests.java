package com.jomm.terroir;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jomm.terroir.business.AbstractUserTest;
import com.jomm.terroir.business.AddressTest;
import com.jomm.terroir.business.AdminTest;
import com.jomm.terroir.business.CustomerTest;
import com.jomm.terroir.business.EnterpriseServiceImplTest;
import com.jomm.terroir.business.EnterpriseServiceTest;
import com.jomm.terroir.business.EnterpriseTest;
import com.jomm.terroir.business.ImageTest;
import com.jomm.terroir.business.ProductServiceImplTest;
import com.jomm.terroir.business.ProductServiceTest;
import com.jomm.terroir.business.ProductTest;
import com.jomm.terroir.business.SellerTest;
import com.jomm.terroir.business.SiteServiceImplTest;
import com.jomm.terroir.business.SiteServiceTest;
import com.jomm.terroir.business.SiteTest;
import com.jomm.terroir.business.UserServiceImplTest;
import com.jomm.terroir.business.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({AbstractUserTest.class, AddressTest.class, AdminTest.class, CustomerTest.class, 
	EnterpriseTest.class, EnterpriseServiceTest.class, EnterpriseServiceImplTest.class, ImageTest.class, 
	ProductTest.class, ProductServiceTest.class, ProductServiceImplTest.class, SellerTest.class, 
	SiteTest.class, SiteServiceTest.class, SiteServiceImplTest.class, UserServiceTest.class, UserServiceImplTest.class
	})
public class AllTests {

}
