package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.BundleMessage;

/**
 * This abstract Class is the View that creates a new {@link com.jomm.terroir.business.model.AbstractUser}.
 * It defines common attributes shared among its children.
 * It relates to {@link ResourceBundle} to generate proper {@link BundleMessage} messages,
 * and to {@link FacesContext} to throw them to the view.
 * @author Maic
 */
public abstract class AbstractView {
	
	// Injected fields
	@Inject
	private FacesContext facesContext;
	@Inject
	@BundleError
	private ResourceBundle resourceBundleError;
	@Inject
	@BundleMessage
	private ResourceBundle resourceBundleMessage;
	
	// Utility methods
	/**
	 * This method is used when a exception occurred in a service.
	 * It adds a {@link FacesMessage} to the {@link FacesContext}.
	 * The {@link Severity} is set to {@code FacesMessage.SEVERITY_ERROR},
	 * and the summary is the value obtained from {@code ResourceBundleError.EXCEPTION}.
	 * @param detail String the message's detail.
	 */
	public void addMessageException(String detail) {
		addMessage(null, FacesMessage.SEVERITY_ERROR, getErrorFromResourceBundle(EXCEPTION.getKey()), detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext}
	 * with the {@link Severity} set to {@code FacesMessage.SEVERITY_INFO}.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(String summary, String detail) {
		addMessage(null, FacesMessage.SEVERITY_INFO, summary, detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext}
	 * with the {@link Severity} set to {@code FacesMessage.SEVERITY_INFO}.
	 * @param idClient String the client identifier with which this message is associated.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(String idClient, String summary, String detail) {
		addMessage(idClient, FacesMessage.SEVERITY_INFO, summary, detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext}.
	 * @param idClient String the client identifier with which this message is associated.
	 * @param severity {@link Severity} the message's severity, chosen from {@link FacesMessage}'s constants.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(String idClient, Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		facesContext.addMessage(idClient, message);
	}
	
	/**
	 * Generate a readable message about an exception.
	 * @param exception {@link Exception}.
	 * @param id Long the identifier of the entity.
	 * @param entity the {@link Serializable}.
	 * @return String the generated message.
	 */
	public String generateExceptionMessage(Exception exception, Long id, Serializable entity) {
		return exception.getMessage() + " on [id=" + id + ", class=" + entity.getClass().getName() + "]";
	}
	
	/**
	 * @return facesContext the {@link FacesContext}.
	 */
	protected FacesContext getFacesContext() {
		return facesContext;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param facesContext the {@link FacesContext}.
	 */
	void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
	/**
	 * Retrieve the value associated with the key in the {@link ResourceBundle} qualified with {@link BundleMessage}.
	 * @param key String the key provided.
	 * @return value String the associated value.
	 */
	protected String getMessageFromResourceBundle(String key) {
		return resourceBundleMessage.getString(key);
	}
	
	/**
	 * Retrieve the value associated with the key in the {@link ResourceBundle} qualified with {@link BundleError}.
	 * @param key String the key provided.
	 * @return value String the associated value.
	 */
	protected String getErrorFromResourceBundle(String key) {
		return resourceBundleError.getString(key);
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param resourceBundle the {@link ResourceBundle} qualified with {@link BundleMessage}.
	 */
	void setResourceBundleMessage(ResourceBundle resourceBundle) {
		this.resourceBundleMessage = resourceBundle;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param resourceBundle the {@link ResourceBundle} qualified with {@link BundleError}.
	 */
	void setResourceBundleError(ResourceBundle resourceBundle) {
		this.resourceBundleError = resourceBundle;
	}
}