package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.ConverterPattern.ZONED_DATE_TIME;

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
 * using the methods {@code getAsObject()} and {@code getAsString()}.
 * <br />It is annotated {@link FacesConverter} to be registered as a FacesConverter, with its parameter 
 * {@code value="zonedDateTimeConverter"} in order to be called in the views using {@code converterId}.
 * @author Maic
 */
@FacesConverter(value = "zonedDateTimeConverter")
public final class ConverterZonedDateTime implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ZonedDateTime dateAsZonedDateTime = null;
		if (value != null) {
			dateAsZonedDateTime = ZonedDateTime.parse(value, 
					DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getPattern()));
		}
		return dateAsZonedDateTime;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateAsString = null;
		if (value != null) {
			ZonedDateTime dateValue = (ZonedDateTime) value;
			dateAsString = dateValue.format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getPattern()));
		}
		return dateAsString;
	}
}