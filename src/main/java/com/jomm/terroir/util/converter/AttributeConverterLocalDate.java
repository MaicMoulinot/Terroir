package com.jomm.terroir.util.converter;

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
 * using the methods {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()}.
 * <br />It is annotated {@link Converter} to be registered as a Converter, with its parameter 
 * {@code autoApply=true} in order to be applied on each related entity's attributes by default.
 * @author Maic
 */
@Converter(autoApply = true)
public final class AttributeConverterLocalDate implements AttributeConverter<LocalDate, Date> {
	
	@Inject
	private ZoneId zoneId;

	@Override
	public Date convertToDatabaseColumn(LocalDate entityDate) {
		Date dbDate = null;
		if (entityDate != null) {
			dbDate = Date.from(entityDate.atStartOfDay().atZone(zoneId).toInstant());
		}
        return dbDate;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbDate) {
		LocalDate entityDate = null;
		if (dbDate != null) {
			entityDate = LocalDateTime.ofInstant(dbDate.toInstant(), zoneId).toLocalDate();
		}
        return entityDate;
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