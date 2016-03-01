package com.jomm.terroir.util.exception;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.Constants;

/**
 * This Class is an Exception.
 * It is used in Services when working with a <code>null</code> entity that should not be <code>null</code>.
 * It extends {@link Exception} and overrides its methods <code>getMessage()</code> 
 * and <code>getLocalizedMessage()</code>.
 * @author Maic
 */
public class ExceptionNullEntity extends Exception {

	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -9046790551328167438L;
	
	@Inject
	@BundleError
	private transient ResourceBundle resource;
	
	
	@Override
	public String getMessage() {
		return "The entity is null at this stage and it should not";
	}
	
	@Override
	public String getLocalizedMessage() {
		return resource.getString(Constants.USER_SHOULD_NOT_BE_NULL);
	}
}