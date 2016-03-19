package com.jomm.terroir.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a designation.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceDesignation} for all its business operations.
 * It includes a {@link Label}, an {@link Address} and an {@link Image} among other attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code designation}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Designation.findAll", query="SELECT d FROM Designation d")
public class Designation extends AbstractEntity {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "designation_id")
	private Long id;
	
	@NotNull
	@Column(name = "registered_name")
	private String registeredName;
	
	@Column(name = "transcripted_name")
	private String transcriptedName;
	
	@Column(name = "legal_act")
	private String legalAct;
	
	@Embedded
	private Address address;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="fk_qualitylabel_id")
	private Label label;
	
	@OneToOne
	@JoinColumn(name="fk_image_id")
	private Image logo;
	
	@OneToMany(mappedBy = "designation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
	
	// Getters and Setters
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
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(Label label) {
		this.label = label;
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