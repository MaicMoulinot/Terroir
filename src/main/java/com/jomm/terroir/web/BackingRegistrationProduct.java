package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.MONEY_SYMBOL;
import static com.jomm.terroir.util.Constants.Entity.PRODUCT;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.MEDIAN_PRICE;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceProduct;
import com.jomm.terroir.business.ServiceSite;
import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.util.Constants.Unit;
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
	private Logger logger;

	// Variables //-----------------------------------------------
	private String title;
	private BigDecimal price;
	private Unit unit;
	private BigDecimal taxPercentage;
	private Boolean active;
	private Stock stock;	
	private Site site;
	private Designation designation;

	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes, such as {@link Site} and {@link Stock}.
	 * It replaces the constructor and it is annotated {@link PostConstruct},
	 * for proper call from the bean management framework which uses proxies, such as CDI.
	 */
	@PostConstruct 
	public void init() {
		setSite(serviceSite.getSite(1L)); //TODO à supprimer !! //setSite(new Site());
		setStock(new Stock());
	}

	/**
	 * Create and save a new Product.
	 * @return String for navigation.
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

	/**
	 * Return a map containing all values from the enumeration {@link Unit}.
	 * Each value has the localized Unit's name as key.
	 * @return the map of Units.
	 */
	public Map<String, Unit> getUnits() {
		Map<String, Unit> units = new LinkedHashMap<>();
		for (Unit currentUnit : Unit.values()) {
			units.put(getValueFromKey(currentUnit), currentUnit);
		}
		return units;
	}

	/**
	 * Construct a list of possible values for the {@code title} combining the initial {@code query} 
	 * provided by the user and the product's diverse properties.
	 * @param query String the initial value provided by the user.
	 * @return a list of possible completed titles.
	 */
	public List<String> completeTitle(String query) {
		List<String> results = new LinkedList<>();
		// Add designation's information
		String designationPart = "";
		if (designation != null) {
			designationPart = designation.getRegisteredName();
			results.add(query + " " + designationPart);
			designationPart += ", ";
			results.add(designationPart + query);
		}
		// Add site's information
		String sitePart = "";
		if (site != null) {
			sitePart = site.getName();
			results.add(query + " made with care at " + sitePart);
			sitePart += ", ";
			results.add(sitePart + query);
		}
		String pricePart = "";
		String quantityPart = "";
		if (unit != null) {
			// Add price and unit information
			if (price != null) {
				pricePart = price + " " + MONEY_SYMBOL + " / " + unit;
				results.add(query + " @ " + pricePart);
				pricePart += ", ";
				results.add(pricePart + query);
			}
			// Add quantity information
			if (stock != null && stock.getQuantity() != null) {
				quantityPart = stock.getQuantity() + " " + unit;
				results.add(query + ", " + quantityPart);
				quantityPart += ", ";
				results.add(quantityPart + query);
			}
		}
		// Add combined information
		results.add(designationPart + quantityPart + sitePart + query);
		results.add(designationPart + sitePart + quantityPart + query);
		results.add(quantityPart + sitePart + designationPart + query);
		results.add(quantityPart + designationPart + sitePart + query);
		results.add(sitePart + designationPart + quantityPart + query);
		results.add(sitePart + quantityPart + designationPart + query);
		results.add(designationPart + pricePart + sitePart + query);
		results.add(designationPart + sitePart + pricePart + query);
		results.add(pricePart + sitePart + designationPart + query);
		results.add(pricePart + designationPart + sitePart + query);
		results.add(sitePart + designationPart + pricePart + query);
		results.add(sitePart + pricePart + designationPart + query);
		return results;
	}

	/**
	 * Format a message describing the {@link Designation}'s median price and its unit.
	 * @return String the formatted designation's median price.
	 */
	public String formatMedianPrice() {
		String medianPrice = null;
		if (designation != null) {
			Object[] arguments = {designation.getRegisteredName(), designation.getMedianPrice(), 
					MONEY_SYMBOL, designation.getUnit().getSymbol()};
			medianPrice = MessageFormat.format(getValueFromKey(MEDIAN_PRICE).replace("'", "''"), arguments);
		}
		return medianPrice;
	}

	// Helpers //-------------------------------------------------
	/**
	 * Generate an {@link Product} using values from the {@link BackingRegistrationProduct}.
	 * @return {@link Product}.
	 */
	private Product convertIntoEntity() {
		Product entity = new Product();
		entity.setTitle(getTitle());
		entity.setPrice(getPrice());
		entity.setUnit(unit);
		entity.setTaxPercentage(taxPercentage);
		entity.setActive(isActive());
		entity.setStock(stock);
		entity.setSite(getSite());
		entity.setDesignation(getDesignation());
		return entity;
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
	public Boolean isActive() {
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
}