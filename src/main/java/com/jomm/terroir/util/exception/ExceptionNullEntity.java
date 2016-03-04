package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_SHOULD_NOT_BE_NULL;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.jomm.terroir.util.BundleError;

/**
 * This Class is an Exception.
 * It is used in Services when working with a {@code null} entity that should not be {@code null}.
 * It extends {@link Exception} and overrides its method {@code getMessage()}.
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
		return resource.getString(USER_SHOULD_NOT_BE_NULL.getKey());
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param resource the resource to set.
	 */
	void setResourceBundle(ResourceBundle resource) {
		this.resource = resource;
	}
}