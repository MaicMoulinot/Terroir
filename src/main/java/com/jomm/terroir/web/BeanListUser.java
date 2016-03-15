package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_USER;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is used to show the list of all {@link BeanRegistrationUser}s.
 * It extends {@link BackingBean} and defines common attributes shared among its concrete children.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceUser} to update or delete the {@link AbstractUser}s,
 * and to {@link Logger} to generate proper logging messages.
 * @author Maic
 */
public abstract class BeanListUser extends BackingBean {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Injected fields
	@Inject
	protected ServiceUser userService;
	@Inject
	protected Logger logger;
	
	// Attributes
	protected AbstractUser currentUser;
	
	/**
	 * Initialize the list of all users.
	 */
	public abstract void init();
	
	/**
	 * Is called when a row is edited.
	 * @param event RowEditEvent the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		currentUser = (AbstractUser) event.getObject();
		if (currentUser != null) {
			try {
				userService.update(currentUser);
				Object[] argument = {currentUser.getUserName()};
				String detail = MessageFormat.format(getValueFromResourceBundle(UPDATE_USER), argument);
				addMessage(getValueFromResourceBundle(UPDATE_OK), detail);
			} catch (ExceptionService exception) {
				String problem = generateExceptionMessage(exception, currentUser.getId(), currentUser);
				addMessageException(problem);
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
			try {
				userService.delete(currentUser);
				Object[] argument = {currentUser.getUserName()};
				String detail = MessageFormat.format(getValueFromResourceBundle(DELETE_USER), argument);
				addMessage(getValueFromResourceBundle(DELETE_OK), detail);
			} catch (ExceptionService exception) {
				String problem = generateExceptionMessage(exception, currentUser.getId(), currentUser);
				addMessageException(problem);
				logger.log(Level.FINE, problem, exception);
			}
		}
		return null;	// Navigation case.
	}

	/**
	 * @return the currentUser
	 */
	public AbstractUser getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(AbstractUser currentUser) {
		this.currentUser = currentUser;
	}
}