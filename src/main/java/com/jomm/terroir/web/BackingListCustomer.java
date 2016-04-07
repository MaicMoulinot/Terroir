package com.jomm.terroir.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.Constants.Entity;

/**
 * This Class is the Bean linked to {@code listcustomer.xhtml}, displaying the list of {@link Customer}s.
 * It extends {@link BackingListUser} and defines specific attributes.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BackingListCustomer extends BackingListUser {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Variables //-----------------------------------------------
	private List<Customer> listCustomers;
	
	// Methods //-------------------------------------------------
	@Override
	@PostConstruct 
	public void init() {
		listCustomers = userService.getAllCustomers();
	}
	
	@Override
	public String delete() {
		super.delete();
		return "listcustomer" + "?faces-redirect=true";	// Navigation case.
	}
	
	// Helpers //-------------------------------------------------
	@Override
	protected Entity getConstantsEntity() {
		return Entity.CUSTOMER;
	}

	// Getters and Setters //-------------------------------------
	/**
	 * @return the listCustomers
	 */
	public List<Customer> getListCustomers() {
		return listCustomers;	// Return the already-prepared model.
	}

	/**
	 * @param listCustomers the listCustomers to set
	 */
	public void setListCustomers(List<Customer> listCustomers) {
		this.listCustomers = listCustomers;
	}
}