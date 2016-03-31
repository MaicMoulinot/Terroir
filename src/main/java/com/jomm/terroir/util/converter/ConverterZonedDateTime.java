package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.Pattern.ZONED_DATE_TIME;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * This Class is a Converter.
 * <br />The JSF2.2 API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link java.util.Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link Converter}, and converts a {@link String} to/from a {@link ZonedDateTime}, 
 * using the methods {@code getAsObject()} and {@code getAsString()}.
 * <br />It is annotated {@link FacesConverter} to be registered as a FacesConverter, with its parameter 
 * {@code value="zonedDateTimeConverter"} in order to be called in the views using {@code converterId}.
 * @author Maic
 */
@FacesConverter(value = "zonedDateTimeConverter")
public final class ConverterZonedDateTime implements Converter {
	
	// Constants //-----------------------------------------------
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getRegex());
	
	// Injected Fields //-----------------------------------------
	@Inject
	private Logger logger;
	
	// Methods //-------------------------------------------------
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ZonedDateTime dateAsZonedDateTime = null;
		try {
			dateAsZonedDateTime = ZonedDateTime.parse(value, FORMATTER);
		} catch (NullPointerException | DateTimeParseException exception) {
			logger.log(Level.FINER, "Value " + value + " could not be parsed into ZonedDateTime", exception);
		}
		return dateAsZonedDateTime;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateAsString = null;
		try {
			ZonedDateTime dateValue = (ZonedDateTime) value;
			dateAsString = dateValue.format(FORMATTER);
		} catch (ClassCastException | NullPointerException | DateTimeException exception) {
			logger.log(Level.FINER, "Value " + (value == null ? value : value.toString()) 
					+ " could not be formatted into a String", exception);
		}
		return dateAsString;
	}
	
	// Tests //---------------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @return the formatter.
	 */
	static DateTimeFormatter getFormatter() {
		return FORMATTER;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param logger the logger to set.
	 */
	void setLogger(Logger logger) {
		this.logger = logger;
	}
}