package com.jomm.terroir.business.validator;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 * @author Maic
 *
 */
@Named
public class PasswordValidator implements Validator {

	// Static constants
	private static final String PASSWORD_1 = "password1";
	private static final String I18N_ERROR = "i18n.error";
    private static final String PASSWORDS_DONT_MATCH = "passwordsdifferent";
    private static final String FIELD_MANDATORY = "mandatory";
    private static final String PASSWORD_TOO_SIMPLE = "passwordunsecured";
    private static final String PASSWORD_RULES = "passwordrules";
    
    // Pattern for password
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	// (?=.*\d)		#   must contain one digit from 0-9
	// (?=.*[a-z])	#   must contain one lowercase character
	// (?=.*[A-Z])	#   must contain one uppercase character
	// (?=.*[@#$%])	#   must contain one special symbol in the list "@#$%"
	// .			#	match all previous conditions with
	// {6,20}		#	length is minimum of 6 characters and maximum of 20 characters

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String password1 = (String) ((UIInput) component.getAttributes().get(PASSWORD_1)).getValue();
			String password2 = (String) value;
			ResourceBundle resource = ResourceBundle.getBundle(I18N_ERROR, Locale.getDefault());
			if (password1 == null || password2 == null) {
				// One password at least is lacking
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(FIELD_MANDATORY), null));
			} else if (!password2.equals(password1)) {
				// Passwords don't match
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(PASSWORDS_DONT_MATCH), null));
			} else {
				// Password doesn't match pattern
				Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
				if (!pattern.matcher(password1).matches()) {
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(PASSWORD_TOO_SIMPLE), 
									resource.getString(PASSWORD_RULES)));
				}
			}
		} catch(Exception exception) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));
		}
	}
}
