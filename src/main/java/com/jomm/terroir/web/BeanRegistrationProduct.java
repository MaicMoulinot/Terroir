package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.Entity.PRODUCT;

import java.math.BigDecimal;
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
public class BeanRegistrationProduct extends BackingBean {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
	
	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceProduct serviceProduct;
	@Inject
	private ServiceDesignation serviceDesignation;
	@Inject
	private ServiceSite serviceSite; //TODO : à supprimer !!
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
	private List<Designation> designations;
	
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes, such as the {@link Site} and {@link Designation}.
	 * It replaces the constructor and it is annotated {@link PostConstruct},
	 * for proper call from the bean management framework which uses proxies, such as CDI.
	 */
	@PostConstruct 
	public void init() {
		setSite(serviceSite.getAllSites().get(0)); //TODO à supprimer !! //setSite(new Site());
		setDesignation(new Designation());
		designations = serviceDesignation.getAllDesignations();
	}
	
	/**
	 * Generate an {@link Product} using values from the {@link BeanRegistrationProduct}.
	 * @return {@link Product}.
	 */
	public Product convertIntoEntity() {
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

	/**
	 * @return the designations
	 */
	public List<Designation> getDesignations() {
		return designations;
	}

	/**
	 * @param designations the designations to set
	 */
	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}
}