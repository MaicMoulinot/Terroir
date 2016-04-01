package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jomm.terroir.business.model.AbstractEntity;
import com.jomm.terroir.util.Constants.Entity;

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
	protected void addFacesMessageException(String detail) {
		addFacesMessage(null, FacesMessage.SEVERITY_ERROR, getValueFromKey(EXCEPTION), detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful create.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param id Long the entity's identifier.
	 */
	protected void addFacesMessageCreate(Entity entity, Long id) {
		addFacesMessageCreate(entity, "id=[" + id + "]");
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful create.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param name String the entity's name.
	 */
	protected void addFacesMessageCreate(Entity entity, String name) {
		Object[] argument = {name};
		String detail = MessageFormat.format(getValueFromKey(entity), argument) + getValueFromKey(CREATE);
		addFacesMessage(null, FacesMessage.SEVERITY_INFO, getValueFromKey(CREATE_OK), detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful update.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param id Long the entity's identifier.
	 */
	protected void addFacesMessageUpdate(Entity entity, Long id) {
		addFacesMessageUpdate(entity, "id=[" + id + "]");
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful update.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param name String the entity's name.
	 */
	protected void addFacesMessageUpdate(Entity entity, String name) {
		Object[] argument = {name};
		String detail = MessageFormat.format(getValueFromKey(entity), argument) + getValueFromKey(UPDATE);
		addFacesMessage(null, FacesMessage.SEVERITY_INFO, getValueFromKey(UPDATE_OK), detail);
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful delete.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param id Long the entity's identifier.
	 */
	protected void addFacesMessageDelete(Entity entity, Long id) {
		addFacesMessageDelete(entity, "id=[" + id + "]");
	}
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext} about a successful delete.
	 * @param entity a value from the enumeration {@link Entity}.
	 * @param name String the entity's name.
	 */
	protected void addFacesMessageDelete(Entity entity, String name) {
		Object[] argument = {name};
		String detail = MessageFormat.format(getValueFromKey(entity), argument) + getValueFromKey(DELETE);
		addFacesMessage(null, FacesMessage.SEVERITY_INFO, getValueFromKey(DELETE_OK), detail);
	}	
	
	/**
	 * This method adds a {@link FacesMessage} to the {@link FacesContext}.
	 * @param idClient String the client identifier with which this message is associated.
	 * @param severity {@link Severity} the message's severity, chosen from {@link FacesMessage}'s constants.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	protected void addFacesMessage(String idClient, Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		facesContext.addMessage(idClient, message);
	}
	
	/**
	 * Generate a readable message about an exception.
	 * @param exception {@link Exception}.
	 * @param entity the {@link AbstractEntity}.
	 * @return String the generated message.
	 */
	protected String generateReadableExceptionMessage(Exception exception, AbstractEntity entity) {
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