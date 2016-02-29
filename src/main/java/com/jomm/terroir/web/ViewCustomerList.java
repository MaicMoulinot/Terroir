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
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.Constants;

/**
 * This Class is the View linked to customerlist.xhtml, that displays the list of {@link ViewCustomer}.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to update or delete the {@link Customer}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public class ViewCustomerList {

	// Injected fields
	@Inject
	ServiceUser userService;	
	@Inject
	FacesContext facesContext;	
	@Inject
	@BundleMessage
	ResourceBundle resourceMessage;
	@Inject
	@BundleError
	ResourceBundle resourceError;
	
	// Attributes
	private LinkedList<ViewCustomer> listCustomers;
	private ViewCustomer currentCustomer;
	private HtmlDataTable dataTable;

	/**
	 * Initialize the list of all customers.
	 */
	@PostConstruct 
	public void init() {
		listCustomers = new LinkedList<>();
		for (Customer customer : userService.getAllCustomers()) {
			listCustomers.add(ViewCustomer.convertIntoView(customer));
		}
	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		ViewCustomer customerJsf = (ViewCustomer) event.getObject();
		if (customerJsf != null) {
			FacesMessage message = null;
			try {
				userService.update(customerJsf.convertIntoEntity());
				Object[] argument = {customerJsf.getUserName()};
				String detail = MessageFormat.format(resourceMessage.getString(Constants.UPDATE_USER), argument);
				message = new FacesMessage(resourceMessage.getString(Constants.UPDATE_OK), detail);
			} catch (NullPointerException exception) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), exception.getMessage());
			} catch (IllegalArgumentException exception) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						resourceError.getString(Constants.ID_SHOULD_NOT_BE_NULL), exception.getMessage());
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
	 * Delete a customer.
	 * @return "customerlist" (navigation).
	 */
	public String delete() {
		if (currentCustomer != null) {
			Customer customer = currentCustomer.convertIntoEntity();
			FacesMessage message = null;
			try {
				Object[] argument = {customer.getUserName()};
				userService.delete(customer);
				String detail = MessageFormat.format(resourceMessage.getString(Constants.DELETE_USER), argument);
				message = new FacesMessage(resourceMessage.getString(Constants.DELETE_OK), detail);
			} catch (NullPointerException exception) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), customer.getUserName() 
						+ ", " + customer.getId() + ":" + exception.getMessage());
			} catch (IllegalArgumentException exception) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						resourceError.getString(Constants.ID_SHOULD_NOT_BE_NULL), customer.getUserName() 
						+ ":" + exception.getMessage());
			} finally {
				facesContext.addMessage(null, message);
			}
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
	public LinkedList<ViewCustomer> getListCustomers() {
		return listCustomers;	// Return the already-prepared model.
	}

	/**
	 * @param listCustomers the listCustomers to set
	 */
	public void setListCustomers(LinkedList<ViewCustomer> listCustomers) {
		this.listCustomers = listCustomers;
	}

	/**
	 * @return the currentCustomer
	 */
	public ViewCustomer getCurrentCustomer() {
		return currentCustomer;
	}

	/**
	 * @param currentCustomer the currentCustomer to set
	 */
	public void setCurrentCustomer(ViewCustomer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
}