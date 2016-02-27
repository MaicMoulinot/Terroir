package com.jomm.terroir.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class is a Junit test case testing the <code>convertToDatabaseColumn()</code> and <code>convertToEntityAttribute()</code> 
 * methods of {@link AttributeConverterZonedDateTime}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestAttributeConverterZonedDateTime {

	@InjectMocks
	private AttributeConverterZonedDateTime converter;

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToDatabaseColumn(ZonedDateTime)} with its value null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNull() {
		assertNull(converter.convertToDatabaseColumn(null));
	}
	
	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToDatabaseColumn(ZonedDateTime)} with its value not null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNotNull() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals(Timestamp.from(now.toInstant()), converter.convertToDatabaseColumn(now));
	}
	
	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToEntityAttribute(Timestamp)}, with its value null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNull() {
		assertNull(converter.convertToEntityAttribute(null));
	}

	/**
	 * Test method for {@link AttributeConverterZonedDateTime#convertToEntityAttribute(Timestamp)}, with its value not null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNotNull() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals(now, converter.convertToEntityAttribute(Timestamp.from(now.toInstant())));
	}
}