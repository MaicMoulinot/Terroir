package com.jomm.terroir.business;

import java.util.ArrayList;


public interface EnterpriseEntityServiceInterface {
	
	/**
	 * Save an enterprise.
	 * @param enterprise EnterpriseEntity the enterprise to save.
	 * @see com.jomm.terroir.business.EnterpriseEntity
	 */
	public void persistEnterprise(EnterpriseEntity enterprise);
	
	/**
	 * Fetch the list of all enterprises.
	 * @return a list of all enterprises.
	 * @see com.jomm.terroir.business.EnterpriseEntity
	 */
	public ArrayList<EnterpriseEntity> getAllEnterprises();
	
	/**
	 * Delete an enterprise.
	 * @param enterprise EnterpriseEntity.
	 * @see com.jomm.terroir.business.EnterpriseEntity
	 */
	public void deleteEnterprise(EnterpriseEntity enterprise);
}
