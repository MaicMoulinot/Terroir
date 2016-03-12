package com.jomm.terroir.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.jomm.terroir.business.model.Customer;

/**
 * This Class is the Bean linked to {@code listcustomer.xhtml}, displaying the list of {@link BeanRegistrationCustomer}s.
 * It extends {@link BeanListUser} and defines specific attributes.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BeanListCustomer extends BeanListUser implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 8628417548186329266L;
	
	// Attributes
	private List<BeanRegistrationCustomer> listCustomers;

	@Override
	@PostConstruct 
	public void init() {
		listCustomers = new LinkedList<>();
		for (Customer customer : userService.getAllCustomers()) {
			listCustomers.add(BeanRegistrationCustomer.convertIntoView(customer));
		}
	}

	@Override
	public String delete() {
		super.delete();
		return "listcustomer" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * @return the listCustomers
	 */
	public List<BeanRegistrationCustomer> getListCustomers() {
		return listCustomers;	// Return the already-prepared model.
	}

	/**
	 * @param listCustomers the listCustomers to set
	 */
	public void setListCustomers(List<BeanRegistrationCustomer> listCustomers) {
		this.listCustomers = listCustomers;
	}
}