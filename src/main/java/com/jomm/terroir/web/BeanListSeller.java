package com.jomm.terroir.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Seller;

/**
 * This Class is the Bean linked to {@code listseller.xhtml}, displaying the list of {@link Seller}s.
 * It extends {@link BeanListUser} and defines specific attributes.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BeanListSeller extends BeanListUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Variables //-----------------------------------------------
	private List<Seller> listSellers;
	
	// Methods //-------------------------------------------------
	@Override
	@PostConstruct 
	public void init() {
		listSellers = userService.getAllSellers();
	}

	@Override
	public String delete() {
		super.delete();
		return "listseller" + "?faces-redirect=true";	// Navigation case.
	}
	
	// Getters and Setters //-------------------------------------
	/**
	 * @return the listSellers
	 */
	public List<Seller> getListSellers() {
		return listSellers;	// Return the already-prepared model.
	}

	/**
	 * @param listSellers the listSellers to set
	 */
	public void setListSellers(List<Seller> listSellers) {
		this.listSellers = listSellers;
	}
}