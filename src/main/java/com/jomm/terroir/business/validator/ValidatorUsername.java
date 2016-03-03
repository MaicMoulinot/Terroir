package com.jomm.terroir.business.validator;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.Constants;

/**
 * This Class is the Validator relating to an user name.
 * It implements {@link Validator} and defines its method <code>validate()</code>,
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link BundleError} messages,
 * and to {@link ServiceUser} to check if the user name is already in use.
 * It is annotated {@link Named} for proper access from/to the view pages, with
 * <code>f:validator binding="#{validatorUsername}"</code>. It is not yet annotated 
 * {@link javax.faces.validator.FacesValidator} because validators are not injection targets in JSF2.2.
 * @author Maic
 */
@Named
public class ValidatorUsername implements Validator {

	@Inject
	private ServiceUser userService;

	@Inject
	@BundleError
	private ResourceBundle resource;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String userName = (String) value;
			if (!userName.isEmpty()) {
				// Minimum length = 6
				if (userName.length() < 6) {
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, 
									resource.getString(Constants.LENGTH_AT_LEAST_6_CHARACTERS), null));
				}
				// Existing in database
				if (userService.isExistingUserName(userName)) {
					Object[] argument = {userName};
					String detail = MessageFormat.format(resource.getString(Constants.USER_NAME_EXISTING), argument);
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null));
				}
			}
		}
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param resource the resource to set.
	 */
	void setResourceBundle(ResourceBundle resource) {
		this.resource = resource;
	}
}