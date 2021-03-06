package com.jomm.terroir.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a site.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceSite} for all its business operations.
 * It includes a {@link Enterprise}, and an {@link Address} among other specific attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code site}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site extends AbstractEntity {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The site's unique identifier in the system. */
	@Id
	@GeneratedValue
	@Column(name = "site_id")
	private Long id;
	
	/** The site's name. */
	@NotNull
	@Column(name = "site_name")
	private String name;
	
	/** The site's legal identification. */
	@NotNull
	@Column(name = "legal_identification")
	private String legalIdentification;
	
	/** The site's description. */
	@Column(columnDefinition = "text")
	private String description;
	
	/** The site's address. */
	@Embedded
	private Address address;
	
	/** 
	 * The site's enterprise. Site is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_enterprise_id")
	private Enterprise enterprise;
	
	/** 
	 * The site's list of products. Product is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
	
	/** 
	 * The site's list of images. Image is the owning side of the relationship, 
	 * and this one-to-many relationship is persisted in a join table.
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "siteimage",
	joinColumns = {@JoinColumn(name = "fk_site_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "fk_image_id", nullable = false, updatable = false)}
	)
	private List<Image> images;
	
	/** 
	 * The site's list of possible designations. Site is the owning side of the relationship, 
	 * and this many-to-many relationship is persisted in a join table.
	 */
	@ManyToMany
	@JoinTable(name = "sitedesignation", 
		joinColumns = {@JoinColumn(name = "fk_site_id", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_designation_id", nullable = false, updatable = false)}
	)
	private List<Designation> designations;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the legalIdentification
	 */
	public String getLegalIdentification() {
		return legalIdentification;
	}

	/**
	 * @param legalIdentification the legalIdentification to set
	 */
	public void setLegalIdentification(String legalIdentification) {
		this.legalIdentification = legalIdentification;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the enterprise
	 */
	public Enterprise getEnterprise() {
		return enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
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