package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.USER_REGISTRED;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public abstract class ViewUser {
	
	// Injected fields
	@Inject
	protected ServiceUser userService;
	@Inject
	protected FacesContext facesContext;
	@Inject
	@BundleMessage
	protected ResourceBundle resource;
	@Inject
	protected Logger logger;

	//	Attributes
	protected Long id;
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String email;
	protected char[] password;
	
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
		FacesMessage message = null;
		try {
			userService.create(convertIntoEntity());
			message = new FacesMessage(resource.getString(USER_REGISTRED.getKey()), null);
		} catch (ExceptionService exception) {
			String problem = exception.getMessage();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, null);
			logger.log(Level.FINE, problem, exception);
		} finally {
			facesContext.addMessage(null, message);
		}
		return null;
	}

	/**
	 * Generate tips to create a secured enough password into growl.
	 */
	public void passwordTooltip() {
		FacesMessage message = new FacesMessage(
				resource.getString(PASSWORD_TITLE.getKey()), 
				resource.getString(PASSWORD_RULES.getKey()));
		facesContext.addMessage(CLIENT_ID_GROWL.getId(), message);
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
	public char[] getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}
}