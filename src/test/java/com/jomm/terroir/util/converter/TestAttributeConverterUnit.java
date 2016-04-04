package com.jomm.terroir.util.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Constants.Unit;

/**
 * This class is a Junit test case testing the {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()} 
 * methods of {@link AttributeConverterUnit}.
 * @author Maic
 */
public class TestAttributeConverterUnit {
	
	// Variables //-----------------------------------------------
	private AttributeConverterUnit converter;

	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new AttributeConverterUnit();
	}

	/**
	 * Test method for {@link AttributeConverterUnit#convertToDatabaseColumn(Unit)} 
	 * with its value null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNull() {
		assertNull(converter.convertToDatabaseColumn(null));
	}

	/**
	 * Test method for {@link AttributeConverterUnit#convertToDatabaseColumn(Unit)} 
	 * with its value not null.
	 */
	@Test
	public final void testConvertToDatabaseColumnWithValueNotNull() {
		Unit unit = Unit.GRAM;
		assertEquals("The symbol should be", unit.getSymbol(), converter.convertToDatabaseColumn(unit));
	}

	/**
	 * Test method for {@link AttributeConverterUnit#convertToEntityAttribute(String)}, 
	 * with its value null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNull() {
		assertNull(converter.convertToEntityAttribute(null));
	}

	/**
	 * Test method for {@link AttributeConverterUnit#convertToEntityAttribute(String)}, 
	 * with its value not null.
	 */
	@Test
	public final void testConvertToEntityAttributeWithValueNotNull() {
		String symbol = Unit.GRAM.getSymbol();
		assertEquals("The value should be", Unit.getValue(symbol), converter.convertToEntityAttribute(symbol));
	}
}