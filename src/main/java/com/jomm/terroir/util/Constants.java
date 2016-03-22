package com.jomm.terroir.util;

/**
 * This class provides all constants used in the application.
 * @author Maic
 */
public final class Constants {

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
		/** "emailnonvalid" */
		EMAIL_INVALID("emailnonvalid"),
		/** "exception" */
		EXCEPTION("exception"),
		/** "mandatory" */
		FIELD_MANDATORY("mandatory"),
		/** "idnotnull" */
		ID_SHOULD_BE_NULL("idnotnull"),
		/** "idnull" */
		ID_SHOULD_NOT_BE_NULL("idnull"),
		/** "length6" */
		LENGTH_AT_LEAST_6_CHARACTERS("length6"),
		/** "passwordsdifferent" */
		PASSWORDS_DONT_MATCH("passwordsdifferent"),
		/** "passwordunsecured" */
		PASSWORD_TOO_SIMPLE("passwordunsecured"),
		/** "usernameduplicate" */
		USER_NAME_EXISTING("usernameduplicate"),
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
	 * This enumeration stores all the id and binding in the views.
	 * @author Maic
	 */
	public enum View {
		/** "passwordParam" */
		PASSWORD_PARAMETER("passwordParam"),
		/** "growl" */
		CLIENT_ID_GROWL("growl");
		
		private String id;
		
		/**
		 * Constructor.
		 * @param id String the id.
		 */
		private View(String id) {
			this.id = id;
		}

		/**
		 * Get the appropriate id or binding used in the view.
		 * @return the id
		 */
		public String getId() {
			return id;
		}
	}
	
	/**
	 * This enumeration stores all patterns for date/time converters.
	 * @author Maic
	 */
	public enum ConverterPattern {
		/** "dd/MM/yyyy" */
		LOCAL_DATE("dd/MM/yyyy"),
		/** "dd/MM/yyyy HH:mm:ss z" */
		ZONED_DATE_TIME("dd/MM/yyyy HH:mm:ss z");
		
		private String pattern;
		
		/**
		 * Constructor.
		 * @param pattern String the pattern.
		 */
		private ConverterPattern(String pattern) {
			this.pattern = pattern;
		}

		/**
		 * Get the appropriate pattern used in the date/time converter.
		 * @return the pattern
		 */
		public String getPattern() {
			return pattern;
		}
	}

	/** Private constructor to prevent instantiation. */
	private Constants() {}
}