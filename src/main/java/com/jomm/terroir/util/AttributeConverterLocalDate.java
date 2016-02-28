package com.jomm.terroir.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Inject;
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
public final class AttributeConverterLocalDate implements AttributeConverter<LocalDate, Date> {
	
	@Inject
	ZoneId zoneId;

	@Override
	public Date convertToDatabaseColumn(LocalDate entityDate) {
		Date dbDate = null;
		if (entityDate != null) {
			dbDate = Date.from(entityDate.atStartOfDay().atZone(getZoneId()).toInstant());
		}
        return dbDate;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbDate) {
		LocalDate entityDate = null;
		if (dbDate != null) {
			entityDate = LocalDateTime.ofInstant(dbDate.toInstant(), getZoneId()).toLocalDate();
		}
        return entityDate;
	}
	
	/**
	 * This method is for testing purpose as injection isn't fully supported.
	 * @return the {@link ZoneId}.
	 */
	private ZoneId getZoneId() {
		return (zoneId != null) ? zoneId : Resources.getZonedId();
	}
}