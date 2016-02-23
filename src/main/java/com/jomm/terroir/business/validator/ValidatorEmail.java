package com.jomm.terroir.business.validator;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.util.BundleError;

/**
 * This Class is the Validator relating to an email.
 * It implements {@link Validator} and defines its method <code>validate()</code>,
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link BundleError} messages,
 * to {@link Pattern} to define a correct email pattern,
 * and to {@link ServiceUser} to check if the email is already in use.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages.
 * @author Maic
 */
//@FacesValidator(value = "validatorEmail")
@Named
public class ValidatorEmail implements Validator {

	// Static constants
	public static final String EXISTING_EMAIL = "emaildoublon";
	public static final String EMAIL_UNVALID = "emailnonvalid";

	// Pattern for password
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
			Pattern.CASE_INSENSITIVE);

	@Inject
	private ServiceUser userService;

	@Inject
	@BundleError
	private ResourceBundle resource;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String email = (String) value;
			if (!email.isEmpty()) {
				if (!EMAIL_PATTERN.matcher(email).matches()) {
					// Email address is unvalid
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(EMAIL_UNVALID), null));
				} else if (userService.isExistingEmail(email)) {
					Object[] argument = {email};
					String detail = MessageFormat.format(resource.getString(EXISTING_EMAIL), argument);
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null));
				}
			}
		}
	}

	/**
	 * This method is used for Junit testing only.
	 * @param resource {@link ResourceBundle} the resource to set.
	 */
	public void setResourceBundle(ResourceBundle resource) {
		this.resource = resource;
	}
}