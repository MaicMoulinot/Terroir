package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.Constants;

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
	FacesContext facesContext;
	@Inject
	@BundleMessage
	ResourceBundle resource;

	//	Attributes
	protected Long id;
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String email;
	protected String password;

	/**
	 * Create and save a new User.
	 * @return a String (navigation).
	 */
	public abstract String create();

	/**
	 * Generate tips to create a secured enough password into growl.
	 * @param actionEvent the ActionEvent invoking the tips.
	 */
	public void passwordTooltip(ActionEvent actionEvent) {
		FacesMessage message = new FacesMessage(
				resource.getString(Constants.PASSWORD_TITLE), 
				resource.getString(Constants.PASSWORD_RULES));
		facesContext.addMessage(Constants.CLIENT_ID_GROWL, message);
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