package com.jomm.terroir.dao;

import java.io.Serializable;
import java.util.List;

/**
 * This Interface describes all CRUD operations involving a {@link javax.persistence.Entity}.
 * @author Maic
 *
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
public interface Dao<E extends Serializable> {
	
	/**
	 * Fetch an {@link javax.persistence.Entity}.
	 * @param id {@link Long} the entity's id.
	 * @return E the {@link javax.persistence.Entity}.
	 */
	E find(Long id);

	/**
	 * Fetch all {@link javax.persistence.Entity}.
	 * @return a list of {@link javax.persistence.Entity}.
	 */
	List<E> findAll();

	/**
	 * Create and persist a new {@link javax.persistence.Entity}.
	 * @param entity the {@link javax.persistence.Entity}.
	 * @return the persisted {@link javax.persistence.Entity}.
	 */
	E create(E entity);

	/**
	 * Update a previously persisted {@link javax.persistence.Entity}.
	 * @param entity the {@link javax.persistence.Entity}.
	 * @return the updated {@link javax.persistence.Entity}.
	 */
	E update(E entity);

	/**
	 * Delete an {@link javax.persistence.Entity}.
	 * @param entity the {@link javax.persistence.Entity}.
	 */
	void delete(E entity);

	/**
	 * Delete an {@link javax.persistence.Entity} using its id.
	 * @param entityId {@link Long} the entity's id.
	 */
	void deleteById(Long entityId);
	
	/**
	 * Get the {@link javax.persistence.Entity}'s class.
	 * @return the class of the entity.
	 */
	Class<E> getEntityClass();
}
