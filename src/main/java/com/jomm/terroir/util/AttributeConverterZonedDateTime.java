package com.jomm.terroir.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This Class is a Converter.
 * <br />The current JPA API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link Timestamp}, {@link java.util.Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link AttributeConverter}<{@link ZonedDateTime}, {@link Timestamp}>, and
 * converts a {@link ZonedDateTime} (type of entity attribute) to/from a {@link Timestamp} (type of database column),
 * using the methods <code>convertToDatabaseColumn()</code> and <code>convertToEntityAttribute()</code>.
 * <br />It is annotated {@link Converter} to be registered as a Converter, with its parameter 
 * <code>autoApply=true</code> in order to be applied on each related entity's attributes by default.
 * @author Maic
 */
@Converter(autoApply = true)
public final class AttributeConverterZonedDateTime implements AttributeConverter<ZonedDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(ZonedDateTime entityDateTime) {
		Timestamp dbDateTime = null;
		if (entityDateTime != null) {
			ZonedDateTime now = ZonedDateTime.now();
			dbDateTime = Timestamp.from(now.toInstant());
		}
        return dbDateTime;
	}

	@Override
	public ZonedDateTime convertToEntityAttribute(Timestamp dbDateTime) {
		ZonedDateTime entityDateTime = null;
		if (dbDateTime != null) {
			Instant instant = Instant.ofEpochMilli(dbDateTime.getTime());
			entityDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		}
        return entityDateTime;
	}
}
