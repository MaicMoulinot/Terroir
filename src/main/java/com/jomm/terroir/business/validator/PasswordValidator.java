package com.jomm.terroir.business.validator;

import java.util.Locale;
import java.util.ResourceBundle;

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
    private static final String PASSWORDS_DONT_MATCH = "differentpasswords";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password1 = (String) ((UIInput) component.getAttributes().get(PASSWORD_1)).getValue();
		String password2 = (String) value;
		if (password2 != null && !password2.equals(password1)) {
			ResourceBundle resource = ResourceBundle.getBundle(I18N_ERROR, Locale.getDefault());
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(PASSWORDS_DONT_MATCH), null));

		}
	}
}
