package com.jomm.terroir.business;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This Class is an {@link Entity} representing an image.
 * It uses {@link ImageService} for all its logic operations.
 * It implements {@link Serializable} and has a generated serial version ID.
 * It includes a {@link Blob}.
 * Its properties are persisted in table "tr_image".
 * @author Maic
 */
@Entity
@Table(name="tr_image")
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	
	/** Generated serial version ID. Do not modify. */
	private static final long serialVersionUID = 1551346199307522787L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private long id;
	
	@Lob
	private Blob image;
	
	// Getters and Setters
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the image
	 */
	public Blob getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}
}
