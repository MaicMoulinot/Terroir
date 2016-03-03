package com.jomm.terroir.web;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

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
	private Logger logger;
	
	//	Attributes
	private Enterprise enterprise;

	@Override
	public String create() {
		FacesMessage message = null;
		try {
			userService.create(convertIntoEntity());
			message = new FacesMessage(resource.getString(Constants.USER_REGISTRED), null);
		} catch (ExceptionNullEntity | ExceptionInvalidId exception) {
			String problem = exception.getMessage();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, null);
			logger.log(Level.FINE, problem, exception);
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
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @return the userService
	 */
	ServiceUser getUserService() {
		return userService;
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param logger the logger to set
	 */
	void setLogger(Logger logger) {
		this.logger = logger;
	}
}