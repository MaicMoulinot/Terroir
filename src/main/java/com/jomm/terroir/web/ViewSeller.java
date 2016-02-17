package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This Class is the View linked to sellersignup.xhtml, that creates a new {@link Seller}.
 * It extends {@link ViewUser} and defines an additional attribute {@link Enterprise}.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to save the {@link Seller}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewSeller extends ViewUser {
	
	// Injected fields
	@Inject
	private ServiceUser userService;
	@Inject
	private FacesContext facesContext;
	@Inject
	@BundleMessage
	private ResourceBundle resourceMessage;
	@Inject
	@BundleError
	private ResourceBundle resourceError;
	
	// Constants
	private static final String USER_REGISTRED = "usersaved";
	private static final String USER_NULL = "entitynull";
	private static final String ID_NOT_NULL = "idnotnull";

	//	Attributes
	private Enterprise enterprise;

	@Override
	public String create() {
		FacesMessage message = null;
		try {
			userService.create(convertIntoEntity());
			message = new FacesMessage(resourceMessage.getString(USER_REGISTRED), null);
		} catch (NullPointerException exception) {
			message = new FacesMessage(resourceError.getString(USER_NULL), exception.getMessage());
		} catch (InvalidEntityException exception) {
			message = new FacesMessage(resourceError.getString(ID_NOT_NULL), exception.getMessage());
		} finally {
			facesContext.addMessage(null, message);
		}
		return "sellerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Transform an {@link ViewSeller} into {@link Seller}.
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
	 * Transform a {@link Seller} into a {@link ViewSeller}.
	 * @param seller a {@link Seller}.
	 * @return {@link ViewSeller}.
	 */
	public static ViewSeller convertIntoView(Seller seller) {
		ViewSeller sellerJsf = new ViewSeller();
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