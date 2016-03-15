package com.jomm.terroir.business.model;

import java.io.Serializable;

/**
 * This abstract Class describes the common methods for an {@link javax.persistence.Entity}.
 * It implements {@link Serializable} and has a default serial version ID.
 * It should be extended by all entities in the model.
 * @author Maic
 */
public abstract class AbstractEntity implements Serializable {

	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * This method returns the identifier of the {@link javax.persistence.Entity}.
	 * @return the id a {@link Long}.
	 */
	public abstract Long getId();
}