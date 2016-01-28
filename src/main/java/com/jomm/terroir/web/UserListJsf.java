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

import com.jomm.terroir.business.CustomerEntity;
import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;
import com.jomm.terroir.util.Message;

@ManagedBean
@ViewScoped
public class UserListJsf {

	// Attributes
	private LinkedList<UserJsf> allUsers;
	private UserJsf currentUser;

	@Inject
	private UserEntityServiceInterface userService;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	@Message
	private ResourceBundle resource;

	// Managed Backing Bean
	private HtmlDataTable dataTable;
	
	// Static constants
	private static final String UPDATE_USER = "updateuser";
	private static final String UPDATE_OK = "updateok";

	/**
	 * Initialize the list of all users.
	 */
	@PostConstruct 
	public void init() {
//		allUsers = new LinkedList<UserJsf>();
//		for (CustomerEntity userEntity : userService.getAllCustomers()) {
//			UserJsf userJsf = new UserJsf();
//			userJsf.setId(userEntity.getId());
//			userJsf.setFirstName(userEntity.getFirstName());
//			userJsf.setLastName(userEntity.getLastName());
//			userJsf.setUserName(userEntity.getUserName());
//			userJsf.setEmail(userEntity.getEmail());
//			userJsf.setPassword(userEntity.getUserPassword());
//			userJsf.setBirthDate(userEntity.getBirthDate());
//			userJsf.setSignUpDate(userEntity.getSignUpDate());
//			allUsers.add(userJsf);
//		}

	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		UserJsf userJsf = (UserJsf) event.getObject();
		if (userJsf != null) {
//			userService.persistUser(userJsf.convertIntoEntity());
			Object[] argument = {userJsf.getUserName()};
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
		//TODO put ancient values
	}

	/**
	 * Delete an user.
	 * @return "userlist" (navigation).
	 */
	public String delete() {
		if (currentUser != null) {
//			UserEntity userEntity = currentUser.convertIntoEntity();
			// Call Service to delete.
//			userService.deleteUser(userEntity);
		}
		return "userlist" + "?faces-redirect=true";	// Navigation case.
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
	 * @return the allUsers
	 */
	public LinkedList<UserJsf> getAllUsers() {
		return allUsers;	// Return the already-prepared model.
	}

	/**
	 * @param allUsers the allUsers to set
	 */
	public void setAllUsers(LinkedList<UserJsf> allUsers) {
		this.allUsers = allUsers;
	}

	/**
	 * @return the currentUser
	 */
	public UserJsf getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(UserJsf currentUser) {
		this.currentUser = currentUser;
	}
}