package com.jomm.terroir.util.exception;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.Constants;

/**
 * This Class is an Exception.
 * It is used in Services when working with an entity whose id is invalid :
 * it is <code>null</code> when it should not, or it is not <code>null</code> when it should.
 * It extends {@link Exception} and overrides its methods <code>getMessage()</code> 
 * and <code>getLocalizedMessage()</code>.
 * @author Maic
 */
public class ExceptionInvalidId extends Exception {

	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = -9046790551328167438L;
	
	private boolean idShouldBeNull;
	
	@Inject
	@BundleError
	private transient ResourceBundle resource;
	
	/**
	 * Constructor. Determine if the id should be <code>null</code> or should not be <code>null</code>.
	 * @param idShouldBeNull boolean.
	 */
	public ExceptionInvalidId(boolean idShouldBeNull) {
		this.idShouldBeNull = idShouldBeNull;
	}

	@Override
	public String getMessage() {
		String message = "The identifier should not be null at this stage";
		if (idShouldBeNull) {
			message = "The identifier should be null at this stage";
		}
		return message;
	}
	
	@Override
	public String getLocalizedMessage() {
		String message = resource.getString(Constants.ID_SHOULD_NOT_BE_NULL);;
		if (idShouldBeNull) {
			message = resource.getString(Constants.ID_SHOULD_BE_NULL);
		}
		return message;
	}
}