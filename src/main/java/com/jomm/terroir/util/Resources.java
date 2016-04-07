package com.jomm.terroir.util;

import static com.jomm.terroir.util.Constants.PERSISTENCE_UNIT;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.ERROR;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.LABEL;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.MESSAGE;

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

import com.jomm.terroir.util.Constants.Entity;
import com.jomm.terroir.util.Constants.ResourceBundleError;
import com.jomm.terroir.util.Constants.ResourceBundleMessage;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is a Utility tool exposing resources using the resource producer pattern.
 * It relies on {@link Produces}.
 * @author Maic
 */
public final class Resources {
	
	// Variables //-----------------------------------------------
	/**
	 * Expose an {@link EntityManager} using the resource producer pattern.
	 */
	@Produces
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private static EntityManager entityManager;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor private to prevent instantiation.
	 */
	private Resources() {}	
	
	// Methods //-------------------------------------------------
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
	 * Retrieve the value from the {@link ResourceBundle} {@code error}.
	 * @param key {@link ResourceBundleError} the key.
	 * @return String the value.
	 */
	public static String getValueFromKey(ResourceBundleError key) {
		return ResourceBundle.getBundle(ERROR.getFileName(), Locale.getDefault()).getString(key.getKey());
	}
	
	/**
	 * Retrieve the value from the {@link ResourceBundle} {@code message}.
	 * @param key {@link ResourceBundleMessage} the key.
	 * @return String the value.
	 */
	public static String getValueFromKey(ResourceBundleMessage key) {
		return ResourceBundle.getBundle(MESSAGE.getFileName(), Locale.getDefault()).getString(key.getKey());
	}
	
	/**
	 * Retrieve the value from the {@link ResourceBundle} {@code label}.
	 * @param key {@link Unit} the key.
	 * @return String the value.
	 */
	public static String getValueFromKey(Unit key) {
		return ResourceBundle.getBundle(LABEL.getFileName(), Locale.getDefault()).getString("unit" + key.getSymbol());
	}
	
	/**
	 * Retrieve the value from the {@link ResourceBundle} {@code message}.
	 * @param key {@link Entity} the key.
	 * @return String the value.
	 */
	public static String getValueFromKey(Entity key) {
		return ResourceBundle.getBundle(MESSAGE.getFileName(), Locale.getDefault()).getString(key.toString());
	}
}