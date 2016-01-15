package com.jomm.terroir.web;

import java.util.ArrayList;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@Model
public class UserJsf2 {

	@Inject
	private UserEntityServiceInterface userService;

	//	Attributes
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private int age;

	// Managed Backing Bean
	private HtmlDataTable dataTable;

	/**
	 * Set the user and the hidden input field.
	 * @return "user" (navigation).
	 */
	public String edit() {
		// Get selected animal to be displayed.
		if (getDataTable() != null) {
			UserEntity userFromList = (UserEntity) getDataTable().getRowData();
			if (userFromList != null) {
				// Set user's properties to be displayed.
				setAge(userFromList.getAge());
				setEmail(userFromList.getEmail());
				setFirstName(userFromList.getFirstName());
				setLastName(userFromList.getLastName());
				setUserName(userFromList.getUserName());
			}
		}
		// Navigation case.
		return "user" + "?faces-redirect=true";
	}

	public String editRow(UserEntity user) {//TODO
		if (user != null) {
			// Set user's properties to be displayed.
			setAge(user.getAge());
			setEmail(user.getEmail());
			setFirstName(user.getFirstName());
			setLastName(user.getLastName());
			setUserName(user.getUserName());
		}
		// Navigation case.
		return null;
	}

	/**
	 * Create and save a new User.
	 * @return "userlist" (navigation).
	 */
	public String create() {
		//userService.saveUser(firstName, lastName, userName, email, age);
		// Navigation case.
		return "userlist" + "?faces-redirect=true";
	}

	/**
	 * Delete an user.
	 * @param user UserEntity the user to be deleted.
	 * @return null (navigation).
	 */
	public String delete(UserEntity user) {
		userService.deleteUser(user);
		// Navigation case.
		return null;
	}

	/**
	 * Fetch all users.
	 * @return a list of all users.
	 */
	public ArrayList<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * @param context a FacesContext.
	 * @param component a UIComponent.
	 * @param value an Object the birthPlace to validate.
	 * @throws ValidatorException
	 */
	public void validateBirthPlace(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String birthPlace = (String) value;
		String errorMessage = null;
		if (birthPlace.length() < 4) {
			errorMessage = "The birth place must be at least 4 characters long.";
		}
		if (errorMessage == null && !birthPlace.startsWith("ZOO_") && !birthPlace.matches("WILD")) {
			errorMessage = "The birth place must be on format 'WILD' or 'ZOO_something'.";
		}
		if (errorMessage != null) {
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(facesMessage);
		}
		//TODO usefull??
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the dataTable
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable the dataTable to set
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

}