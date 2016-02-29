package com.jomm.terroir.util;

import java.time.ZoneId;
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

	/**
	 * Expose an {@link EntityManager} using the resource producer pattern.
	 */
	@PersistenceContext(name="terroirPU")
	@Produces
	private static EntityManager entityManager;
	
	/**
	 * Constructor private to prevent instantiation.
	 */
	private Resources() {}	

	/**
	 * Expose the {@link Logger} using the resource producer pattern.
	 * @param injectionPoint the {@link InjectionPoint}.
	 * @return the {@link Logger} to use.
	 */
	@Produces
	public static Logger getLogger(InjectionPoint injectionPoint) {
		Logger logger = null;
		if (injectionPoint != null) {
			String category = injectionPoint.getMember().getDeclaringClass().getName();
			logger = Logger.getLogger(category);
		}
		return logger;
	}
	
	/**
	 * Expose the {@link ZoneId} of the system using the resource producer pattern.
	 * @return the {@link ZoneId} to use.
	 */
	@Produces
	public static ZoneId getZonedId() {
		return ZoneId.systemDefault();
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
		return ResourceBundle.getBundle(Constants.BUNDLE_MESSAGE, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to errors.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@BundleError
	public static ResourceBundle getResourceBundleError() {
		return ResourceBundle.getBundle(Constants.BUNDLE_ERROR, Locale.getDefault());
	}
	
	/**
	 * Expose the ResourceBundle relating to labels.
	 * @return ResourceBundle to use.
	 */
	@Produces
	@BundleLabel
	public static ResourceBundle getResourceBundleLabel() {
		return ResourceBundle.getBundle(Constants.BUNDLE_LABEL, Locale.getDefault());
	}
}