package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.io.Serializable;

import com.jomm.terroir.util.Constants.ResourceBundleError;

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

	// Variables //-----------------------------------------------
	private String message;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor. Defines the {@code message}.
	 * @param error {@link ResourceBundleError}.
	 */
	public ExceptionService(ResourceBundleError error) {
		super();
		message = getValueFromKey(error);
	}

	// Getters and Setters //-------------------------------------
	@Override
	public String getMessage() {
		return message;
	}
}