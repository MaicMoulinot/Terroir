package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.Pattern.LOCAL_DATE;

import java.time.LocalDate;
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
 * <br />This Class implements {@link Converter}, and converts a {@link String} to/from a {@link LocalDate}, 
 * using the methods {@code getAsObject()} and {@code getAsString()}.
 * <br />It is annotated {@link FacesConverter} to be registered as a FacesConverter, with its parameter 
 * {@code value="localDateConverter"} in order to be called in the views using {@code converterId}.
 * @author Maic
 */
@FacesConverter(value = "localDateConverter")
public final class ConverterLocalDate implements Converter {

	@Inject
	private Logger logger;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(LOCAL_DATE.getRegex());
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LocalDate dateAsLocalDate = null;
		if (value != null) {
			try {
				dateAsLocalDate = LocalDate.parse(value, FORMATTER);
			} catch (DateTimeParseException exception) {
				logger.log(Level.FINER, "Value " + value + " could not be parsed into LocalDate", exception);
			}
		}
		return dateAsLocalDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateAsString = null;
		if (value != null) {
			LocalDate dateValue = (LocalDate) value;
			dateAsString = dateValue.format(FORMATTER);
		}
		return dateAsString;
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @return the formatter.
	 */
	static DateTimeFormatter getFormatter() {
		return FORMATTER;
	}
}