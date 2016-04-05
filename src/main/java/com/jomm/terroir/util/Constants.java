package com.jomm.terroir.util;

/**
 * This class provides all constants used in the application.
 * @author Maic
 */
public final class Constants {

	// Constants //-----------------------------------------------
	public static final String PERSISTENCE_UNIT = "terroirPU";

	// Enumerations //--------------------------------------------	
	/**
	 * This enumeration stores the list of all entities in the model.
	 * @author Maic
	 */
	public enum Entity {
		/** Represents {@link com.jomm.terroir.business.model.Administrator}. */
		ADMINISTRATOR,
		/** Represents {@link com.jomm.terroir.business.model.Category}. */
		CATEGORY,
		/** Represents {@link com.jomm.terroir.business.model.Customer}. */
		CUSTOMER,
		/** Represents {@link com.jomm.terroir.business.model.Designation}. */
		DESIGNATION,
		/** Represents {@link com.jomm.terroir.business.model.Enterprise}. */
		ENTERPRISE,
		/** Represents {@link com.jomm.terroir.business.model.Image}. */
		IMAGE,
		/** Represents {@link com.jomm.terroir.business.model.Label}. */
		LABEL,
		/** Represents {@link com.jomm.terroir.business.model.Product}. */
		PRODUCT,
		/** Represents {@link com.jomm.terroir.business.model.Seller}. */
		SELLER,
		/** Represents {@link com.jomm.terroir.business.model.Site}. */
		SITE;

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

	/**
	 * This enumeration stores all {@link java.util.ResourceBundle} keys for errors.
	 * @author Maic
	 */
	public enum ResourceBundleError {
		/** "designationinvalid" */
		DESIGNATION_INVALID("designationinvalid"),
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
		/** "integer" */
		INTEGER("integer"),
		/** "length5and15" */
		LENGTH_BETWEEN_5_AND_15("length5and15"),
		/** "passwordsdifferent" */
		PASSWORDS_DONT_MATCH("passwordsdifferent"),
		/** "passwordinvalid" */
		PASSWORD_NOT_MATCHING_PATTERN("passwordinvalid"),
		/** "priceoutofrange" */
		PRICE_OUT_OF_RANGE("priceoutofrange"),
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
	 * This enumeration stores all {@link java.util.ResourceBundle} keys for messages.
	 * @see {@link Entity} for messages related to an entity.
	 * @author Maic
	 */
	public enum ResourceBundleMessage {
		/** "createok" */
		CREATE_OK("createok"),
		/** "create" */
		CREATE("create"),
		/** "deleteok" */
		DELETE_OK("deleteok"),
		/** "delete" */
		DELETE("delete"),
		/** "passwordtitle" */
		PASSWORD_TITLE("passwordtitle"),
		/** "passwordrules" */
		PASSWORD_RULES("passwordrules"),
		/** "updateok" */
		UPDATE_OK("updateok"),
		/** "update" */
		UPDATE("update");

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
	 * This enumeration stores the list of all possible units used to describe a product.
	 * @author Maic
	 */
	public enum Unit {
		/** A piece (short name: p). */
		PIECE("p"),
		/** A milligram (SI unit symbol: mg). */
		MILLIGRAM("mg"),
		/** A gram (SI unit symbol: g). */
		GRAM("g"),
		/** A kilogram (SI unit symbol: kg). */
		KILOGRAM("kg"),
		/** A liter (SI unit symbol: l). */
		LITER("l"),
		/** A milliliter (SI unit symbol: ml). */
		MILLILITER("ml");

		private String symbol;

		/**
		 * Constructor.
		 * @param symbol String the symbol.
		 */
		private Unit(String symbol) {
			this.symbol = symbol;
		}

		/**
		 * Get the unit symbol.
		 * @return String the symbol.
		 */
		public String getSymbol() {
			return symbol;
		}

		/**
		 * Get the appropriate value using the symbol.
		 * @param symbol String the symbol.
		 * @return the {@code Unit}'s value if it has been found, {@code null} otherwise.
		 */
		public static Unit getValue(String symbol) {
			Unit value = null;
			if (symbol != null && !symbol.isEmpty()) {
				for (Unit unit : values()) {
					if (unit.getSymbol().matches(symbol)) {
						value = unit;
						break;
					}
				}
			}
			return value;
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


	// Constructors //--------------------------------------------
	/** Private constructor to prevent instantiation. */
	private Constants() {}
}