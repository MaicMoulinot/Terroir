package com.jomm.terroir.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Seller;

/**
 * This Class is the Bean linked to {@code listseller.xhtml}, displaying the list of {@link BeanRegistrationSeller}s.
 * It extends {@link BeanListUser} and defines specific attributes.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BeanListSeller extends BeanListUser implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 2234745264902170386L;
	
	// Attributes
	private List<BeanRegistrationSeller> listSellers;

	@Override
	@PostConstruct 
	public void init() {
		listSellers = new LinkedList<>();
		for (Seller seller : userService.getAllSellers()) {
			listSellers.add(BeanRegistrationSeller.convertIntoView(seller));
		}
	}

	@Override
	public String delete() {
		super.delete();
		return "listseller" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * @return the listSellers
	 */
	public List<BeanRegistrationSeller> getListSellers() {
		return listSellers;	// Return the already-prepared model.
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(List<BeanRegistrationSeller> listSellers) {
		this.listSellers = listSellers;
	}
}