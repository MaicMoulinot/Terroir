package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.NUMBER;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PRICE_OUT_OF_RANGE;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_NOT_CONVERTIBLE;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_QUANTITY_DESIGNATION_MANDATORY;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Constants.View.PARAMETER2;
import static com.jomm.terroir.util.Constants.View.PARAMETER3;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is the Validator relating to a price.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorPrice"}.
 * @author Maic
 */
@FacesValidator("validatorPrice")
public class ValidatorPrice implements Validator {

	// Constants //-----------------------------------------------
	private static final BigDecimal THIRTY_PER_CENT = new BigDecimal("0.3");
	private static final BigDecimal THOUSAND = new BigDecimal("1000");

	// Variables //-----------------------------------------------
	private Unit unit;
	private BigDecimal quantity;
	private Designation designation;

	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			// Retrieve price
			BigDecimal price = (BigDecimal) value;
			if (price.signum() != 1) {
				throw new ValidatorException(createMessage(getValueFromKey(NUMBER)));
			}
			// Retrieve parameters
			retrieveParametersFromComponent(component);
			// Validation
			BigDecimal pricePerUnit = price.divide(quantity);
			if (canCompareCurrentAndMedianPrice()) {
				// Then we can compare current price and median price
				if (unit != designation.getUnit()) {
					pricePerUnit = convertIntoDesignationUnit(pricePerUnit, designation.getUnit(), unit);
				}
				if (isOutOfRange(designation.getMedianPrice(), pricePerUnit)) {
					// Price is too far from median price
					throw new ValidatorException(createMessage(getValueFromKey(PRICE_OUT_OF_RANGE)));
				}
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Retrieve the parameters binded in a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @throws a {@link ValidatorException} if one of the parameters is {@code null}.
	 */
	private void retrieveParametersFromComponent(UIComponent component) throws ValidatorException {
		if (component != null) {
			if (component.getAttributes().get(PARAMETER1.toString()) != null) {
				unit = (Unit) ((UIInput) component.getAttributes().get(PARAMETER1.toString())).getValue();
			}
			if (component.getAttributes().get(PARAMETER2.toString()) != null) {
				quantity = (BigDecimal) ((UIInput) component.getAttributes().get(PARAMETER2.toString())).getValue();
			}
			if (component.getAttributes().get(PARAMETER3.toString()) != null) {
				designation = (Designation) ((UIInput) component.getAttributes().get(PARAMETER3.toString())).getValue();
			}
		}
		if (unit == null || quantity == null || designation == null) {
			throw new ValidatorException(createMessage(getValueFromKey(UNIT_QUANTITY_DESIGNATION_MANDATORY)));
		}
	}

	/**
	 * Convert the current price per unit into the designation's price per unit. 
	 * If the conversion is not possible, it throws a {@link ValidatorException}.
	 * @param pricePerUnit {@link BigDecimal} the non corrected current price per unit.
	 * @param designationUnit {@link Unit} the designation's unit.
	 * @param currentUnit {@link Unit} the current unit.
	 * @return a {@link BigDecimal} the corrected current price per unit.
	 * @throws a {@link ValidatorException} if the current unit is not convertible.
	 */
	private BigDecimal convertIntoDesignationUnit(BigDecimal pricePerUnit, Unit designationUnit, Unit currentUnit) 
			throws ValidatorException {
		BigDecimal correctedPricePerUnit = null;
		boolean unitNotConvertible = true;
		switch (designationUnit) {
		case LITER:
			if (currentUnit == Unit.MILLILITER) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND);
				unitNotConvertible = false;
			}
			break;
		case KILOGRAM:
			if (currentUnit == Unit.MILLIGRAM) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND).multiply(THOUSAND);
				unitNotConvertible = false;
			} else if (currentUnit == Unit.GRAM) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND);
				unitNotConvertible = false;
			}
			break;
		default: // units non convertible
			break;
		}
		if (unitNotConvertible) {
			Object[] argument = {designationUnit.getLocalizedName()};
			String summary = MessageFormat.format(getValueFromKey(UNIT_NOT_CONVERTIBLE).replace("'", "''"), argument);
			throw new ValidatorException(createMessage(summary));
		}
		return correctedPricePerUnit;
	}

	/**
	 * Compare the {@link com.jomm.terroir.business.model.Product}'s current price 
	 * with the {@link Designation}'s median price.
	 * If the current price is more or less than {@link ValidatorPrice#THIRTY_PER_CENT} the median price,
	 * than the current price is out of range.
	 * @param medianPrice {@link BigDecimal} the median price.
	 * @param currentPrice {@link BigDecimal} the current price.
	 * @return true if the current price is out of range, false otherwise.
	 */
	private boolean isOutOfRange(BigDecimal medianPrice, BigDecimal currentPrice) {
		boolean outOfRange = false;
		BigDecimal range = medianPrice.multiply(THIRTY_PER_CENT);
		if (currentPrice.compareTo(medianPrice.add(range)) > 0 
				|| currentPrice.compareTo(medianPrice.subtract(range)) < 0) {
			outOfRange = true;
		}
		return outOfRange;
	}

	/**
	 * Define if the current price and the median price can actually be compared.
	 * For now, if only one use the unit {@link Unit#PIECE}, the method returns {@code false}.
	 * @return {@code true} if both prices can be compared, {@code false} otherwise.
	 */
	private boolean canCompareCurrentAndMedianPrice() {
		return designation.getMedianPrice() != null && designation.getUnit() != null 
				&& unitsAreBothPieceOrOther(designation.getUnit(), unit);
	}
	
	/**
	 * Determine if only one unit is {@link Unit#PIECE}.
	 * @param unit1 {@link Unit} the first unit.
	 * @param unit2 {@link Unit} the second unit.
	 * @return {@code false} if only one unit is {@link Unit#PIECE}, {@code true} otherwise.
	 */
	private boolean unitsAreBothPieceOrOther(Unit unit1, Unit unit2) {
		return (unit1 != Unit.PIECE && unit2 != Unit.PIECE) || (unit1 == Unit.PIECE && unit2 == Unit.PIECE);
	}

	/**
	 * Instantiate a new {@link FacesMessage} with severity {@link FacesMessage#SEVERITY_ERROR}.
	 * @param summary String the message's summary.
	 * @return {@link FacesMessage} the message.
	 */
	private FacesMessage createMessage(String summary) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	}
}