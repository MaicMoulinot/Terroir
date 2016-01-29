package com.jomm.terroir.business.validator;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.UserService;
import com.jomm.terroir.util.Error;

/**
 * This Class is the Validator relating to an email.
 * It implements {@link Validator} and defines its method validate(),
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link Error} messages,
 * to {@link Pattern} to define a correct email pattern,
 * and to {@link UserService} to check if the email is already in use.
 * It is annotated {@link Named} for proper access from/to the view pages.
 * @author Maic
 */
@Named
public class EmailValidator implements Validator {
	
	// Static constants
	private static final String EXISTING_EMAIL = "emaildoublon";
    private static final String FIELD_MANDATORY = "mandatory";
    private static final String EMAIL_UNVALID = "emailnonvalid";
	
    // Pattern for password
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
			Pattern.CASE_INSENSITIVE);
    
    @Inject
    private UserService userService;
    
    @Inject
    @Error
    private ResourceBundle resource;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String email = (String) value;
			if (email == null) {
				// Email address is lacking
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(FIELD_MANDATORY), null));
			} else if (!EMAIL_PATTERN.matcher(email).matches()) {
				// Email address is unvalid
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(EMAIL_UNVALID), null));
			} else if (userService.isExistingUserName(email)) {
				Object[] argument = {email};
    			String detail = MessageFormat.format(resource.getString(EXISTING_EMAIL), argument);
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null));
            }
		} catch(Exception exception) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));
		}
    }
}
