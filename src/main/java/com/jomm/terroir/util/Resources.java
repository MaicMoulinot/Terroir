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
 * This Class is a Utility tool exposing resources using the resource producer pattern.
 * It relies on {@link Produces}.
 * @author Maic
 */
public final class Resources {
	
	// Constants
	private static final String BUNDLE_MESSAGE = "i18n.message";
	private static final String BUNDLE_ERROR = "i18n.error";
	private static final String BUNDLE_LABEL = "i18n.label";

	/**
	 * Constructor private to prevent instantiation.
	 */
	private Resources() {}
	
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
	public static Logger getLogger(InjectionPoint ip) {
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	/**
	 * Expose the FacesContext's current instance.
	 * @return FacesContext to use.
	 */
	@Produces
	@RequestScoped
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Expose the ResourceBundle relating to messages.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@BundleMessage
	public static ResourceBundle getResourceBundleMessage() {
		return ResourceBundle.getBundle(BUNDLE_MESSAGE, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to errors.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@BundleError
	public static ResourceBundle getResourceBundleError() {
		return ResourceBundle.getBundle(BUNDLE_ERROR, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to labels.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@BundleLabel
	public static ResourceBundle getResourceBundleLabel() {
		return ResourceBundle.getBundle(BUNDLE_LABEL, Locale.getDefault());
	}
}