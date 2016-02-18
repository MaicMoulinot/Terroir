package com.jomm.terroir.web;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.BundleMessage;

/**
 * This Class is the View linked to sellerlist.xhtml, that displays the list of {@link ViewSeller}.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to update or delete the {@link Seller}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewSellerList {

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
	
	// Attributes
	private LinkedList<ViewSeller> listSellers;
	private ViewSeller currentSeller;
	private HtmlDataTable dataTable;
	
	// Static constants
	private static final String USER_NULL = "entitynull";
	private static final String ID_NULL = "idnull";
	private static final String UPDATE_USER = "updateuser";
	private static final String UPDATE_OK = "updateok";
	private static final String DELETE_USER = "deleteuser";
	private static final String DELETE_OK = "deleteok";

	/**
	 * Initialize the list of all users.
	 */
	@PostConstruct 
	public void init() {
		listSellers = new LinkedList<ViewSeller>();
		for (Seller seller : userService.getAllSellers()) {
			listSellers.add(ViewSeller.convertIntoView(seller));
		}

	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		ViewSeller sellerJsf = (ViewSeller) event.getObject();
		if (sellerJsf != null) {
			FacesMessage message = null;
			try {
				userService.update(sellerJsf.convertIntoEntity());
				Object[] argument = {sellerJsf.getUserName()};
				String detail = MessageFormat.format(resourceMessage.getString(UPDATE_USER), argument);
				message = new FacesMessage(resourceMessage.getString(UPDATE_OK), detail);
			} catch (NullPointerException exception) {
				message = new FacesMessage(resourceError.getString(USER_NULL), exception.getMessage());
			} catch (IllegalStateException exception) {
				message = new FacesMessage(resourceError.getString(ID_NULL), exception.getMessage());
			} finally {
				facesContext.addMessage(null, message);
			}
		}
	}

	/**
	 * Is called when a edited row is back to normal state.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowCancel(RowEditEvent event) {
		// Do nothing.
	}

	/**
	 * Delete a seller.
	 * @return "sellerlist" (navigation).
	 */
	public String delete() {
		if (currentSeller != null) {
			Seller seller = currentSeller.convertIntoEntity();
			FacesMessage message = null;
			try {
				Object[] argument = {seller.getUserName()};
				userService.delete(seller);
				String detail = MessageFormat.format(resourceMessage.getString(DELETE_USER), argument);
				message = new FacesMessage(resourceMessage.getString(DELETE_OK), detail);
			} catch (NullPointerException exception) {
				message = new FacesMessage(resourceError.getString(USER_NULL), seller.getUserName() 
						+ ", " + seller.getId() + ":" + exception.getMessage());
			} catch (IllegalStateException exception) {
				message = new FacesMessage(resourceError.getString(ID_NULL), seller.getUserName() 
						+ ":" + exception.getMessage());
			} finally {
				facesContext.addMessage(null, message);
			}
		}
		return "sellerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * @return the dataTable
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable the dataTable to set
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the listSellers
	 */
	public LinkedList<ViewSeller> getListSellers() {
		return listSellers;	// Return the already-prepared model.
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(LinkedList<ViewSeller> listSellers) {
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
}