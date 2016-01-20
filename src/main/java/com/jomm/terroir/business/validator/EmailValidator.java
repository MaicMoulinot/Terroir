package com.jomm.terroir.business.validator;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.dao.UserDaoInterface;

/**
 * @author Maic
 *
 */
@Named
public class EmailValidator implements Validator {
	
	// Static constants
	private static final String I18N_ERROR = "i18n.error";
	private static final String EXISTING_EMAIL = "emaildoublon";
    private static final String FIELD_MANDATORY = "mandatory";
    private static final String EMAIL_UNVALID = "emailnonvalid";
	
    // Pattern for password
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
			Pattern.CASE_INSENSITIVE);
    
    @Inject
    private UserDaoInterface userDao;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String email = (String) value;
			ResourceBundle resource = ResourceBundle.getBundle(I18N_ERROR, Locale.getDefault());
			if (email == null) {
				// Email address is lacking
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(FIELD_MANDATORY), null));
			} else if (!EMAIL_PATTERN.matcher(email).matches()) {
				// Email address is unvalid
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(EMAIL_UNVALID), null));
			} else if (userDao.isExistingEmail(email)) {
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
