package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CONFIRM_DELETE;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.util.Constants.Entity;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This abstract Class is used to show the list of all {@link AbstractUser}s.
 * It extends {@link BackingBean} and defines common attributes shared among its concrete children.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceUser} to update or delete the {@link AbstractUser}s,
 * and to {@link Logger} to generate proper logging messages.
 * @author Maic
 */
public abstract class BackingListUser extends BackingBean {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Injected Fields //-----------------------------------------
	@Inject
	protected ServiceUser userService;
	@Inject
	protected Logger logger;
	
	// Variables //-----------------------------------------------
	protected AbstractUser currentUser;
	
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes (i.e. prepare the list of all users).
	 * It replaces the constructor and it is annotated {@link javax.annotation.PostConstruct} 
	 * in concrete implementations, for proper call from the bean management framework 
	 * which uses proxies, such as CDI.
	 */
	public abstract void init();
	
	/**
	 * Is called when a row is edited.
	 * @param event {@link RowEditEvent} the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		currentUser = (AbstractUser) event.getObject();
		if (currentUser != null) {
			try {
				userService.update(currentUser);
				addFacesMessageUpdate(getConstantsEntity(), currentUser.getUserName());
			} catch (ExceptionService exception) {
				String problem = generateReadableExceptionMessage(exception, currentUser);
				addFacesMessageException(problem);
				logger.log(Level.FINE, problem, exception);
			}
		}
	}
	
	/**
	 * Is called when a edited row is back to normal state.
	 * @param event {@link RowEditEvent} the AJAX event.
	 */
	public void onRowCancel(RowEditEvent event) {
		// Do nothing.
	}
	
	/**
	 * Delete an user.
	 * This may be overridden in concrete child to return a String as appropriate navigation case.
	 * @return a String for navigation.
	 */
	public String delete() {
		if (currentUser != null) {
			try {
				userService.delete(currentUser);
				addFacesMessageDelete(getConstantsEntity(), currentUser.getUserName());
			} catch (ExceptionService exception) {
				String problem = generateReadableExceptionMessage(exception, currentUser);
				addFacesMessageException(problem);
				logger.log(Level.FINE, problem, exception);
			}
		}
		return null;	// Navigation case.
	}
	
	/**
	 * Format a message for the delete confirmation.
	 * @return an empty String if the {@code currentUser} is {@code null}, the formatted message otherwise.
	 */
	public String confirmeDeleteMessage() {
		String message = "";
		if (currentUser != null) {
			Object[] argument = {currentUser.getUserName()};
			message = MessageFormat.format(getValueFromKey(getConstantsEntity()).replace("'", "''"), argument) 
					+ getValueFromKey(CONFIRM_DELETE);
		}
		return message;
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Return the appropriate value from enumeration {@link Entity}.
	 * @return {@link Entity}.
	 */
	protected abstract Entity getConstantsEntity();
	
	// Getters and Setters //-------------------------------------
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