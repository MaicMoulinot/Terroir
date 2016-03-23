package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.Pattern.ZONED_DATE_TIME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()} 
 * methods of {@link AttributeConverterZonedDateTime}.
 * @author Maic
 */
public class TestAttributeConverterZonedDateTime {

	private AttributeConverterZonedDateTime converter;

	/**
	 * Set proper ZonedId for the converter.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new AttributeConverterZonedDateTime();
	}

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToDatabaseColumn(ZonedDateTime)} 
	 * with its value null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNull() {
		assertNull(converter.convertToDatabaseColumn(null));
	}

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToDatabaseColumn(ZonedDateTime)} 
	 * with its value not null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNotNull() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals("This method might fail because of rounding", 
				formatTimestampIntoString(Timestamp.from(now.toInstant())), 
				formatTimestampIntoString(converter.convertToDatabaseColumn(now)));
	}

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToEntityAttribute(Timestamp)}, 
	 * with its value null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNull() {
		assertNull(converter.convertToEntityAttribute(null));
	}

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToEntityAttribute(Timestamp)}, 
	 * with its value not null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNotNull() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals("This method might fail because of rounding", 
				formatZonedDateTimeIntoString(now), 
				formatZonedDateTimeIntoString(converter.convertToEntityAttribute(Timestamp.from(now.toInstant()))));
	}

	/**
	 * Format a {@link Timestamp} into a String.
	 * @param date the {@link Timestamp} to format.
	 * @return String the formatted date time.
	 */
	private String formatTimestampIntoString(Timestamp timestamp) {
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(timestamp.toInstant(), Resources.getZonedId());
		return formatZonedDateTimeIntoString(zonedDateTime);
	}

	/**
	 * Format a {@link ZonedDateTime} into a String.
	 * @param zonedDateTime the {@link ZonedDateTime} to format.
	 * @return String the formatted date time.
	 */
	private String formatZonedDateTimeIntoString(ZonedDateTime zonedDateTime) {
		return zonedDateTime.format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getRegex()));
	}
}