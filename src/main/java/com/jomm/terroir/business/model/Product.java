package com.jomm.terroir.business.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is an {@link Entity} representing a product.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceProduct} for all its business operations.
 * It includes a {@link Site} among other specific attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code product}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product extends AbstractEntity {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;
    
	// Attributes //----------------------------------------------
	/** The product's unique identifier in the system. */
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private Long id;
	
	/** The product's name. */
	@NotNull
	private String title;
	
	/** The product's quantity, counted in {@code unit}. */
	@NotNull
	@Column(precision = 6, scale = 2)
	private BigDecimal quantity;
	
	/** The product's base unit from {@link Constants#Unit}.  */
	@NotNull
	@Column(length = 2)
	private Unit unit;
	
	/** The product's cost per unit. Thus the final product's cost is {@code pricePerUnit} x {@code quantity}. */
	@NotNull
	@Column(name = "price_per_unit", precision = 6, scale = 2)
	private BigDecimal pricePerUnit;
	
	/** Percentage of the price to be charged as tax. */
	@Column(name = "tax_percentage", precision = 4, scale = 2)
	private BigDecimal taxPercentage;
	
	/** 
	 * The product's availability. If this attribute is {@code false}, the product is not currently for sale, 
	 * otherwise it can be offered to customers.
	 */
	@Column(name = "active_for_sale")
	private Boolean active;
	
	/** The product's stock. */
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Stock stock;
	
	/** The product's production site. */
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_site_id")
	private Site site;
	
	/** The product's designation. */
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_designation_id")
	private Designation designation;
	
	// Constructors //--------------------------------------------
    /**
     * Constructor.
     * Instantiate a {@link Product} and its {@link Stock}.
     */
    public Product() {
    	super();
        this.stock = new Stock(this);
    }
    
    // Methods //-------------------------------------------------
	/**
	 * Add the child entity {@link Stock} to the parent entity {@link Product},
	 * handling both sides of the relationship.
	 * @param stock {@link Stock}.
	 */
    public void addStock(Stock stock) {
    	setStock(stock);
    	if (stock != null && stock.getProduct() != this) {
    		stock.setProduct(this);
    	}
    }
    
	/**
	 * Remove the child entity {@link Stock} from the parent entity {@link Product},
	 * handling both sides of the relationship.
	 */
    public void removeStock(Stock stock) {
    	if (stock != null) {
    		stock.setProduct(null);
    	}
    	setStock(null);
    }
    
	// Getters and Setters //-------------------------------------
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
	 * @return the pricePerUnit
	 */
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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
	protected void setStock(Stock stock) {
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