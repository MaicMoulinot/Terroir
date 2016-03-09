package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_USER;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
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
 * @author Maic
 */
public abstract class ViewUserList extends AbstractView {

	// Injected fields
	@Inject
	protected ServiceUser userService;
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
		currentUser = (ViewUser) event.getObject();
		if (currentUser != null) {
			AbstractUser entity = currentUser.convertIntoEntity();
			try {
				userService.update(entity);
				Object[] argument = {entity.getUserName()};
				String detail = MessageFormat.format(resourceBundleMessage.getString(UPDATE_USER.getKey()), argument);
				addMessage(resourceBundleMessage.getString(UPDATE_OK.getKey()), detail);
			} catch (ExceptionService exception) {
				String problem = generateExceptionMessage(exception, entity.getId(), entity);
				addMessage(FacesMessage.SEVERITY_ERROR, resourceBundleError.getString(EXCEPTION.getKey()), problem);
				logger.log(Level.FINE, problem, exception);
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
			AbstractUser entity = currentUser.convertIntoEntity();
			try {
				userService.delete(entity);
				Object[] argument = {entity.getUserName()};
				String detail = MessageFormat.format(resourceBundleMessage.getString(DELETE_USER.getKey()), argument);
				addMessage(resourceBundleMessage.getString(DELETE_OK.getKey()), detail);
			} catch (ExceptionService exception) {
				String problem = generateExceptionMessage(exception, entity.getId(), entity);
				addMessage(FacesMessage.SEVERITY_ERROR, resourceBundleError.getString(EXCEPTION.getKey()), problem);
				logger.log(Level.FINE, problem, exception);
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
	public ViewUser getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(ViewUser currentUser) {
		this.currentUser = currentUser;
	}
}