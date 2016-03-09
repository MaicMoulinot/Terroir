package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.USER_REGISTRED;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is the View that creates a new {@link com.jomm.terroir.business.model.AbstractUser}.
 * It defines common attributes shared among its children.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * and to {@link FacesContext} to throw them to the view.
 * @author Maic
 */
public abstract class ViewUser extends AbstractView {
	
	// Injected fields
	@Inject
	protected ServiceUser userService;
	@Inject
	protected Logger logger;

	//	Attributes
	protected Long id;
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String email;
	protected String password;
	
	/**
	 * Transform an {@link ViewUser} into {@link AbstractUser}.
	 * @return {@link AbstractUser}.
	 */
	public abstract AbstractUser convertIntoEntity();

	/**
	 * Create and save a new User.
	 * @return String for navigation.
	 */
	public String create() {
		AbstractUser entity = convertIntoEntity();
		try {
			userService.create(entity);
			addMessage(resourceBundleMessage.getString(USER_REGISTRED.getKey()));
		} catch (ExceptionService exception) {
			String problem = generateExceptionMessage(exception, entity.getId(), entity);
			addMessage(FacesMessage.SEVERITY_ERROR, problem, null);
			logger.log(Level.FINE, problem, exception);
		}
		return null;
	}

	/**
	 * Generate tips to create a secured enough password into growl.
	 */
	public void passwordTooltip() {
		addMessage(CLIENT_ID_GROWL.getId(), resourceBundleMessage.getString(PASSWORD_TITLE.getKey()), 
				resourceBundleMessage.getString(PASSWORD_RULES.getKey()));
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}