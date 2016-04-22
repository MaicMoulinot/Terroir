package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.View.GROWL;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.Constants.Entity;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is used to register a new {@link AbstractUser}.
 * It extends {@link BackingBean} and defines common attributes shared among its concrete children.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceUser} to save the {@link AbstractUser},
 * and to {@link Logger} to generate proper logging messages.
 * @author Maic
 */
public abstract class BackingRegistrationUser extends BackingBean {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Injected Fields //-----------------------------------------
	@Inject
	protected ServiceUser userService;
	@Inject
	protected Logger logger;
	
	// Variables //-----------------------------------------------
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String email;
	protected String password;
	
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes.
	 * It replaces the constructor and it is annotated {@link javax.annotation.PostConstruct} 
	 * in concrete implementations, for proper call from the bean management framework 
	 * which uses proxies, such as CDI.
	 */
	public abstract void init();
	
	/**
	 * Create and save a new user.
	 * If this method is overridden, the child's method should first call this method using {@code super.create()},
	 * and then it should set a String as proper navigation return.
	 * @return String for navigation.
	 */
	public String create() {
		AbstractUser entity = convertIntoEntity();
		try {
			userService.create(entity);
			addFacesMessageCreate(getConstantsEntity(), entity.getUserName());
		} catch (ExceptionService exception) {
			String problem = generateReadableExceptionMessage(exception, entity);
			addFacesMessageException(problem);
			logger.log(Level.FINE, problem, exception);
		}
		return null;
	}
	
	/** Generate tips to create a secured enough password into growl. */
	public void passwordTooltip() {
		addFacesMessage(GROWL.toString(), FacesMessage.SEVERITY_INFO, getValueFromKey(PASSWORD_TITLE), 
				getValueFromKey(PASSWORD_RULES).replaceAll("-", "<br />-"));
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Generate an {@link AbstractUser} using values from the {@link BackingRegistrationUser}.
	 * @return {@link AbstractUser}.
	 */
	protected abstract AbstractUser convertIntoEntity();
	
	/**
	 * Return the appropriate value from enumeration {@link Entity}.
	 * @return {@link Entity}.
	 */
	protected abstract Entity getConstantsEntity();
	
	// Getters and Setters //-------------------------------------
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