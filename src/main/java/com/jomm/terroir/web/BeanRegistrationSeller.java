package com.jomm.terroir.web;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;

/**
 * This Class is the Bean linked to {@code registrationseller.xhtml}, used to register a new {@link Seller}.
 * It extends {@link BeanRegistrationUser} and defines an additional attribute {@link Enterprise}.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BeanRegistrationSeller extends BeanRegistrationUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Variables //-----------------------------------------------
	private Enterprise enterprise;
	
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes, such as the {@link Enterprise}.
	 * It replaces the constructor and it is annotated {@link PostConstruct},
	 * for proper call from the bean management framework which uses proxies, such as CDI.
	 */
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

	/**
	 * Transform an {@link BeanRegistrationSeller} into {@link Seller}.
	 * @return Seller.
	 */
	@Override
	public Seller convertIntoEntity() {
		Seller userEntity = new Seller();
		userEntity.setId(getId());
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setPassword(getPassword() != null ? getPassword().toCharArray() : null);
		userEntity.setEnterprise(getEnterprise());
		return userEntity;
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