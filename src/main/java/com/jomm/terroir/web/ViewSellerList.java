package com.jomm.terroir.web;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.BundleMessage;

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
	
	// Attributes
	private List<ViewSeller> listSellers;

	@Override
	@PostConstruct 
	public void init() {
		listSellers = new LinkedList<>();
		for (Seller seller : userService.getAllSellers()) {
			listSellers.add(ViewSeller.convertIntoView(seller));
		}
	}

	@Override
	public String delete() {
		super.delete();
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
}