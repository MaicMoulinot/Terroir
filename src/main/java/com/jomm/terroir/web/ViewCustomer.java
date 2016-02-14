package com.jomm.terroir.web;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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

	// Injected fields
	@Inject
	private ServiceUser userService;	
	@Inject
	private FacesContext facesContext;	
	@Inject
	@BundleMessage
	private ResourceBundle resource;
	
	// Constants
	private static final String USER_REGISTRED = "usersaved";

	//	Attributes
	private LocalDate birthDate;
	private ZonedDateTime signUpDate;

	@Override
	public String create() {
		setSignUpDate(ZonedDateTime.now());
		userService.create(convertIntoEntity());
		FacesMessage message = new FacesMessage(resource.getString(USER_REGISTRED), null);
		facesContext.addMessage(null, message);
		return "customerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Generate the year's range for birth date calendar.
	 * @return "minYear:maxYear"
	 */
	public String generateYearRange() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
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
		userEntity.setUserPassword(getPassword());
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
		customerJsf.setPassword(customer.getUserPassword());
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