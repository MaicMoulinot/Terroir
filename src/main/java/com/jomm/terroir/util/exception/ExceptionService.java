package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.io.Serializable;

/**
 * This Class is an Exception.
 * All Services throw an {@code ExceptionService} when working with an entity in an invalid state at that stage.
 * It extends {@link Exception} and overrides its method {@code getMessage()}, sending an appropriate message.
 * It implements {@link Serializable} and has a default serial version ID.
 * @author Maic
 */
public class ExceptionService extends Exception {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This {@link java.util.Enumeration} describes the {@link ExceptionService}.
	 * Possible values are {@code ENTITY_NULL, ID_NULL, ID_NOT_NULL}.
	 */
	public enum TypeException {
		/** The entity is {@code null}. */
		ENTITY_NULL,
		/** The entity's identifier is {@code null}. */
		ID_NULL,
		/** The entity's identifier is not {@code null}. */
		ID_NOT_NULL;
	}
	
	// Variables //-----------------------------------------------
	private String message;
	
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Defines the {@code message}.
	 * @param type the {@link TypeException}.
	 */
	public ExceptionService(TypeException type) {
		super();
		switch (type) {
		case ENTITY_NULL:
			message = getValueFromKey(ENTITY_SHOULD_NOT_BE_NULL);
			break;
		case ID_NULL:
			message = getValueFromKey(ID_SHOULD_NOT_BE_NULL);
			break;
		case ID_NOT_NULL:
			message = getValueFromKey(ID_SHOULD_BE_NULL);
			break;
		default:
			message = "The parameter is not a correct TypeException.";
		}
	}
	
	// Getters and Setters //-------------------------------------
	@Override
	public String getMessage() {
		return message;
	}
}