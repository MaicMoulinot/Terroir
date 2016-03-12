package com.jomm.terroir.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Customer;

/**
 * This Class is the Bean linked to {@code customerlist.xhtml}, displaying the list of {@link ViewCustomer}s.
 * It extends {@link ViewUserList} and defines specific attributes.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class ViewCustomerList extends ViewUserList implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 8628417548186329266L;
	
	// Attributes
	private List<ViewCustomer> listCustomers;

	@Override
	@PostConstruct 
	public void init() {
		listCustomers = new LinkedList<>();
		for (Customer customer : userService.getAllCustomers()) {
			listCustomers.add(ViewCustomer.convertIntoView(customer));
		}
	}

	@Override
	public String delete() {
		super.delete();
		return "customerlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * @return the listCustomers
	 */
	public List<ViewCustomer> getListCustomers() {
		return listCustomers;	// Return the already-prepared model.
	}

	/**
	 * @param listCustomers the listCustomers to set
	 */
	public void setListCustomers(List<ViewCustomer> listCustomers) {
		this.listCustomers = listCustomers;
	}
}