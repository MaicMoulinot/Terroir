package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.model.AbstractEntity;

/**
 * This abstract Class defines common attributes shared among all Beans.
 * It implements {@link Serializable} and has a default serial version ID.
 * It relates to {@link FacesContext} to throw appropriate {@link FacesMessage} to the view.
 * @author Maic
 */
public abstract class BackingBean implements Serializable {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Injected Fields //-----------------------------------------
	@Inject
	private FacesContext facesContext;
	
	// Methods //-------------------------------------------------
	/**
	 * This method is used when a exception occurred in a service.
	 * It adds a {@link FacesMessage} to the {@link FacesContext}.
	 * The {@link Severity} is set to {@code FacesMessage.SEVERITY_ERROR},
	 * and the summary is the value obtained from {@code ResourceBundleError.EXCEPTION}.
	 * @param detail String the message's detail.
	 */
	public void addMessageException(String detail) {
		addMessage(null, FacesMessage.SEVERITY_ERROR, getValueFromKey(EXCEPTION), detail);
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
	 * @param entity the {@link AbstractEntity}.
	 * @return String the generated message.
	 */
	public String generateExceptionMessage(Exception exception, AbstractEntity entity) {
		String message = exception.getMessage();
		if (entity != null) {
			message += " on [id=" + entity.getId() + ", class=" + entity.getClass().getName() + "]";
		}
		return message;
	}
	
	// Getters and Setters //-------------------------------------
	/**
	 * @return facesContext the {@link FacesContext}.
	 */
	protected FacesContext getFacesContext() {
		return facesContext;
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param facesContext the {@link FacesContext}.
	 */
	void setTestFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}