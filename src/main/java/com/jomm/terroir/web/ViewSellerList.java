package com.jomm.terroir.web;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This Class is the View linked to sellerlist.xhtml, that displays the list of {@link ViewSeller}.
 * It extends {@link ViewUserList} and defines specific attributes.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to update or delete the {@link Seller}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewSellerList extends ViewUserList {

	// Injected fields
	@Inject
	private Logger logger;
	
	// Attributes
	private List<ViewSeller> listSellers;
	private ViewSeller currentSeller;

	@Override
	@PostConstruct 
	public void init() {
		listSellers = new LinkedList<>();
		for (Seller seller : userService.getAllSellers()) {
			listSellers.add(ViewSeller.convertIntoView(seller));
		}
	}

	@Override
	public void onRowEdit(RowEditEvent event) {
		ViewSeller sellerJsf = (ViewSeller) event.getObject();
		if (sellerJsf != null) {
			FacesMessage message = null;
			try {
				userService.update(sellerJsf.convertIntoEntity());
				Object[] argument = {sellerJsf.getUserName()};
				String detail = MessageFormat.format(resource.getString(Constants.UPDATE_USER), argument);
				message = new FacesMessage(resource.getString(Constants.UPDATE_OK), detail);
			} catch (ExceptionNullEntity | ExceptionInvalidId exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + sellerJsf.getUserName() + ", UserId=" + sellerJsf.getId());
				logger.log(Level.FINE, problem, exception);
			} finally {
				facesContext.addMessage(null, message);
			}
		}
	}

	@Override
	public void onRowCancel(RowEditEvent event) {
		// Do nothing.
	}

	@Override
	public String delete() {
		if (currentSeller != null) {
			Seller seller = currentSeller.convertIntoEntity();
			FacesMessage message = null;
			try {
				Object[] argument = {seller.getUserName()};
				userService.delete(seller);
				String detail = MessageFormat.format(resource.getString(Constants.DELETE_USER), argument);
				message = new FacesMessage(resource.getString(Constants.DELETE_OK), detail);
			} catch (ExceptionNullEntity | ExceptionInvalidId exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + seller.getUserName() + ", UserId=" + seller.getId());
				logger.log(Level.FINE, problem, exception);
			} finally {
				facesContext.addMessage(null, message);
			}
		}
		return "sellerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * @return the listSellers
	 */
	public List<ViewSeller> getListSellers() {
		return listSellers;	// Return the already-prepared model.
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(List<ViewSeller> listSellers) {
		this.listSellers = listSellers;
	}

	/**
	 * @return the currentSeller
	 */
	public ViewSeller getCurrentSeller() {
		return currentSeller;
	}

	/**
	 * @param currentSeller the currentSeller to set
	 */
	public void setCurrentSeller(ViewSeller currentSeller) {
		this.currentSeller = currentSeller;
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param logger the logger to set
	 */
	void setLogger(Logger logger) {
		this.logger = logger;
	}
}