package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.Enterprise;
import com.jomm.terroir.business.Seller;
import com.jomm.terroir.business.UserService;
import com.jomm.terroir.util.Message;

/**
 * This Class is the View linked to sellersignup.xhtml, that creates a new {@link Seller}.
 * It extends {@link UserJsf} and defines an additional attribute {@link Enterprise}.
 * It relates to {@link ResourceBundle} to generate proper {@link Message} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link UserService} to save the {@link Seller}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class SellerJsf extends UserJsf {
	
	// Injected fields
	@Inject
	private UserService userService;
	@Inject
	private FacesContext facesContext;
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Constants
	private static final String USER_REGISTRED = "usersaved";

	//	Attributes
	private Enterprise enterprise;

	@Override
	public String create() {
		userService.create(convertIntoEntity());
		FacesMessage message = new FacesMessage(resource.getString(USER_REGISTRED), null);
		facesContext.addMessage(null, message);
		return "sellerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Transform an {@link SellerJsf} into {@link Seller}.
	 * @return Seller.
	 */
	public Seller convertIntoEntity() {
		Seller userEntity = new Seller();
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
	 * Transform an {@link Seller} into {@link SellerJsf}.
	 * @param customer a {@link Seller}.
	 * @return {@link SellerJsf}.
	 */
	public static SellerJsf convertIntoView(Seller seller) {
		SellerJsf sellerJsf = new SellerJsf();
		sellerJsf.setId(seller.getId());
		sellerJsf.setFirstName(seller.getFirstName());
		sellerJsf.setLastName(seller.getLastName());
		sellerJsf.setUserName(seller.getUserName());
		sellerJsf.setEmail(seller.getEmail());
		sellerJsf.setPassword(seller.getUserPassword());
		sellerJsf.setEnterprise(seller.getEnterprise());
		return sellerJsf;
	}

	/**
	 * @return the enterprise
	 */
	public Enterprise getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}