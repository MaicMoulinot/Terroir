package com.jomm.terroir.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a quality label, or certification mark.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceLabel} for all its business operations.
 * It includes an {@link Image} and other specific attributes.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code label}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Label.findAll", query="SELECT l FROM Label l")
public class Label extends AbstractEntity {

	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	@Id
	@GeneratedValue
	@Column(name = "label_id")
	private Long id;
	
	@NotNull
	@Column(name = "official_name", unique = true)
	private String name;
	
	@NotNull
	@Column(unique = true)
	private String acronym;
	
	@Column(columnDefinition = "text")
	private String definition;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="fk_image_id")
	private Image logo;
	
	@ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY)
	private List<Designation> designations;
	
	// Getters and Setters //-------------------------------------
	@Override
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
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
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * @param sigle the acronym to set
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
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