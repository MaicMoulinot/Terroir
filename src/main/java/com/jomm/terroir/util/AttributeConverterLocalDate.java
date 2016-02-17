/**
 * 
 */
package com.jomm.terroir.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This Class is a Converter.
 * <br />The current JPA API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link AttributeConverter}<{@link LocalDate}, {@link Date}>, and
 * converts a {@link LocalDate} (type of entity attribute) to/from a {@link Date} (type of database column),
 * using the methods <code>convertToDatabaseColumn()</code> and <code>convertToEntityAttribute()</code>.
 * <br />It is annotated {@link Converter} to be registered as a Converter, with its parameter 
 * <code>autoApply=true</code> in order to be applied on each related entity's attributes by default.
 * @author Maic
 */
@Converter(autoApply = true)
public class AttributeConverterLocalDate implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		LocalTime now = LocalTime.now();
        Instant instant = now.atDate(attribute).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		Instant instant = Instant.ofEpochMilli(dbData.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
}