package com.jomm.terroir.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * This Class is an abstract {@link Entity} representing an user.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceUser} for all its business operations.
 * It includes all common attributes shared among its child classes.
 * As the {@link Inheritance} strategy is {@link InheritanceType#TABLE_PER_CLASS}, 
 * its properties are persisted in each concrete child's table.
 * @author Maic
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="AbstractUser.findAll", query="SELECT u FROM AbstractUser u")
public abstract class AbstractUser extends AbstractEntity {

	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The user's unique identifier in the system. */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "user_id")
	private Long id;
	
	/** The user's first name. */
	@Column(name = "user_firstname")
	private String firstName;
	
	/** The user's last name. */
	@Column(name = "user_lastname")
	private String lastName;
	
	/** The user's unique name in the system. */
	@Column(name = "user_name", unique = true)
	@NotNull
	private String userName;
	
	/** The user's email address. */
	@Column(name = "user_email", unique=true)
	@NotNull
	private String email;
	
	/** The user's password to log in the system. */
	@Column(name = "user_password")
	@NotNull
	private char[] password;

	// Getters and Setters //-------------------------------------
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
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