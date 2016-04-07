package com.jomm.terroir.web;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.Constants.Entity;

/**
 * This Class is the Bean linked to {@code registrationseller.xhtml}, used to register a new {@link Seller}.
 * It extends {@link BackingRegistrationUser} and defines an additional attribute {@link Enterprise}.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BackingRegistrationSeller extends BackingRegistrationUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Variables //-----------------------------------------------
	private Enterprise enterprise;
	
	// Methods //-------------------------------------------------
	@Override
	@PostConstruct 
	public void init() {
		setEnterprise(new Enterprise());
	}
	
	/**
	 * Create and save a new Seller.
	 * @return String for navigation.
	 */
	@Override
	public String create() {
		super.create();
		return "listseller" + "?faces-redirect=true";	// Navigation case.
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Transform an {@link BackingRegistrationSeller} into {@link Seller}.
	 * @return Seller.
	 */
	@Override
	protected Seller convertIntoEntity() {
		Seller userEntity = new Seller();
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setPassword(getPassword() != null ? getPassword().toCharArray() : null);
		userEntity.setEnterprise(getEnterprise());
		return userEntity;
	}
	
	@Override
	protected Entity getConstantsEntity() {
		return Entity.SELLER;
	}
	
	// Getters and Setters //-------------------------------------
	/**
	 * @return the enterprise
	 */
	public Enterprise getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}