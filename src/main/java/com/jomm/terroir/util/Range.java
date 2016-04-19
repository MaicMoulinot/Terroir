package com.jomm.terroir.util;

import java.math.BigDecimal;

/**
 * This simple Class represents a price's range, with a {@code minimum} and a {@code maximum}.
 * @author Maic
 */
public class Range {
	
	// Variables //-----------------------------------------------
	private BigDecimal minimum;
	private BigDecimal maximum;
	
	// Constructors //--------------------------------------------
	/** Constructor.
	 * @param minimum {@link BigDecimal} the minimum of range.
	 * @param maximum {@link BigDecimal} the maximum of range.
	 */
	public Range(BigDecimal minimum, BigDecimal maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	// Getters and Setters //-------------------------------------
	/**
	 * @return the minimum
	 */
	public BigDecimal getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public BigDecimal getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(BigDecimal maximum) {
		this.maximum = maximum;
	}
}