package com.jomm.terroir.util;

/**
 * This class provides all constants used in the application.
 * @author Maic
 */
public final class Constants {
	
	public static final String PERSISTENCE_UNIT = "terroirPU";
	
	/**
	 * This enumeration stores all {@link java.util.ResourceBundle} file names.
	 * @author Maic
	 */
	public enum ResourceBundleFileName {
		/** "i18n.error" */
		ERROR("i18n.error"),
		/** "i18n.label" */
		LABEL("i18n.label"),
		/** "i18n.message" */
		MESSAGE("i18n.message");

		private String fileName;

		/**
		 * Constructor.
		 * @param fileName String the file name.
		 */
		private ResourceBundleFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * Get the appropriate file name where the {@link java.util.ResourceBundle} is stored.
		 * @return String the file name.
		 */
		public String getFileName() {
			return fileName;
		}
	}

	/**
	 * This enumeration stores all {@link java.util.ResourceBundle} keys for errors.
	 * @author Maic
	 */
	public enum ResourceBundleError {
		/** "emailduplicate" */
		EMAIL_EXISTING("emailduplicate"),
		/** "emailinvalid" */
		EMAIL_NOT_MATCHING_PATTERN("emailinvalid"),
		/** "exception" */
		EXCEPTION("exception"),
		/** "mandatory" */
		FIELD_MANDATORY("mandatory"),
		/** "idnotnull" */
		ID_SHOULD_BE_NULL("idnotnull"),
		/** "idnull" */
		ID_SHOULD_NOT_BE_NULL("idnull"),
		/** "length5and15" */
		LENGTH_BETWEEN_5_AND_15("length5and15"),
		/** "passwordsdifferent" */
		PASSWORDS_DONT_MATCH("passwordsdifferent"),
		/** "passwordinvalid" */
		PASSWORD_NOT_MATCHING_PATTERN("passwordinvalid"),
		/** "usernameduplicate" */
		USER_NAME_EXISTING("usernameduplicate"),
		/** "usernameinvalid" */
		USER_NAME_NOT_MATCHING_PATTERN("usernameinvalid"),
		/** "entitynull" */
		ENTITY_SHOULD_NOT_BE_NULL("entitynull");

		private String key;

		/**
		 * Constructor.
		 * @param key String the key.
		 */
		private ResourceBundleError(String key) {
			this.key = key;
		}

		/**
		 * Get the appropriate key for the {@link java.util.ResourceBundle} Error.
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
	}

	/**
	 * This enumeration stores all {@link java.util.ResourceBundle} keys for messages.
	 * @author Maic
	 */
	public enum ResourceBundleMessage {
		/** "createok" */
		CREATE_OK("createok"),
		/** "createuser" */
		CREATE_USER("createuser"),
		/** "deleteok" */
		DELETE_OK("deleteok"),
		/** "deleteuser" */
		DELETE_USER("deleteuser"),
		/** "passwordtitle" */
		PASSWORD_TITLE("passwordtitle"),
		/** "passwordrules" */
		PASSWORD_RULES("passwordrules"),
		/** "updateok" */
		UPDATE_OK("updateok"),
		/** "updateuser" */
		UPDATE_USER("updateuser");

		private String key;

		/**
		 * Constructor.
		 * @param key String the key.
		 */
		private ResourceBundleMessage(String key) {
			this.key = key;
		}

		/**
		 * Get the appropriate key for the {@link java.util.ResourceBundle} Message.
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
	}
	
	/**
	 * This enumeration stores useful parameters (e.g. id and binding) in the views.
	 * @author Maic
	 */
	public enum View {
		/** "parameter" */
		PARAMETER,
		/** "growl" */
		GROWL;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	/**
	 * This enumeration stores all patterns.
	 * @author Maic
	 */
	public enum Pattern {
		/** Pattern used to validate an email address.
		 * The complete pattern is {@code ^([a-z0-9._%+-]+)(@{1})([a-z0-9.-]+)(\\.[a-z0-9]+)*(\\.[a-z]{2,})$},
		 * which can be explained as follow:
		 * <ul><li>{@code ^}			# beginning of the regex</li>
		 * <li>{@code ([a-z0-9._%+-]+)}	# identifier: the first part has a length of at least 1, and matches characters 
		 * and symbols in the list: a-z, 0-9, dot (.), underscore (_), percentage (%), plus (+) and minus (-),</li>
		 * <li>{@code @{1}				# at: the second part is the character at (@)</li>
		 * <li>{@code ([a-z0-9.-]+)}	# domain: the third part has a length of at least 1, and matches characters and 
		 * symbols in the list: a-z, 0-9, dot (.) and hyphen (-),</li>
		 * <li>{@code (\\.[a-z0-9]+)*}	# ccTLD: the fourth part is optional. If present, it has a length of at least 1, 
		 * it starts with the character dot (.), and then matches characters in the list: a-z, 0-9,</li>
		 * <li>{@code (\\.[a-z]{2,})}	# ccTLD: the firth part has a length of at least 2, it starts with the character 
		 * dot (.), and then matches characters in the list: a-z</li>
		 * <li>{@code $}				# end of the regex.</li></ul>
		 */
		EMAIL("^([a-z0-9._%+-]+)(@{1})([a-z0-9.-]+)(\\.[a-z0-9]+)*(\\.[a-z]{2,})$"),
		/** Pattern used to validate a password that is secured enough.
		 * The complete pattern is {@code ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$},
		 * which can be explained as follow:
		 * <ul><li>{@code ^}			beginning of the regex</li>
		 * <li>{@code (?=.*[0-9])}		must contain at least one digit from 0-9,</li>
		 * <li>{@code (?=.*[a-z])}		must contain at least one lower case character,</li>
		 * <li>{@code (?=.*[A-Z])}		must contain at least one upper case character,</li>
		 * <li>{@code (?=.*[@#$%^&+=])}	must contain at least one symbol in the list: at (@), sharp (#), dollar ($), 
		 * percentage (%), caret (^), ampersand (&), plus (+) and equals (=),</li>
		 * <li>{@code (?=\\S+$)}		must not contain any space character ( ),</li>
		 * <li>{@code .{6,}				the length is minimum 6 characters,</li>
		 * <li>{@code $}				end of the regex.</li></ul>
		 */
		PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"),
		/** Pattern used to validate a user name.
		 * The complete pattern is {@code ^[a-zA-Z0-9._@#$%\\-]{5,15}$},
		 * which can be explained as follow:
		 * <ul><li>{@code ^}				beginning of the regex</li>
		 * <li>{@code [a-zA-Z0-9._@#$%\\-]}	matches characters and symbols in the list: a-z, A-Z, 0-9, dot (.), 
		 * underscore (_), at (@), sharp (#), dollar ($), percentage (%) and hyphen (-),</li>
		 * <li>{@code {5,15}				the length is minimum 5 and maximum 15 characters,</li>
		 * <li>{@code $}					end of the regex.</li></ul>
		 */
		USERNAME("^[a-zA-Z0-9._@#$%\\-]{5,15}$"),
		/** Pattern used in date converter: {@code dd/MM/yyyy}. */
		LOCAL_DATE("dd/MM/yyyy"),
		/** Pattern used in date time converter: {@code dd/MM/yyyy HH:mm:ss z}. */
		ZONED_DATE_TIME("dd/MM/yyyy HH:mm:ss z");

		private String regex;

		/**
		 * Constructor.
		 * @param regex String the regex.
		 */
		private Pattern(String regex) {
			this.regex = regex;
		}

		/**
		 * Get the appropriate regex.
		 * @return the pattern
		 */
		public String getRegex() {
			return regex;
		}
	}

	/** Private constructor to prevent instantiation. */
	private Constants() {}
}