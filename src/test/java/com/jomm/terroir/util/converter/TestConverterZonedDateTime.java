package com.jomm.terroir.util.converter;

import static com.jomm.terroir.util.Constants.ConverterPattern.ZONED_DATE_TIME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class is a Junit test case testing the {@code getAsObject()} and {@code getAsString()} 
 * methods of {@link ConverterZonedDateTime}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConverterZonedDateTime {
	
	@Mock
    private FacesContext context;
	
	@Mock
    private UIComponent component;
	
	private ConverterZonedDateTime converter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new ConverterZonedDateTime();
	}

	/**
	 * Test method for {@link ConverterZonedDateTime#getAsObject(FacesContext, UIComponent, String)} with its value null.
	 */
	@Test
	public final void testGetAsObjectWithValueNull() {
		assertNull(converter.getAsObject(context, component, null));
	}
	
	/**
	 * Test method for {@link ConverterZonedDateTime#getAsObject(FacesContext, UIComponent, String)} with its value not null.
	 */
	@Test
	public final void testGetAsObjectWithValueNotNull() {
		String value = "27/02/2016 23:52:36 GMT";
		assertEquals("This method should never fail because of rounding", 
				ZonedDateTime.parse(value, DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getPattern())), 
				converter.getAsObject(context, component, value));
	}
	
	/**
	 * Test method for {@link ConverterZonedDateTime#getAsString(FacesContext, UIComponent, Object)}, with its value null.
	 */
	@Test
	public final void testGetAsStringWithValueNull() {
		assertNull(converter.getAsString(context, component, null));
	}

	/**
	 * Test method for {@link ConverterZonedDateTime#getAsString(FacesContext, UIComponent, Object)}, with its value not null.
	 */
	@Test
	public final void testGetAsStringWithValueNotNull() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals("This method should never fail because of rounding", 
				now.format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME.getPattern())), 
				converter.getAsString(context, component, now));
	}
}