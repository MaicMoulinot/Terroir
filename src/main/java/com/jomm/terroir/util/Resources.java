package com.jomm.terroir.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This Class is the specific Tool for exposing resources using the resource producer pattern.
 * It relies on {@link Produces}.
 * @author Maic
 */
public class Resources {
	
	// Constants
	public static final String BUNDLE_MESSAGE = "i18n.message";
	public static final String BUNDLE_ERROR = "i18n.error";
	public static final String BUNDLE_LABEL = "i18n.label";

	/**
	 * Expose an entity manager using the resource producer pattern
	 */
	@PersistenceContext(name="terroirPU")
	@Produces
	private EntityManager entityManager;

	/**
	 * Expose the Logger.
	 * @param ip InjectionPoint the injection point.
	 * @return Logger to use.
	 */
	@Produces
	public Logger getLogger(InjectionPoint ip) {
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	/**
	 * Expose the FacesContext's current instance.
	 * @return FacesContext to use.
	 */
	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Expose the ResourceBundle relating to messages.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@Message
	public ResourceBundle getResourceBundleMessage() {
		return ResourceBundle.getBundle(BUNDLE_MESSAGE, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to errors.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@Error
	public ResourceBundle getResourceBundleError() {
		return ResourceBundle.getBundle(BUNDLE_ERROR, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to labels.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@Label
	public ResourceBundle getResourceBundleLabel() {
		return ResourceBundle.getBundle(BUNDLE_LABEL, Locale.getDefault());
	}
}

