package com.jomm.terroir.business.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * This Class is an {@link Entity} representing the stock of a product.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceProduct} for all its business operations.
 * It includes a {@link Product}, in an one-to-one relation, and both tables share the same primary key.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code stock}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
public class Stock extends AbstractEntity {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The product's unique identifier in the system. */
	@Id
	private Long id;
	
	/** 
	 * The product related to this stock. Stock is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
    @MapsId
    @OneToOne
    @JoinColumn(name = "product_id")
	private Product product;
	
	/** The number of products available for sale. */
	private Integer availability;
	
	/** The last time the stock's availability changed. */
	@Column(name = "last_update", columnDefinition = "timestamp with time zone")
	private ZonedDateTime lastUpdate;
	
	// Constructors //--------------------------------------------
	/**
	 * Zero-argument constructor, to be fully compatible with JPA entity's requirements.
	 * This constructor is not to be used, prefer {@link Stock#Stock(Product)}.
	 */
	public Stock() {
		super();
    }
	
	/**
	 * Constructor.
	 * @param product the {@link Product}.
	 */
	public Stock(Product product) {
		this();
		setProduct(product);
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the availability
	 */
	public Integer getAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	/**
	 * @return the lastUpdate
	 */
	public ZonedDateTime getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}