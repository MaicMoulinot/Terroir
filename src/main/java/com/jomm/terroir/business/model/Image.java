package com.jomm.terroir.business.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing an image.
 * It uses {@link ImageService} for all its logic operations.
 * It implements {@link Serializable} and has a default serial version ID.
 * It includes a {@link Blob}.
 * Its properties are persisted in table "tr_image".
 * @author Maic
 */
@Entity
@Table(name="tr_image")
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private Long id;
	
	@NotNull
	private String title;
	
	private String description;
	
	@Lob
	@NotNull
	@Column(name = "image_data")
	private BufferedImage imageData;
	
	// Getters and Setters
	/**
	 * @return the id
	 */
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
	 * @return the imageData
	 */
	public BufferedImage getBufferedImage() {
		return imageData;
	}

	/**
	 * @param imageData the imageData to set
	 */
	public void setBufferedImage(BufferedImage imageData) {
		this.imageData = imageData;
	}
}
