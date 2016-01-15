package com.jomm.terroir.web;

import java.util.ArrayList;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.UserEntity;
import com.jomm.terroir.business.UserEntityServiceInterface;

@ManagedBean
@ViewScoped
public class UserListJsf {

	// Attributes
	@Inject
	private UserEntityServiceInterface userService;

	// Managed Backing Bean
	private HtmlDataTable dataTable;

	
	//TODO tests...
    public void onRowEdit(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("User Edited", String.valueOf(((UserJsf) event.getObject()).getUserName()));
        FacesMessage msg = new FacesMessage("User Edited", ((UserJsf) event.getObject()).getUserName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((UserJsf) event.getObject()).getId()));
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((UserJsf) event.getObject()).getUserName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   public void onCellEdit(CellEditEvent event) {
       Object oldValue = event.getOldValue();
       Object newValue = event.getNewValue();
       
       if(newValue != null && !newValue.equals(oldValue)) {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
           FacesContext.getCurrentInstance().addMessage(null, msg);
       }
       
       // TODO
//		if (userJsf != null) {
//			userService.persistUser(userJsf.convertIntoEntity());
//			userJsf.setEditable(false);//TODO util?
//		}
   }
	
	
	
	/**
	 * Set a user as editable.
	 * @param userJsf UserJsf
	 * @return null (navigation).
	 */
	public String edit(UserJsf userJsf) {
		userJsf.setEditable(true);
		return null;	// Navigation purpose.
		//TODO ne doit pas charger en base
	}
	
	
	
	/**
	 * Set a user as non-editable.
	 * @param userJsf UserJsf
	 * @return null (navigation).
	 */
	public String cancelEdit(UserJsf userJsf) {
		userJsf.setEditable(false);
		return null;	// Navigation purpose.
	}
	
	/**
	 * Update an existing user.
	 * @param userJsf UserJsf the user to be updated.
	 * @return "userlist" (navigation).
	 */
	public String validEdit(UserJsf userJsf) {
		if (userJsf != null) {
			userService.persistUser(userJsf.convertIntoEntity());
			userJsf.setEditable(false);//TODO util?
		}
		return "userlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Delete an user.
	 * @param userJsf UserJsf the user to be deleted.
	 * @return "userlist" (navigation).
	 */
	public String delete(UserJsf userJsf) {
		if (userJsf != null) {
			UserEntity userEntity = userJsf.convertIntoEntity();
			// Call Service to delete.
			userService.deleteUser(userEntity);
		}
		return "userlist" + "?faces-redirect=true";	// Navigation case.
	}

	/**
	 * Fetch all users.
	 * @return a list of all users.
	 */
	public ArrayList<UserJsf> getAllUsers() {
		ArrayList<UserJsf> allUsers = new ArrayList<>();
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
		return allUsers;
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

}