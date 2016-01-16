package com.jomm.terroir.business.validator;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
public class UsernameValidator implements Validator {

	// Static constants
	private static final String I18N_ERROR = "i18n.error";
	private static final String EXISTING_USER_NAME = "existingusername";
	private static final String LENGHT_AT_LEAST_6_CHARACTERS = "lenght6";

	@Inject
	private UserDaoInterface userDao;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String userName = (String) value;
			ResourceBundle resource = ResourceBundle.getBundle(I18N_ERROR, Locale.getDefault());
			if (userName.length() < 6) {
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, 
								resource.getString(LENGHT_AT_LEAST_6_CHARACTERS), null));
			}
			if (userDao.isExistingUserName(userName)) {
				String detail = MessageFormat.format(resource.getString(EXISTING_USER_NAME), userName);
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null));
			}
		} catch (Exception exception) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(component.getClientId(facesContext), message);
		}
	}
}
