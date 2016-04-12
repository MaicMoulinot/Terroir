package com.jomm.terroir.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing an image.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceImage} for all its business operations.
 * It includes a {@code byte[]} persisted as a {@link Lob}.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code image}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image extends AbstractEntity {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes //----------------------------------------------
	/** The image's unique identifier in the system. */
	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private Long id;
	
	/** The image's title. */
	@NotNull
	@Column(name = "imag_title")
	private String title;
	
	/** The image's description. */
	@Column(name = "imag_description", columnDefinition = "text")
	private String description;
	
	/** The image's data. */
	@Lob
	//@NotNull //TODO Not ready for this !
	@Column(name = "imag_data")
	private byte[] data;
	
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
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}