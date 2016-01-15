package com.jomm.terroir.web;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@ManagedBean
@ViewScoped
public class UserListJsf {

	// Attributes
	private LinkedList<UserJsf> allUsers;
	private UserJsf currentUser;

	@Inject
	private UserEntityServiceInterface userService;

	// Managed Backing Bean
	private HtmlDataTable dataTable;

	/**
	 * Initialize the list of all users.
	 */
	@PostConstruct 
	public void init() {
		allUsers = new LinkedList<UserJsf>();
		for (UserEntity userEntity : userService.getAllUsers()) {
			UserJsf userJsf = new UserJsf();
			userJsf.setAge(userEntity.getAge());
			userJsf.setEmail(userEntity.getEmail());
			userJsf.setFirstName(userEntity.getFirstName());
			userJsf.setLastName(userEntity.getLastName());
			userJsf.setUserName(userEntity.getUserName());
			userJsf.setId(userEntity.getUserId());
			allUsers.add(userJsf);
		}

	}

	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		UserJsf userJsf = (UserJsf) event.getObject();
		if (userJsf != null) {
			userService.persistUser(userJsf.convertIntoEntity());
			ResourceBundle resource = ResourceBundle.getBundle("i18n.label", Locale.getDefault());
			String detail = MessageFormat.format(resource.getString("updateuser"), userJsf.getUserName());
			FacesMessage msg = new FacesMessage(resource.getString("updateok"), detail);
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
	 * @return "userlist" (navigation).
	 */
	public String delete() {
		if (currentUser != null) {
			UserEntity userEntity = currentUser.convertIntoEntity();
			// Call Service to delete.
			userService.deleteUser(userEntity);
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