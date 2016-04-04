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
    @OneToOne(optional = false)
    @JoinColumn(name = "product_id")
	private Product product;
	
	/** The product's number of units available for sale. */
	@Column(name = "in_stock")
	private Integer quantity;
	
	/** The last time the stock's status changed. */
	@Column(name = "last_update", columnDefinition = "timestamp with time zone")
	private ZonedDateTime lastUpdate;
	
	// Constructors //--------------------------------------------
	/** Zero-argument constructor. */
	public Stock() {
		// To be fully compatible with JPA entity's requirements
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

	/**
	 * @param id the id to set
	 */
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
		if (product.getStock() != this) {
			product.setStock(this);
		}
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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