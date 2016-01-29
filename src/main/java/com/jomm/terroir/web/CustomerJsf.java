package com.jomm.terroir.web;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.CustomerEntity;
import com.jomm.terroir.business.UserService;
import com.jomm.terroir.util.Message;

/**
 * This Class is the View linked to customersignup.xhtml, that creates a new {@link CustomerEntity}.
 * It extends {@link UserJsf} and defines customer specific attributes.
 * It relates to {@link ResourceBundle} to generate proper {@link Message} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link UserService} to save the {@link CustomerEntity}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class CustomerJsf extends UserJsf {

	// Injected fields
	@Inject
	private UserService userService;	
	@Inject
	private FacesContext facesContext;	
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Constants
	private static final String USER_REGISTRED = "usersaved";

	//	Attributes
	private Date birthDate;
	private Date signUpDate;

	@Override
	public String create() {
		setSignUpDate(new Date());
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
	 * Transform an {@link CustomerJsf} into {@link CustomerEntity}.
	 * @return CustomerEntity.
	 */
	public CustomerEntity convertIntoEntity() {
		CustomerEntity userEntity = new CustomerEntity();
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
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the signUpDate
	 */
	public Date getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate the signUpDate to set
	 */
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
}