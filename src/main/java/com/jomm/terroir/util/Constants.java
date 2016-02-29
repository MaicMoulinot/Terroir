package com.jomm.terroir.util;

/**
 * This class provides all constants used in the application.
 * @author Maic
 */
public final class Constants {
	
	// ResourceBundle names
	static final String BUNDLE_MESSAGE = "i18n.message";
	static final String BUNDLE_ERROR = "i18n.error";
	static final String BUNDLE_LABEL = "i18n.label";
	
	// ResourceBundle keys in error
	public static final String FIELD_MANDATORY = "mandatory";
	public static final String LENGTH_AT_LEAST_6_CHARACTERS = "length6";
	public static final String USER_SHOULD_NOT_BE_NULL = "entitynull";
	public static final String ID_SHOULD_NOT_BE_NULL = "idnull";
	public static final String ID_SHOULD_BE_NULL = "idnotnull";
	public static final String EMAIL_EXISTING = "emaildoublon";
	public static final String EMAIL_UNVALID = "emailnonvalid";
	public static final String USER_NAME_EXISTING = "existingusername";
	public static final String PASSWORDS_DONT_MATCH = "passwordsdifferent";
	public static final String PASSWORD_TOO_SIMPLE = "passwordunsecured";
	public static final String PASSWORD_RULES = "passwordrules";
	
	// ResourceBundle keys in message
	public static final String UPDATE_USER = "updateuser";
	public static final String UPDATE_OK = "updateok";
	public static final String DELETE_USER = "deleteuser";
	public static final String DELETE_OK = "deleteok";
	public static final String PASSWORD_TITLE = "passwordtitle";
	public static final String USER_REGISTRED = "usersaved";
	
	// Id in views
	public static final String PASSWORD_PARAMETER = "passwordParam";
	public static final String CLIENT_ID_GROWL = "growl";
	
	// Converter patterns
	static final String LOCAL_DATE_PATTERN = "dd/MM/yyyy";
	static final String ZONED_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss z";
	
	/** Private constructor to prevent instantiation. */
	private Constants() {}
}