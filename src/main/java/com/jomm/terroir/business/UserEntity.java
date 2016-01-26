package com.jomm.terroir.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="users")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM users u")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserEntity {
	
	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	@XmlAttribute(required = true)
	private long userId;
	
	@Column(name = "first_name")
	@XmlAttribute
	private String firstName;
	
	@XmlAttribute
	@Column(name = "last_name")
	private String lastName;
	
	@Column(unique = true, name = "user_name")
	@NotNull
	@XmlAttribute(required = true)
	private String userName;
	
	@Column(unique=true)
	@NotNull
	@XmlAttribute(required = true)
	private String email;
	
	@Column(name = "user_password")
	@NotNull
	@XmlAttribute(required = true)
	private String userPassword;
	
	@Temporal(TemporalType.DATE)
	@Past(message = "date doit etre passée")
	@Column(name = "birth_date")
	@XmlAttribute
	private Date birthDate;
	
	@Column(name = "is_admin")
	@XmlAttribute
	private boolean isAdmin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "signup_date")
	@XmlAttribute
	private Date signUpDate;
	
	@Embedded
	private AddressEmbeddable address;
	
	// Getters and Setters
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
	/**
	 * @return the address
	 */
	public AddressEmbeddable getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressEmbeddable address) {
		this.address = address;
	}
}
