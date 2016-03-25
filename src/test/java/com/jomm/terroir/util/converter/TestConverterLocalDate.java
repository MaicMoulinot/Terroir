package com.jomm.terroir.util.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class is a Junit test case testing the {@code getAsObject()} and {@code getAsString()} 
 * methods of {@link ConverterLocalDate}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConverterLocalDate {
	
	@Mock
    private FacesContext context;
	
	@Mock
    private UIComponent component;
	
	private ConverterLocalDate converter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new ConverterLocalDate();
	}

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
		assertEquals("This should never fail because of rounding", LocalDate.parse(value, ConverterLocalDate.getFormatter()), 
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
		assertEquals("This method should never fail because of rounding", now.format(ConverterLocalDate.getFormatter()), 
				converter.getAsString(context, component, now));
	}
}