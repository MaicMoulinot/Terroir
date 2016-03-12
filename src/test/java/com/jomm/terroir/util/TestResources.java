package com.jomm.terroir.util;

import static com.jomm.terroir.util.Constants.ResourceBundleFileName.ERROR;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.LABEL;
import static com.jomm.terroir.util.Constants.ResourceBundleFileName.MESSAGE;
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
	 * Test method for {@link Resources#getResourceBundleMessage()}.
	 */
	@Test
	public final void testGetResourceBundleMessage() {
		assertEquals(ResourceBundle.getBundle(MESSAGE.getFileName(), Locale.getDefault()), 
				Resources.getResourceBundleMessage());
	}

	/**
	 * Test method for {@link Resources#getResourceBundleError()}.
	 */
	@Test
	public final void testGetResourceBundleError() {
		assertEquals(ResourceBundle.getBundle(ERROR.getFileName(), Locale.getDefault()), 
				Resources.getResourceBundleError());
	}

	/**
	 * Test method for {@link Resources#getResourceBundleLabel()}.
	 */
	@Test
	public final void testGetResourceBundleLabel() {
		assertEquals(ResourceBundle.getBundle(LABEL.getFileName(), Locale.getDefault()), 
				Resources.getResourceBundleLabel());
	}
	
	/**
	 * When injection is not available (in testing environment), this method retrieve
	 * the value from the {@link ResourceBundle} with qualifier {@link BundleError}.
	 * @param key {@link ResourceBundleError} the key.
	 * @return String the value.
	 */
	public static String getValueFromResourceBundle(ResourceBundleError key) {
		return Resources.getResourceBundleError().getString(key.getKey());
	}
	
	/**
	 * When injection is not available (in testing environment), this method retrieve
	 * the value from the {@link ResourceBundle} with qualifier {@link BundleMessage}.
	 * @param key {@link ResourceBundleMessage} the key.
	 * @return String the value.
	 */
	public static String getValueFromResourceBundle(ResourceBundleMessage key) {
		return Resources.getResourceBundleMessage().getString(key.getKey());
	}
	
	/**
	 * Construct a dummy {@link Logger} usable for test.
	 * @param declaringClass the declaring class of {@link InjectionPoint}.
	 * @return the {@link Logger}.
	 */
	public static Logger createLogger(Class<?> declaringClass) {
		return Resources.getLogger(constructInjectionPoint(declaringClass));
	}
	
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