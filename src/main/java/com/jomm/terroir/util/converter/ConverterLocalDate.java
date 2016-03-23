package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.Pattern.LOCAL_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LocalDate dateAsLocalDate = null;
		if (value != null) {
			dateAsLocalDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(LOCAL_DATE.getRegex()));
		}
		return dateAsLocalDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateAsString = null;
		if (value != null) {
			LocalDate dateValue = (LocalDate) value;
			dateAsString = dateValue.format(DateTimeFormatter.ofPattern(LOCAL_DATE.getRegex()));
		}
		return dateAsString;
	}
}