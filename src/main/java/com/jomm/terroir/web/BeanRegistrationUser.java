package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is used to register a new {@link AbstractUser}.
 * It extends {@link BackingBean} and defines common attributes shared among its concrete children.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceUser} to save the {@link AbstractUser},
 * and to {@link Logger} to generate proper logging messages.
 * @author Maic
 */
public abstract class BeanRegistrationUser extends BackingBean {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
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
	 * Generate an {@link AbstractUser} using values from the {@link BeanRegistrationUser}.
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
			Object[] argument = {entity.getUserName()};
			String detail = MessageFormat.format(getValueFromResourceBundle(CREATE_USER), argument);
			addMessage(getValueFromResourceBundle(CREATE_OK), detail);
		} catch (ExceptionService exception) {
			String problem = generateExceptionMessage(exception, entity);
			addMessageException(problem);
			logger.log(Level.FINE, problem, exception);
		}
		return null;
	}

	/**
	 * Generate tips to create a secured enough password into growl.
	 */
	public void passwordTooltip() {
		addMessage(CLIENT_ID_GROWL.getId(), getValueFromResourceBundle(PASSWORD_TITLE), 
				getValueFromResourceBundle(PASSWORD_RULES));
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