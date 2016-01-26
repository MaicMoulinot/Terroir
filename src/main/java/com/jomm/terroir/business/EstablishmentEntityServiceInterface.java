package com.jomm.terroir.business;

import java.util.ArrayList;


public interface EstablishmentEntityServiceInterface {
	
	/**
	 * Save an establishment.
	 * @param establishment EstablishmentEntity the establishment to save.
	 * @see com.jomm.terroir.business.EstablishmentEntity
	 */
	public void persistEstablishment(EstablishmentEntity establishment);
	
	/**
	 * Fetch the list of all establishments.
	 * @return a list of all establishments.
	 * @see com.jomm.terroir.business.EstablishmentEntity
	 */
	public ArrayList<EstablishmentEntity> getAllEstablishments();
	
	/**
	 * Delete an establishment.
	 * @param establishment EstablishmentEntity.
	 * @see com.jomm.terroir.business.EstablishmentEntity
	 */
	public void deleteEstablishment(EstablishmentEntity establishment);
}
