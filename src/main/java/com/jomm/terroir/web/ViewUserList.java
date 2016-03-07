package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_USER;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.BundleMessage;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is the View linked to a list of {@link ViewUser}.
 * It defines common attributes shared among its children.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * to {@link FacesContext} to throw them to the view, 
 * and to {@link ServiceUser} to update or delete the {@link ViewUser}.
 * It is annotated {@link ManagedBean} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@ManagedBean
@ViewScoped
public abstract class ViewUserList {

	// Injected fields
	@Inject
	protected ServiceUser userService;
	@Inject
	protected FacesContext facesContext;
	@Inject
	@BundleMessage
	protected ResourceBundle resource;
	@Inject
	protected Logger logger;
	
	// Attributes
	protected HtmlDataTable dataTable;
	protected ViewUser currentUser;
	
	/**
	 * Initialize the list of all users.
	 */
	public abstract void init();
	
	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		ViewUser view = (ViewUser) event.getObject();
		if (view != null) {
			FacesMessage message = null;
			try {
				userService.update(view.convertIntoEntity());
				Object[] argument = {view.getUserName()};
				String detail = MessageFormat.format(resource.getString(UPDATE_USER.getKey()), argument);
				message = new FacesMessage(resource.getString(UPDATE_OK.getKey()), detail);
			} catch (ExceptionService exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + view.getUserName() + ", UserId=" + view.getId());
				logger.log(Level.FINE, problem, exception);
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
	 * Delete an user.
	 * @return a String for navigation.
	 */
	public String delete() {
		if (currentUser != null) {
			AbstractUser user = currentUser.convertIntoEntity();
			FacesMessage message = null;
			try {
				Object[] argument = {user.getUserName()};
				userService.delete(user);
				String detail = MessageFormat.format(resource.getString(DELETE_USER.getKey()), argument);
				message = new FacesMessage(resource.getString(DELETE_OK.getKey()), detail);
			} catch (ExceptionService exception) {
				String problem = exception.getMessage();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, problem, 
						"Username=" + user.getUserName() + ", UserId=" + user.getId());
				logger.log(Level.FINE, problem, exception);
			} finally {
				facesContext.addMessage(null, message);
			}
		}
		return null;	// Navigation case.
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
	 * @return the currentUser
	 */
	ViewUser getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	void setCurrentUser(ViewUser currentUser) {
		this.currentUser = currentUser;
	}
}