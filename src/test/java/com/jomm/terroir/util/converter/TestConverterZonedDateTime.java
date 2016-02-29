package com.jomm.terroir.util.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.converter.ConverterZonedDateTime;

/**
 * This class is a Junit test case testing the <code>getAsObject()</code> and <code>getAsString()</code> 
 * methods of {@link ConverterZonedDateTime}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConverterZonedDateTime {
	
	@Mock(name = "context")
    private FacesContext context;
	
	@Mock(name = "component")
    private UIComponent component;
	
	@InjectMocks
	private ConverterZonedDateTime converter;

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
				ZonedDateTime.parse(value, DateTimeFormatter.ofPattern(Constants.ZONED_DATE_TIME_PATTERN)), 
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
				now.format(DateTimeFormatter.ofPattern(Constants.ZONED_DATE_TIME_PATTERN)), 
				converter.getAsString(context, component, now));
	}
}