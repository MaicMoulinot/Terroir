package com.jomm.terroir.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is an abstract {@link Entity} representing an user.
 * It uses {@link ServiceUser} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes all common attributes shared among its child classes.
 * As the {@link Inheritance} strategy is TABLE_PER_CLASS, its properties are persisted in each concrete child's table.
 * @author Maic
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="AbstractUser.findAll", query="SELECT u FROM AbstractUser u")
public abstract class AbstractUser implements Serializable {

	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -7643819675779152993L;

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true, name = "user_name")
	@NotNull
	private String userName;

	@Column(unique=true)
	@NotNull
	private String email;

	@Column(name = "user_password")
	@NotNull
	private String userPassword;

	// Getters and Setters
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
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * @param userPassword the password to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
