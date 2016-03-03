package com.jomm.terroir.web;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.util.BundleMessage;

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
	
	// Attributes
	protected HtmlDataTable dataTable;
	
	/**
	 * Initialize the list of all users.
	 */
	public abstract void init();
	
	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public abstract void onRowEdit(RowEditEvent event);
	
	/**
	 * Is called when a edited row is back to normal state.
	 * @param event RowEditEvent the AJAX event.
	 */
	public abstract void onRowCancel(RowEditEvent event);
	
	/**
	 * Delete an user.
	 * @return a String for navigation.
	 */
	public abstract String delete();
	
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
}