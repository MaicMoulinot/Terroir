package com.jomm.terroir.business;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.MEDIAN_PRICE_INVALID;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_INVALID;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.dao.DaoDesignation;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Service relating to {@link Designation}.
 * It implements {@link ServiceDesignation} and defines all its business methods.
 * It relates to {@link DaoDesignation} for all persistence operations.
 * @author Maic
 */
@Stateless
public class ServiceDesignationImpl implements ServiceDesignation {

	// Constants //-----------------------------------------------
	private static final BigDecimal THIRTY_PER_CENT = new BigDecimal("0.3");
	private static final BigDecimal THOUSAND = new BigDecimal("1000");

	// Injected Fields //-----------------------------------------
	@Inject
	private DaoDesignation daoDesignation;

	// Methods //-------------------------------------------------
	@Override
	public Designation create(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() != null) {
			throw new ExceptionService(ID_SHOULD_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
		} else if (!isValidMedianPrice(designation.getMedianPrice())) {
			throw new ExceptionService(MEDIAN_PRICE_INVALID);
		}
		return daoDesignation.create(designation);
	}

	@Override
	public Designation update(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
		} else if (!isValidMedianPrice(designation.getMedianPrice())) {
			throw new ExceptionService(MEDIAN_PRICE_INVALID);
		}
		return daoDesignation.update(designation);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Designation> getAllDesignations() {
		return daoDesignation.findAll();
	}

	@Override
	public void delete(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (designation.getId() == null) {
			throw new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		}
		daoDesignation.delete(designation);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal getMaxRangePrice(Designation designation) throws ExceptionService {
		BigDecimal range = computeRange(designation);
		return designation.getMedianPrice().add(range);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal getMinRangePrice(Designation designation) throws ExceptionService {
		BigDecimal range = computeRange(designation);
		return designation.getMedianPrice().subtract(range);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal convertPriceIntoDesignationUnit(Designation designation, BigDecimal pricePerUnit, 
			Unit currentUnit) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
		} else if (currentUnit == null || pricePerUnit == null) {
			throw new NullPointerException();
		}
		BigDecimal correctedPricePerUnit = null;
		if (currentUnit != designation.getUnit()) {
			if (!onlyOneUnitIsPIECE(currentUnit, designation.getUnit())) {
				correctedPricePerUnit = convertPrice(designation.getUnit(), pricePerUnit, currentUnit);
			}
		} else {
			correctedPricePerUnit = pricePerUnit;
		}
		return correctedPricePerUnit;
	}

	// Helpers //-------------------------------------------------
	/**
	 * Determine is the designation's unit is valid, i.e. is either {@link Unit#KILOGRAM}, 
	 * {@link Unit#LITER} or {@link Unit#PIECE}.
	 * @param unit the {@link Unit}.
	 * @return {@code true} if the unit is valid, {@code false} otherwise.
	 */
	private boolean isValidUnit(Unit unit) {
		return unit != null && (unit == Unit.KILOGRAM || unit == Unit.LITER || unit == Unit.PIECE);
	}

	/**
	 * Determine is the designation's median price is valid, i.e. is not {@code null} and positive.
	 * @param medianPrice {@link BigDecimal} the median price.
	 * @return {@code true} if the median price is valid, {@code false} otherwise.
	 */
	private boolean isValidMedianPrice(BigDecimal medianPrice) {
		return medianPrice != null && medianPrice.signum() == 1;
	}

	/**
	 * Calculate the amount to add or subtract to the median price, 
	 * in order to define the range of authorized prices.
	 * Before to compute it verifies that the {@link Designation} is in a correct state.
	 * @param designation the {@link Designation}.
	 * @return {@link BigDecimal} the computed range.
	 * @throws ExceptionService {@link ExceptionService}.
	 */
	private BigDecimal computeRange(Designation designation) throws ExceptionService {
		if (designation == null) {
			throw new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		} else if (!isValidUnit(designation.getUnit())) {
			throw new ExceptionService(UNIT_INVALID);
		} else if (!isValidMedianPrice(designation.getMedianPrice())) {
			throw new ExceptionService(MEDIAN_PRICE_INVALID);
		}
		return designation.getMedianPrice().multiply(THIRTY_PER_CENT);
	}

	/**
	 * Determine if only one unit is {@link Unit#PIECE}.
	 * @param unit1 {@link Unit} the first unit.
	 * @param unit2 {@link Unit} the second unit.
	 * @return {@code true} if only one unit is {@link Unit#PIECE}, {@code false} otherwise.
	 */
	private boolean onlyOneUnitIsPIECE(Unit unit1, Unit unit2) {
		return (unit1 != Unit.PIECE && unit2 == Unit.PIECE) || (unit1 == Unit.PIECE && unit2 != Unit.PIECE);
	}

	/**
	 * Convert the current price per unit into the designation's price per unit. 
	 * If the conversion is not possible, it returns {@code null}.
	 * @param designationUnit {@link Unit} the designation's unit.
	 * @param pricePerUnit {@link BigDecimal} the current price per unit.
	 * @param currentUnit {@link Unit} the current unit.
	 * @return a {@link BigDecimal} the corrected current price per unit.
	 */
	private BigDecimal convertPrice(Unit designationUnit, BigDecimal pricePerUnit, Unit currentUnit) {
		BigDecimal correctedPricePerUnit = null;
		switch (designationUnit) {
		case LITER:
			if (currentUnit == Unit.MILLILITER) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND);
			}
			break;
		case KILOGRAM:
			if (currentUnit == Unit.MILLIGRAM) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND).multiply(THOUSAND);
			} else if (currentUnit == Unit.GRAM) {
				correctedPricePerUnit = pricePerUnit.multiply(THOUSAND);
			}
			break;
		default: // units non convertible
			break;
		}
		return correctedPricePerUnit;
	}

	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param daoDesignation the daoDesignation to set.
	 */
	void setTestDaoDesignation(DaoDesignation daoDesignation) {
		this.daoDesignation = daoDesignation;
	}
}