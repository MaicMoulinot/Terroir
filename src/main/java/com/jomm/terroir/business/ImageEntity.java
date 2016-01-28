package com.jomm.terroir.business;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tr_image")
@NamedQuery(name="ImageEntity.findAll", query="SELECT i FROM ImageEntity i")
public class ImageEntity {
	
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
