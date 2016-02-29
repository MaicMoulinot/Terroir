package com.jomm.terroir.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class is a Junit test case testing the <code>getAsObject()</code> and <code>getAsString()</code> 
 * methods of {@link ConverterLocalDate}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConverterLocalDate {
	
	@Mock(name = "context")
    private FacesContext context;
	
	@Mock(name = "component")
    private UIComponent component;
	
	@InjectMocks
	private ConverterLocalDate converter;

	/**
	 * Test method for {@link ConverterLocalDate#getAsObject(FacesContext, UIComponent, String)} with its value null.
	 */
	@Test
	public final void testGetAsObjectWithValueNull() {
		assertNull(converter.getAsObject(context, component, null));
	}
	
	/**
	 * Test method for {@link ConverterLocalDate#getAsObject(FacesContext, UIComponent, String)} with its value not null.
	 */
	@Test
	public final void testGetAsObjectWithValueNotNull() {
		String value = "27/02/2016";
		assertEquals("This should not fail because of rounding", 
				LocalDate.parse(value, DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_PATTERN)), 
				converter.getAsObject(context, component, value));
	}
	
	/**
	 * Test method for {@link ConverterLocalDate#getAsString(FacesContext, UIComponent, Object)}, with its value null.
	 */
	@Test
	public final void testGetAsStringWithValueNull() {
		assertNull(converter.getAsString(context, component, null));
	}

	/**
	 * Test method for {@link ConverterLocalDate#getAsString(FacesContext, UIComponent, Object)}, with its value not null.
	 */
	@Test
	public final void testGetAsStringWithValueNotNull() {
		LocalDate now = LocalDate.now();
		assertEquals("This method should not fail because of rounding", 
				now.format(DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_PATTERN)), 
				converter.getAsString(context, component, now));
	}
}