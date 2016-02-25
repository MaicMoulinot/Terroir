package com.jomm.terroir.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * This Class is a Converter.
 * <br />The JSF2.2 API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link java.util.Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link Converter}, and converts a {@link String} to/from a {@link ZonedDateTime}, 
 * using the methods <code>getAsObject()</code> and <code>getAsString()</code>.
 * <br />It is annotated {@link FacesConverter} to be registered as a FacesConverter, with its parameter 
 * <code>value="zonedDateTimeConverter"</code> in order to be called in the views using <code>converterId</code>.
 * @author Maic
 */
@FacesConverter(value = "zonedDateTimeConverter")
public final class ConverterZonedDateTime implements Converter {
	
	private static final String ZONED_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss z";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return ZonedDateTime.parse(value, DateTimeFormatter.ofPattern(ZONED_DATE_TIME_PATTERN));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		ZonedDateTime dateValue = (ZonedDateTime) value;
		return dateValue.format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME_PATTERN));
	}
}
