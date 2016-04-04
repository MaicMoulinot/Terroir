package com.jomm.terroir.web;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Address;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.Constants.Entity;

/**
 * This Class is the Bean linked to {@code registrationcustomer.xhtml}, used to register a new {@link Customer}.
 * It extends {@link BackingRegistrationUser} and defines customer specific attributes.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BackingRegistrationCustomer extends BackingRegistrationUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Variables //-----------------------------------------------
	private LocalDate birthDate;
	private Address address;
    
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes, such as the {@link Address}.
	 * It replaces the constructor and it is annotated {@link PostConstruct},
	 * for proper call from the bean management framework which uses proxies, such as CDI.
	 */
	@PostConstruct 
	public void init() {
		setAddress(new Address());
	}
	
	@Override
	public Entity getConstantsEntity() {
		return Entity.CUSTOMER;
	}
	
	@Override
	public String create() {
		super.create();
		return "listcustomer" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Generate the year's range for birth date calendar.
	 * @return "minYear:maxYear"
	 */
	public String generateYearRange() {
		int currentYear = LocalDate.now().getYear();
		int minYear = currentYear - 97;
		int maxYear = currentYear - 17;
		return minYear + ":" + maxYear;
	}

	@Override
	public Customer convertIntoEntity() {
		Customer userEntity = new Customer();
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setPassword(getPassword() != null ? getPassword().toCharArray() : null);
		userEntity.setBirthDate(getBirthDate());
		userEntity.setAddress(getAddress());
		return userEntity;
	}
	
	// Getters and Setters //-------------------------------------
	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}