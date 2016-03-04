package com.jomm.terroir.util.converter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This Class is a Converter.
 * <br />The current JPA API does not work with the Java 8 Date/Time API by default since it was built to work with 
 * {@link Timestamp}, {@link java.util.Date}, and other old Java Date/Time APIs.
 * <br />This Class implements {@link AttributeConverter}<{@link ZonedDateTime}, {@link Timestamp}>, and
 * converts a {@link ZonedDateTime} (type of entity attribute) to/from a {@link Timestamp} (type of database column),
 * using the methods {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()}.
 * <br />It is annotated {@link Converter} to be registered as a Converter, with its parameter 
 * {@code autoApply=true} in order to be applied on each related entity's attributes by default.
 * @author Maic
 */
@Converter(autoApply = true)
public final class AttributeConverterZonedDateTime implements AttributeConverter<ZonedDateTime, Timestamp> {

	@Inject
	private ZoneId zoneId;
	
	@Override
	public Timestamp convertToDatabaseColumn(ZonedDateTime entityDateTime) {
		Timestamp dbDateTime = null;
		if (entityDateTime != null) {
			dbDateTime = Timestamp.from(ZonedDateTime.now().toInstant());
		}
        return dbDateTime;
	}

	@Override
	public ZonedDateTime convertToEntityAttribute(Timestamp dbDateTime) {
		ZonedDateTime entityDateTime = null;
		if (dbDateTime != null) {
			entityDateTime = ZonedDateTime.ofInstant(dbDateTime.toInstant(), zoneId);
		}
        return entityDateTime;
	}
	
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @return the zoneId
	 */
	ZoneId getZoneId() {
		return zoneId;
	}

	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param zoneId the zoneId to set
	 */
	void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}
}