package com.jomm.terroir.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@Model
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
	private static final ResourceBundle RESOURCE_LABEL = ResourceBundle.getBundle("i18n.label", Locale.getDefault());
	
	/**
	 * Create and save a new User.
	 * @return "userlist" (navigation).
	 */
    public String create() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		setSignUpDate(now);
		userService.persistUser(convertIntoEntity());
        FacesMessage message = 
        		new FacesMessage(FacesMessage.SEVERITY_INFO, RESOURCE_LABEL.getString(USER_REGISTRED), null);
        FacesContext.getCurrentInstance().addMessage(null, message);
		return "userlist" + "?faces-redirect=true";	// Navigation case.
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