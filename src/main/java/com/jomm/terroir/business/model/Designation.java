package com.jomm.terroir.business.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is an {@link Entity} representing a designation.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceDesignation} for all its business operations.
 * It includes a list of {@link Label}s, a list of {@link Product}s, a {@link Category}, an {@link Address} 
 * and two {@link Image}s, among other attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code designation}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Designation.findAll", query="SELECT d FROM Designation d")
public class Designation extends AbstractEntity {

	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The designation's unique identifier in the system. */
	@Id
	@GeneratedValue
	@Column(name = "designation_id")
	private Long id;
	
	/** The designation's official registered name. */
	@NotNull
	@Column(name = "registered_name")
	private String registeredName;
	
	/** The designation's local name, if any. */
	@Column(name = "local_name")
	private String localName;
	
	/** The designation's name in Latin alphabet, if the registered name is in an other alphabet. */
	@Column(name = "transcripted_name")
	private String transcriptedName;
	
	/** The designation's legal act reference. */
	@Column(name = "legal_act")
	private String legalAct;
	
	/** The designation's official date of registration. */
	@Column(name = "registration_date", columnDefinition = "timestamp with time zone")
	private ZonedDateTime registrationDate;
	
	/** The designation's official definition. */
	@Column(columnDefinition = "text")
	private String definition;
	
	/** The designation's season, if any. */
	@Column(columnDefinition = "text")
	private String season;
	
	/** The designation's official website, if any. */
	@Column(name = "web_site")
	private String webSite;
	
	/** The designation's median price, if any. */
	@Column(name = "median_price")
	private BigDecimal medianPrice;
	
	/** The designation's median price's base unit from {@link Constants#Unit}.  */
	@Column(name = "median_price_unit", length = 2)
	private Unit unit;
	
	/** The designation's address. */
	@Embedded
	private Address address;
	
	/** 
	 * The designation's logo, if any. Designation is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_image_logo_id")
	private Image logo;
	
	/** 
	 * The designation's picture. Designation is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "fk_image_picture_id")
	private Image picture;
	
	/** 
	 * The designation's category. Designation is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_category_id")
	private Category category;
	
	/** 
	 * The designation's list of labels. Designation is the owning side of the relationship, 
	 * and this many-to-many relationship is persisted in a join table.
	 */
	@ManyToMany
	@JoinTable(name = "designationlabel", 
		joinColumns = {@JoinColumn(name = "fk_designation_id", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_label_id", nullable = false, updatable = false)}
	)
	private List<Label> labels;
	
	/** 
	 * The designation's list of sites. Site is the owning side of the relationship, 
	 * and this many-to-many relationship is persisted in a join table.
	 */
	@ManyToMany(mappedBy = "designations", fetch = FetchType.LAZY)
	private List<Site> sites;
	
	/** 
	 * The designation's list of products. Product is the owning side of the relationship, 
	 * since it contains the foreign key.
	 */
	@OneToMany(mappedBy = "designation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

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
	 * @return the registeredName
	 */
	public String getRegisteredName() {
		return registeredName;
	}

	/**
	 * @param registeredName the registeredName to set
	 */
	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	/**
	 * @return the localName
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * @param localName the localName to set
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	/**
	 * @return the transcriptedName
	 */
	public String getTranscriptedName() {
		return transcriptedName;
	}

	/**
	 * @param transcriptedName the transcriptedName to set
	 */
	public void setTranscriptedName(String transcriptedName) {
		this.transcriptedName = transcriptedName;
	}

	/**
	 * @return the legalAct
	 */
	public String getLegalAct() {
		return legalAct;
	}

	/**
	 * @param legalAct the legalAct to set
	 */
	public void setLegalAct(String legalAct) {
		this.legalAct = legalAct;
	}

	/**
	 * @return the registrationDate
	 */
	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(ZonedDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the medianPrice
	 */
	public BigDecimal getMedianPrice() {
		return medianPrice;
	}

	/**
	 * @param medianPrice the medianPrice to set
	 */
	public void setMedianPrice(BigDecimal medianPrice) {
		this.medianPrice = medianPrice;
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
	 * @return the logo
	 */
	public Image getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(Image logo) {
		this.logo = logo;
	}

	/**
	 * @return the picture
	 */
	public Image getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Image picture) {
		this.picture = picture;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the labels
	 */
	public List<Label> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
	/**
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites the sites to set
	 */
	public void setSites(List<Site> sites) {
		this.sites = sites;
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
}