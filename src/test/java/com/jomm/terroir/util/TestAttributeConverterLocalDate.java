package com.jomm.terroir.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class is a Junit test case testing the <code>convertToDatabaseColumn()</code> and <code>convertToEntityAttribute()</code> 
 * methods of {@link AttributeConverterLocalDate}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestAttributeConverterLocalDate {

	@InjectMocks
	private AttributeConverterLocalDate converter;
	
	/**
	 * Set proper ZonedId for the converter.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter.zoneId = Resources.getZonedId();
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
		LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), converter.zoneId).toLocalDate();
		return formatLocalDateIntoString(localDate);
	}
	
	/**
	 * Format a {@link LocalDate} into a String.
	 * @param localDate the {@link LocalDate} to format.
	 * @return String the formatted date.
	 */
	private String formatLocalDateIntoString(LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_PATTERN));
	}
}