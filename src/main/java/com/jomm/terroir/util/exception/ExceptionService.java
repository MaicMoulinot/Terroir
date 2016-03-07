package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.Resources;

/**
 * This Class is an Exception.
 * All Services throw an {@code ExceptionService} when working with an entity in an invalid state at that stage.
 * It extends {@link Exception} and overrides its method {@code getMessage()}, sending an appropriate message.
 * @author Maic
 */
public class ExceptionService extends Exception {

	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -9046790551328167438L;
	
	/**
	 * This {@link java.util.Enumeration} describes the {@link ExceptionService}.
	 * Possible values are {@code ENTITY_NULL, ID_NULL, ID_NOT_NULL}.
	 */
	public enum TypeException {
		ENTITY_NULL,
		ID_NULL,
		ID_NOT_NULL;
	}
	
	// Injected attribute
	@Inject
	@BundleError
	private transient ResourceBundle resource;
	
	// Private attribute
	private String message;
	
	
	/**
	 * Constructor.
	 * Defines the {@code message}.
	 * @param type the {@link TypeException}.
	 */
	public ExceptionService(TypeException type) {
		super();
		switch (type) {
		case ENTITY_NULL:
			message = getResourceBundle().getString(ENTITY_SHOULD_NOT_BE_NULL.getKey());
			break;
		case ID_NULL:
			message = getResourceBundle().getString(ID_SHOULD_NOT_BE_NULL.getKey());
			break;
		case ID_NOT_NULL:
			message = getResourceBundle().getString(ID_SHOULD_BE_NULL.getKey());
			break;
		default:
			message = "The parameter is not a correct TypeException.";
		}
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * This method is useful for tests where injection is not functional.
	 * It sets the {@code resource} when it is {@code null}.
	 * @return resource the {@link ResourceBundle}.
	 */
	private ResourceBundle getResourceBundle() {
		return resource != null? resource : Resources.getResourceBundleError();
	}
}