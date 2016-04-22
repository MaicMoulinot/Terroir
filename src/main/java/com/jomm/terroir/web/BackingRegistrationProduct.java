package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.Entity.PRODUCT;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.MEDIAN_PRICE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PRICE_PER_UNIT;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceDesignation;
import com.jomm.terroir.business.ServiceProduct;
import com.jomm.terroir.business.ServiceSite;
import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.Range;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Bean linked to {@code registrationproduct.xhtml}, used to register a new {@link Product}.
 * It extends {@link BackingBean} and defines product specific attributes.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceUser} to save the {@link Product},
 * and to {@link Logger} to generate proper logging messages.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BackingRegistrationProduct extends BackingBean {

	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceProduct serviceProduct;
	@Inject
	private ServiceSite serviceSite;
	@Inject
	private ServiceDesignation serviceDesignation;
	@Inject
	private Logger logger;

	// Variables //-----------------------------------------------
	private String title;
	private BigDecimal quantity;
	private Unit unit;
	private BigDecimal price;
	private BigDecimal taxPercentage;
	private Boolean active;
	private Stock stock;	
	private Site site;
	private Designation designation;
	private boolean activateValidation; // multiple validation disabled or not

	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes, such as {@link Site} and {@link Stock}.
	 * It replaces the constructor and it is annotated {@link PostConstruct},
	 * for proper call from the bean management framework which uses proxies, such as CDI.
	 */
	@PostConstruct 
	public void init() {
		try {
			setSite(serviceSite.getSite(1L));
		} catch (ExceptionService e) {
			e.printStackTrace();
		}
		setStock(new Stock());
		setActivateValidation(false);
	}

	/**
	 * Create and save a new Product.
	 * @return a {@code String} for navigation.
	 */
	public String create() {
		Product entity = convertIntoEntity();
		try {
			serviceProduct.create(entity);
			addFacesMessageCreate(PRODUCT, entity.getId());
		} catch (ExceptionService exception) {
			String problem = generateReadableExceptionMessage(exception, entity);
			addFacesMessageException(problem);
			logger.log(Level.FINE, problem, exception);
		}
		return "listproduct" + "?faces-redirect=true";	// Navigation case.
	}

	/** The multiple price's validation is now active. */
	public void activateMultipleValidation() {
		setActivateValidation(true);
	}
	
	/**
	 * Format a message describing the {@link Designation}'s median price and its unit.
	 * @return a {@code String} the formatted designation's median price.
	 */
	public String formatMedianPrice() {
		String medianPrice = "";
		if (designation != null) {
			try {
				Range range = serviceDesignation.getPriceRange(designation);
				Object[] arguments = {designation.getRegisteredName(), 
						range.getMinimum().stripTrailingZeros(), 
						range.getMaximum().stripTrailingZeros(), 
						getCurrencySymbol(), 
						designation.getUnit().getSymbol()};
				medianPrice = MessageFormat.format(getValueFromKey(MEDIAN_PRICE).replace("'", "''"), arguments);
			} catch (ExceptionService exception) {
				medianPrice = exception.getMessage();
				logger.log(Level.FINE, medianPrice, exception);
			}
		}
		return medianPrice;
	}

	/**
	 * Format a readable current computed price per unit.
	 * @return a {@code String} the formatted price per unit.
	 */
	public String formatPricePerUnit() {
		String result = null;
		BigDecimal computedPrice = calculatePricePerUnit(quantity, price);
		if (computedPrice != null) {
			Object[] arguments = {computedPrice.stripTrailingZeros(), getPricePerUnitSuffix(unit)};
			result = MessageFormat.format(getValueFromKey(PRICE_PER_UNIT).replace("'", "''"), arguments);
		}
		return result;
	}

	/**
	 * Construct a list of possible values for the {@code title} combining the initial {@code query} 
	 * provided by the user and the product's diverse properties.
	 * @param query {@code String} the initial value provided by the user.
	 * @return a {@code List<String>} of possible completed titles.
	 */
	public List<String> completeTitle(String query) {
		List<String> results = new LinkedList<>();
		String trimmedQuery = query.trim();
		// Add designation's information
		String designationPart = "";
		if (designation != null) {
			designationPart = completePossibleResults(results, trimmedQuery, designation.getRegisteredName());
		}
		// Add site's information
		String sitePart = "";
		if (site != null) {
			sitePart = completePossibleResults(results, trimmedQuery, site.getName());
		}
		String pricePerUnitPart = "";
		String quantityPart = "";
		if (unit != null) {
			// Add price per unit information
			BigDecimal pricePerUnit = calculatePricePerUnit(quantity, price);
			if (pricePerUnit != null) {
				pricePerUnitPart = completePossibleResults(results, trimmedQuery, 
						pricePerUnit + getPricePerUnitSuffix(unit));
			}
			// Add quantity in unit information
			if (quantity != null) {
				quantityPart = completePossibleResults(results, trimmedQuery, quantity + unit.getSymbol());
			}
		}
		// Add combined information
		results.add(trimmedQuery + designationPart + sitePart + quantityPart + pricePerUnitPart);
		results.add(trimmedQuery + sitePart + designationPart + quantityPart);
		results.add(trimmedQuery + designationPart + sitePart);
		results.add(trimmedQuery + sitePart + designationPart + pricePerUnitPart);
		results.add(designationPart + sitePart + quantityPart + pricePerUnitPart);
		return results;
	}

	// Helpers //-------------------------------------------------
	/**
	 * Generate an {@link Product} using values from the {@link BackingRegistrationProduct}.
	 * @return a {@link Product}.
	 */
	private Product convertIntoEntity() {
		Product entity = new Product();
		entity.setTitle(getTitle());
		entity.setQuantity(getQuantity());
		entity.setUnit(unit);
		entity.setPricePerUnit(calculatePricePerUnit(quantity, price));
		entity.setTaxPercentage(taxPercentage);
		entity.setActive(getActive());
		entity.setSite(getSite());
		entity.setDesignation(getDesignation());
		if (getStock() != null && getStock().getAvailability() != null) {
			entity.addStock(getStock());
		} else {
			entity.removeStock(getStock());
		}
		return entity;
	}

	/**
	 * Compute the price per unit using the {@code quantity} and {@code price}.
	 * @param quantity {@link BigDecimal} the quantity.
	 * @param price {@link BigDecimal} the price.
	 * @return a {@link BigDecimal} the computed price per unit.
	 */
	private BigDecimal calculatePricePerUnit(BigDecimal quantity, BigDecimal price) {
		return (quantity != null && price != null) ? price.divide(quantity, 4, RoundingMode.HALF_UP) : null;
	}

	/**
	 * Format the price per unit suffix.
	 * @param unit the {@link Unit}.
	 * @return a {@code String} the price per unit suffix.
	 */
	private String getPricePerUnitSuffix(Unit unit) {
		return getCurrencySymbol() + "/" + unit.getSymbol();
	}

	/**
	 * Fill the list with possible results.
	 * @param results {@code List<String>} the list to complete.
	 * @param query {@code String} the query entered by the user.
	 * @param partialResult {@code String} a possible result to add.
	 * @return a {@code String} the partialResult if it is not already contained in the query.
	 */
	private String completePossibleResults(List<String> results, String query, String partialResult) {
		boolean partialResultIsInQuery = true;
		String queryToLowerCase = query.toLowerCase();
		String partToLowerCase = partialResult.toLowerCase();
		if (!queryToLowerCase.contains(partToLowerCase)) {
			partialResultIsInQuery = false;
			if (partToLowerCase.startsWith(queryToLowerCase)) {
				// If the query is the actual beginning of part
				results.add(partialResult);
			} else {
				// If the query ends by the beginning of part
				int lastIndexQuery = queryToLowerCase.length() - 1;
				if (partToLowerCase.startsWith(queryToLowerCase.substring(lastIndexQuery))) {
					results.add(query.substring(0, lastIndexQuery) + partialResult);
				}
			}
		}
		return partialResultIsInQuery ? "" : " " + partialResult;
	}

	// Getters and Setters //-------------------------------------
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the taxPercentage
	 */
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}

	/**
	 * @return the designation
	 */
	public Designation getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	/**
	 * @return the activateValidation
	 */
	public boolean getActivateValidation() {
		return activateValidation;
	}

	/**
	 * @param activateValidation the activateValidation to set
	 */
	public void setActivateValidation(boolean activateValidation) {
		this.activateValidation = activateValidation;
	}
}