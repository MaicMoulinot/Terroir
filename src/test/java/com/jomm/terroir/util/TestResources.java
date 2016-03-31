package com.jomm.terroir.util;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.ERROR;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.MESSAGE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

import org.junit.Test;

import com.jomm.terroir.util.Constants.ResourceBundleError;
import com.jomm.terroir.util.Constants.ResourceBundleMessage;

/**
 * This Class is a Junit test case testing {@link Resources}.
 * @author Maic
 */
public class TestResources {
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link Resources#getLogger(InjectionPoint)} with its {@link InjectionPoint} null.
	 */
	@Test
	public final void testGetLoggerWithInjectionPointNull() {
		assertNull(Resources.getLogger(null));
	}

	/**
	 * Test method for {@link Resources#getLogger(InjectionPoint)} with its {@link InjectionPoint} not null.
	 */
	@Test
	public final void testGetLoggerWithInjectionPointNotNull() {
		Logger logger = createLogger(this.getClass());
		assertNotNull(logger);
		assertEquals(logger.getName(), this.getClass().getName());
		logger.log(Level.SEVERE, "This entry should be logged in SEVERE level");
		logger.log(Level.INFO, "This entry should be logged in INFO level");
	}

	/**
	 * Test method for {@link Resources#getFacesContext()}.
	 */
	@Test
	public final void testGetFacesContext() {
		assertEquals(Resources.getFacesContext(), FacesContext.getCurrentInstance());
	}

	/**
	 * Test method for {@link Resources#getValueFromKey(ResourceBundleMessage)}.
	 */
	@Test
	public final void testGetValueFromKeyResourceBundleMessage() {
		assertEquals("ResourceBundleMessage", 
				ResourceBundle.getBundle(MESSAGE.getFileName(), Locale.getDefault()).getString(CREATE_OK.getKey()), 
				getValueFromKey(CREATE_OK));
	}
	
	/**
	 * Test method for {@link Resources#getValueFromKey(ResourceBundleError)}.
	 */
	@Test
	public final void testGetValueFromKeyResourceBundleError() {
		assertEquals("ResourceBundleError", 
				ResourceBundle.getBundle(ERROR.getFileName(), Locale.getDefault()).getString(EXCEPTION.getKey()), 
				getValueFromKey(EXCEPTION));
	}
	
	// Static methods //------------------------------------------
	/**
	 * Construct a dummy {@link Logger} usable for test.
	 * @param declaringClass the declaring class of {@link InjectionPoint}.
	 * @return the {@link Logger}.
	 */
	public static Logger createLogger(Class<?> declaringClass) {
		return Resources.getLogger(constructInjectionPoint(declaringClass));
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Construct a dummy {@link InjectionPoint} usable for test.
	 * @param declaringClass the declaring class of {@link InjectionPoint}.
	 * @return the {@link InjectionPoint}.
	 */
	private static InjectionPoint constructInjectionPoint(Class<?> declaringClass) {
		InjectionPoint injectionPoint = new InjectionPoint() {
			
			@Override
			public boolean isTransient() {
				return false;
			}
			
			@Override
			public boolean isDelegate() {
				return false;
			}
			
			@Override
			public Type getType() {
				return null;
			}
			
			@Override
			public Set<Annotation> getQualifiers() {
				return null;
			}
			
			@Override
			public Member getMember() {
				return new Member() {
					
					@Override
					public boolean isSynthetic() {
						return false;
					}
					
					@Override
					public String getName() {
						return null;
					}
					
					@Override
					public int getModifiers() {
						return 0;
					}
					
					@Override
					public Class<?> getDeclaringClass() {
						return declaringClass;
					}
				};
			}
			
			@Override
			public Bean<?> getBean() {
				return null;
			}
			
			@Override
			public Annotated getAnnotated() {
				return null;
			}
		};
		return injectionPoint;
	}
}