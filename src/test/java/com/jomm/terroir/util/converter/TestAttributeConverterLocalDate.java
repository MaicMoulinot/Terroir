package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.ConverterPattern.LOCAL_DATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()} 
 * methods of {@link AttributeConverterLocalDate}.
 * @author Maic
 */
public class TestAttributeConverterLocalDate {

	private AttributeConverterLocalDate converter;
	
	/**
	 * Set proper ZonedId for the converter.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new AttributeConverterLocalDate();
	}

	/**
	 * Test method for {@link AttributeConverterLocalDate#convertToDatabaseColumn(LocalDate)} with its value null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNull() {
		assertNull(converter.convertToDatabaseColumn(null));
	}
	
	/**
	 * Test method for {@link AttributeConverterLocalDate#convertToDatabaseColumn(LocalDate)} with its value not null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNotNull() {
		Date fromJavaUtil = new Date();
		Date fromConverter = converter.convertToDatabaseColumn(LocalDate.now());
		assertEquals("This method should never fail because of rounding", 
				formatDateIntoString(fromJavaUtil), formatDateIntoString(fromConverter));
	}
	
	/**
	 * Test method for {@link AttributeConverterLocalDate#convertToEntityAttribute(Date)}, with its value null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNull() {
		assertNull(converter.convertToEntityAttribute(null));
	}

	/**
	 * Test method for {@link AttributeConverterLocalDate#convertToEntityAttribute(Date)}, with its value not null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNotNull() {
		assertEquals("This method should never fail because of rounding", 
				formatLocalDateIntoString(LocalDate.now()), 
				formatLocalDateIntoString(converter.convertToEntityAttribute(new Date())));
	}
	
	/**
	 * Format a {@link Date} into a String.
	 * @param date the {@link Date} to format.
	 * @return String the formatted date.
	 */
	private String formatDateIntoString(Date date) {
		LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), Resources.getZonedId()).toLocalDate();
		return formatLocalDateIntoString(localDate);
	}
	
	/**
	 * Format a {@link LocalDate} into a String.
	 * @param localDate the {@link LocalDate} to format.
	 * @return String the formatted date.
	 */
	private String formatLocalDateIntoString(LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern(LOCAL_DATE.getPattern()));
	}
}