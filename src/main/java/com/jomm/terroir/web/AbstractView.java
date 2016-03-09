package com.jomm.terroir.web;

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
	protected FacesContext facesContext;
	@Inject
	@BundleError
	protected ResourceBundle resourceBundleError;
	@Inject
	@BundleMessage
	protected ResourceBundle resourceBundleMessage;
	
	// Utility methods
	/**
	 * Add a {@link FacesMessage} to the {@link FacesContext}
	 * with the {@link Severity} {@code FacesMessage.SEVERITY_INFO}.
	 * @param summary String the message's summary.
	 */
	public void addMessage(String summary) {
		addMessage(summary, null);
	}
	
	/**
	 * Add a {@link FacesMessage} to the {@link FacesContext}
	 * with the {@link Severity} {@code FacesMessage.SEVERITY_INFO}.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}
	
	/**
	 * Add a {@link FacesMessage} to the {@link FacesContext}.
	 * @param severity {@link Severity} the message's severity.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(Severity severity, String summary, String detail) {
		addMessage(null, severity, summary, detail);
	}
	
	/**
	 * Add a {@link FacesMessage} to the {@link FacesContext} 
	 * with the {@link Severity} {@code FacesMessage.SEVERITY_INFO}.
	 * @param idClient String the client identifier with which this message is associated (if any).
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	public void addMessage(String idClient, String summary, String detail) {
		addMessage(idClient, FacesMessage.SEVERITY_INFO, summary, detail);
	}
	
	/**
	 * Add a {@link FacesMessage} to the {@link FacesContext}.
	 * @param idClient String the client identifier with which this message is associated (if any).
	 * @param severity {@link Severity} the message's severity.
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
	 * @return
	 */
	public String generateExceptionMessage(Exception exception, Long id, Serializable entity) {
		return exception.getMessage() + " on [id=" + id + ", class=" + entity.getClass().getName() + "]";
	}
}