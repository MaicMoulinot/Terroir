package com.jomm.terroir.web;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.BundleMessage;

/**
 * This Class is the View linked to customersignup.xhtml, that creates a new {@link Customer}.
 * It extends {@link ViewUser} and defines customer specific attributes.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to save the {@link Customer}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewCustomer extends ViewUser {

	//	Attributes
	private LocalDate birthDate;
	private ZonedDateTime signUpDate;
	
	/**
	 * Create and save a new Customer.
	 * @return String for navigation.
	 */
	public String create() {
		super.create();
		return "customerlist" + "?faces-redirect=true";	// Navigation case.
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

	/**
	 * Transform an {@link ViewCustomer} into {@link Customer}.
	 * @return {@link Customer}.
	 */
	public Customer convertIntoEntity() {
		Customer userEntity = new Customer();
		userEntity.setId(getId());
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setPassword(getPassword() != null ? getPassword().toCharArray() : null);
		userEntity.setBirthDate(getBirthDate());
		userEntity.setSignUpDate(getSignUpDate());
		return userEntity;
	}
	
	/**
	 * Transform a {@link Customer} into a {@link ViewCustomer}.
	 * @param customer a {@link Customer}.
	 * @return {@link ViewCustomer}.
	 */
	public static ViewCustomer convertIntoView(Customer customer) {
		ViewCustomer customerJsf = new ViewCustomer();
		customerJsf.setId(customer.getId());
		customerJsf.setFirstName(customer.getFirstName());
		customerJsf.setLastName(customer.getLastName());
		customerJsf.setUserName(customer.getUserName());
		customerJsf.setEmail(customer.getEmail());
		customerJsf.setPassword(String.valueOf(customer.getPassword()));
		customerJsf.setBirthDate(customer.getBirthDate());
		customerJsf.setSignUpDate(customer.getSignUpDate());
		return customerJsf;
	}

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
	 * @return the signUpDate
	 */
	public ZonedDateTime getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(ZonedDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}
}