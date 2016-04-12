package com.jomm.terroir.business.model;

import java.io.Serializable;

/**
 * This abstract Class describes the common methods for an {@link javax.persistence.Entity}.
 * It implements {@link Serializable} and has a default serial version ID.
 * <br />It should be extended by all entities in the model, 
 * and each child should fulfill the following requirements :
 * <ul><li>it must declare a {@link javax.persistence.NamedQuery} 
 * whose parameter {@code name} is the concatenation of the entity's name and {@code .findAll}
 * (e.g. {@code Admin.findAll}, or {@code Product.findAll}),
 * and whose parameter {@code query} is a selection from all the entity
 * (e.g. {@code "SELECT a FROM Admin a"}, or {@code "SELECT p FROM Product p"}).</li>
 * <li>its identifier must be a {@link Long}.</li></ul>
 * @author Maic
 */
public abstract class AbstractEntity implements Serializable {

	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Methods //-------------------------------------------------
	@Override
	public final String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
	public int hashCode() {
		return (getId() != null) ? 
				(getClass().getSimpleName().hashCode() + getId().hashCode()) : super.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && isSameClass(other) && getId() != null) ?
				getId().equals(((AbstractEntity) other).getId()) : (other == this);
	}

	// Helpers //-------------------------------------------------
	/**
	 * Verify if {@code this} current instance and the parameter {@code other} belong to the same class.
	 * @param other {@link Object} the parameter.
	 * @return true if the parameter and the current instance are the same class, false otherwise. 
	 */
	private boolean isSameClass(Object other) {
		return other.getClass().isAssignableFrom(getClass()) && getClass().isAssignableFrom(other.getClass());
	}

	// Getters and Setters //-------------------------------------
	/**
	 * This method returns the identifier of the {@link AbstractEntity}.
	 * @return the id a {@link Long}.
	 */
	public abstract Long getId();
	
	/**
	 * This method sets the identifier of the {@link AbstractEntity}.
	 * @param id a {@link Long}.
	 */
	public abstract void setId(Long id);
}