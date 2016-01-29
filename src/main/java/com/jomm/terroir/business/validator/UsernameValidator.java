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

import com.jomm.terroir.business.UserServiceInterface;
import com.jomm.terroir.dao.UserDaoInterface;
import com.jomm.terroir.util.Error;

/**
 * This Class is the Validator relating to an user name.
 * It implements {@link Validator} and defines its method validate(),
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link Error} messages,
 * and to {@link UserServiceInterface} to check if the user name is already in use.
 * It is annotated {@link Named} for proper access from/to the view pages.
 * @author Maic
 */
@Named
public class UsernameValidator implements Validator {

	// Static constants
	private static final String EXISTING_USER_NAME = "existingusername";
	private static final String LENGHT_AT_LEAST_6_CHARACTERS = "lenght6";

	@Inject
	private UserDaoInterface userDao;
	
	@Inject
	@Error
	private ResourceBundle resource;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String userName = (String) value;
			if (userName.length() < 6) {
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, 
								resource.getString(LENGHT_AT_LEAST_6_CHARACTERS), null));
			}
			if (userDao.isExistingUserName(userName)) {
				Object[] argument = {userName};
				String detail = MessageFormat.format(resource.getString(EXISTING_USER_NAME), argument);
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null));
			}
		} catch (Exception exception) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));
		}
	}
}
