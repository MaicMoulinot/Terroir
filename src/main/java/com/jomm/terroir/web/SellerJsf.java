package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.EnterpriseEntity;
import com.jomm.terroir.business.SellerEntity;
import com.jomm.terroir.business.UserServiceInterface;
import com.jomm.terroir.util.Message;

@ManagedBean
@ViewScoped
public class SellerJsf extends UserJsf {
	
	// Injected fields
	@Inject
	private UserServiceInterface userService;
	@Inject
	private FacesContext facesContext;
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Constants
	private static final String USER_REGISTRED = "usersaved";

	//	Attributes
	private EnterpriseEntity enterprise;

	@Override
	public String create() {
		userService.persistUser(convertIntoEntity());
		FacesMessage message = new FacesMessage(resource.getString(USER_REGISTRED), null);
		facesContext.addMessage(null, message);
		return "sellerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Transform an {@link SellerJsf} into {@link SellerEntity}.
	 * @return SellerEntity.
	 */
	public SellerEntity convertIntoEntity() {
		SellerEntity userEntity = new SellerEntity();
		userEntity.setId(getId());
		userEntity.setFirstName(getFirstName());
		userEntity.setLastName(getLastName());
		userEntity.setUserName(getUserName());
		userEntity.setEmail(getEmail());
		userEntity.setUserPassword(getPassword());
		userEntity.setEnterprise(getEnterprise());
		return userEntity;
	}

	/**
	 * @return the enterprise
	 */
	public EnterpriseEntity getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(EnterpriseEntity enterprise) {
		this.enterprise = enterprise;
	}
}