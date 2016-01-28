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

import com.jomm.terroir.business.SellerEntity;
import com.jomm.terroir.business.UserServiceInterface;
import com.jomm.terroir.util.Message;

@ManagedBean
@ViewScoped
public class SellerListJsf {

	// Injected fields
	@Inject
	private UserServiceInterface userService;	
	@Inject
	private FacesContext facesContext;	
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Attributes
	private LinkedList<SellerJsf> listSellers;
	private SellerJsf currentSeller;
	private HtmlDataTable dataTable;
	
	// Static constants
	private static final String UPDATE_USER = "updateuser";
	private static final String UPDATE_OK = "updateok";

	/**
	 * Initialize the list of all users.
	 */
	@PostConstruct 
	public void init() {
		listSellers = new LinkedList<SellerJsf>();
		for (SellerEntity sellerEntity : userService.getAllSellers()) {
			SellerJsf sellerJsf = new SellerJsf();
			sellerJsf.setId(sellerEntity.getId());
			sellerJsf.setFirstName(sellerEntity.getFirstName());
			sellerJsf.setLastName(sellerEntity.getLastName());
			sellerJsf.setUserName(sellerEntity.getUserName());
			sellerJsf.setEmail(sellerEntity.getEmail());
			sellerJsf.setPassword(sellerEntity.getUserPassword());
			sellerJsf.setEnterprise(sellerEntity.getEnterprise());
			listSellers.add(sellerJsf);
		}

	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		SellerJsf sellerJsf = (SellerJsf) event.getObject();
		if (sellerJsf != null) {
			userService.persistUser(sellerJsf.convertIntoEntity());
			Object[] argument = {sellerJsf.getUserName()};
			String detail = MessageFormat.format(resource.getString(UPDATE_USER), argument);
			FacesMessage msg = new FacesMessage(resource.getString(UPDATE_OK), detail);
			facesContext.addMessage(null, msg);
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
			SellerEntity sellerEntity = currentSeller.convertIntoEntity();
			userService.deleteUser(sellerEntity);
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
	public LinkedList<SellerJsf> getListSellers() {
		return listSellers;	// Return the already-prepared model.
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(LinkedList<SellerJsf> listSellers) {
		this.listSellers = listSellers;
	}

	/**
	 * @return the currentSeller
	 */
	public SellerJsf getCurrentSeller() {
		return currentSeller;
	}

	/**
	 * @param currentSeller the currentSeller to set
	 */
	public void setCurrentSeller(SellerJsf currentSeller) {
		this.currentSeller = currentSeller;
	}
}