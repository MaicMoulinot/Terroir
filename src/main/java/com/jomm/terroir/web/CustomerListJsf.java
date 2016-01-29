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

import com.jomm.terroir.business.Customer;
import com.jomm.terroir.business.UserService;
import com.jomm.terroir.util.Message;

/**
 * This Class is the View linked to customerlist.xhtml, that displays the list of {@link CustomerJsf}.
 * It relates to {@link ResourceBundle} to generate proper {@link Message} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link UserService} to update or delete the {@link Customer}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class CustomerListJsf {

	// Injected fields
	@Inject
	private UserService userService;	
	@Inject
	private FacesContext facesContext;	
	@Inject
	@Message
	private ResourceBundle resource;
	
	// Attributes
	private LinkedList<CustomerJsf> listCustomers;
	private CustomerJsf currentCustomer;
	private HtmlDataTable dataTable;
	
	// Static constants
	private static final String UPDATE_USER = "updateuser";
	private static final String UPDATE_OK = "updateok";

	/**
	 * Initialize the list of all customers.
	 */
	@PostConstruct 
	public void init() {
		listCustomers = new LinkedList<CustomerJsf>();
		for (Customer customer : userService.getAllCustomers()) {
			CustomerJsf customerJsf = new CustomerJsf();
			customerJsf.setId(customer.getId());
			customerJsf.setFirstName(customer.getFirstName());
			customerJsf.setLastName(customer.getLastName());
			customerJsf.setUserName(customer.getUserName());
			customerJsf.setEmail(customer.getEmail());
			customerJsf.setPassword(customer.getUserPassword());
			customerJsf.setBirthDate(customer.getBirthDate());
			customerJsf.setSignUpDate(customer.getSignUpDate());
			listCustomers.add(customerJsf);
		}
	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		CustomerJsf customerJsf = (CustomerJsf) event.getObject();
		if (customerJsf != null) {
			userService.update(customerJsf.convertIntoEntity());
			Object[] argument = {customerJsf.getUserName()};
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
	 * Delete a customer.
	 * @return "customerlist" (navigation).
	 */
	public String delete() {
		if (currentCustomer != null) {
			Customer userEntity = currentCustomer.convertIntoEntity();
			userService.delete(userEntity);
		}
		return "customerlist" + "?faces-redirect=true";	// Navigation case.
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
	 * @return the listCustomers
	 */
	public LinkedList<CustomerJsf> getListCustomers() {
		return listCustomers;	// Return the already-prepared model.
	}

	/**
	 * @param listCustomers the listCustomers to set
	 */
	public void setListCustomers(LinkedList<CustomerJsf> listCustomers) {
		this.listCustomers = listCustomers;
	}

	/**
	 * @return the currentCustomer
	 */
	public CustomerJsf getCurrentCustomer() {
		return currentCustomer;
	}

	/**
	 * @param currentCustomer the currentCustomer to set
	 */
	public void setCurrentCustomer(CustomerJsf currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
}