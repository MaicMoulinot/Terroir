package com.jomm.terroir.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@ManagedBean
@ViewScoped
public class UserJsf {

	@Inject
	private UserEntityServiceInterface userService;

	//	Attributes
	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private Date birthDate;
	private boolean admin;
	private Date signUpDate;

	// Ressource bundle.
	private static final String USER_REGISTRED = "usersaved";
	private static final String PASSWORD_TITLE = "passwordtitle";
	private static final String PASSWORD_RULES = "passwordrules";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("i18n.message", Locale.getDefault());

	/**
	 * Create and save a new User.
	 * @return "userlist" (navigation).
	 */
	public String create() {
		setSignUpDate(new Date());
		userService.persistUser(convertIntoEntity());
		FacesMessage message = new FacesMessage(RESOURCE_BUNDLE.getString(USER_REGISTRED), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "userlist" + "?faces-redirect=true";	// Navigation case.
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

	public void passwordTooltip(ActionEvent actionEvent) {
		FacesMessage message = new FacesMessage(RESOURCE_BUNDLE.getString(PASSWORD_TITLE), 
				RESOURCE_BUNDLE.getString(PASSWORD_RULES));
		FacesContext.getCurrentInstance().addMessage("growl", message);
	}

	/**
	 * Transform an {@link UserJsf} into {@link UserEntity}.
	 * @return UserEntity.
	 */
	public UserEntity convertIntoEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(getId());
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setUserPassword(getPassword());
		userEntity.setBirthDate(getBirthDate());
		userEntity.setSignUpDate(getSignUpDate());
		userEntity.setAdmin(isAdmin());
		return userEntity;
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
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
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