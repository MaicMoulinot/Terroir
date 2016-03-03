package com.jomm.terroir.web;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

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

	// Injected fields
	@Inject
	private Logger logger;
	
	// Attributes
	private List<ViewCustomer> listCustomers;
	private ViewCustomer currentCustomer;

	@Override
	@PostConstruct 
	public void init() {
		listCustomers = new LinkedList<>();
		for (Customer customer : userService.getAllCustomers()) {
			listCustomers.add(ViewCustomer.convertIntoView(customer));
		}
	}

	@Override
	public void onRowEdit(RowEditEvent event) {
		ViewCustomer customerJsf = (ViewCustomer) event.getObject();
		if (customerJsf != null) {
			FacesMessage message = null;
			try {
				userService.update(customerJsf.convertIntoEntity());
				Object[] argument = {customerJsf.getUserName()};
				String detail = MessageFormat.format(resource.getString(Constants.UPDATE_USER), argument);
				message = new FacesMessage(resource.getString(Constants.UPDATE_OK), detail);
			} catch (ExceptionNullEntity | ExceptionInvalidId exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + customerJsf.getUserName() + ", UserId=" + customerJsf.getId());
				logger.log(Level.FINE, problem, exception);
			} finally {
				facesContext.addMessage(null, message);
			}
		}
	}

	@Override
	public void onRowCancel(RowEditEvent event) {
		// Do nothing.
	}

	@Override
	public String delete() {
		if (currentCustomer != null) {
			Customer customer = currentCustomer.convertIntoEntity();
			FacesMessage message = null;
			try {
				Object[] argument = {customer.getUserName()};
				userService.delete(customer);
				String detail = MessageFormat.format(resource.getString(Constants.DELETE_USER), argument);
				message = new FacesMessage(resource.getString(Constants.DELETE_OK), detail);
			} catch (ExceptionNullEntity | ExceptionInvalidId exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + customer.getUserName() + ", UserId=" + customer.getId());
				logger.log(Level.FINE, problem, exception);
			} finally {
				facesContext.addMessage(null, message);
			}
		}
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
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param logger the logger to set
	 */
	void setLogger(Logger logger) {
		this.logger = logger;
	}
}