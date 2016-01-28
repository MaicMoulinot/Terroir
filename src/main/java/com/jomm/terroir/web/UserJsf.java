package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.jomm.terroir.util.Message;

@ManagedBean
@ViewScoped
public abstract class UserJsf {
	
	// Injected fields
	@Inject
	private FacesContext facesContext;
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Constants
	private static final String PASSWORD_TITLE = "passwordtitle";
	private static final String PASSWORD_RULES = "passwordrules";

	//	Attributes
	protected long id;
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
		FacesMessage message = new FacesMessage(resource.getString(PASSWORD_TITLE), resource.getString(PASSWORD_RULES));
		facesContext.addMessage("growl", message);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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