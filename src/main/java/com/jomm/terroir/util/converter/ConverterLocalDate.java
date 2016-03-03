package com.jomm.terroir.util.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.jomm.terroir.util.Constants;

/**
 * This Class is a Converter.
 * <br />The JSF2.2 API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link java.util.Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link Converter}, and converts a {@link String} to/from a {@link LocalDate}, 
 * using the methods <code>getAsObject()</code> and <code>getAsString()</code>.
 * <br />It is annotated {@link FacesConverter} to be registered as a FacesConverter, with its parameter 
 * <code>value="localDateConverter"</code> in order to be called in the views using <code>converterId</code>.
 * @author Maic
 */
@FacesConverter(value = "localDateConverter")
public final class ConverterLocalDate implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LocalDate dateAsLocalDate = null;
		if (value != null) {
			dateAsLocalDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_PATTERN));
		}
		return dateAsLocalDate;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateAsString = null;
		if (value != null) {
			LocalDate dateValue = (LocalDate) value;
			dateAsString = dateValue.format(DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_PATTERN));
		}
		return dateAsString;
	}
}