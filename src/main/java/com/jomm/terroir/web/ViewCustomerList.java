package com.jomm.terroir.web;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.BundleMessage;

/**
 * This Class is the View linked to customerlist.xhtml, that displays the list of {@link ViewCustomer}.
 * It extends {@link ViewUserList} and defines specific attributes.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to update or delete the {@link Customer}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewCustomerList extends ViewUserList {
	
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