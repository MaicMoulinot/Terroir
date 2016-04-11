package com.jomm.terroir.dao;

import java.util.List;

import com.jomm.terroir.business.model.AbstractEntity;

/**
 * This Interface describes all CRUD operations involving a {@link AbstractEntity}.
 * @author Maic
 *
 * @param <E> {@link AbstractEntity} is the Entity's type, which extends {@link AbstractEntity}.
 */
public interface Dao<E extends AbstractEntity> {
	
	/**
	 * Fetch an {@link AbstractEntity}.
	 * @param id {@link Long} the entity's id.
	 * @return E the {@link AbstractEntity}.
	 */
	E find(Long id);

	/**
	 * Fetch all {@link AbstractEntity}.
	 * @return a list of {@link AbstractEntity}.
	 */
	List<E> findAll();

	/**
	 * Create and persist a new {@link AbstractEntity}.
	 * @param entity the {@link AbstractEntity}.
	 * @return the persisted {@link AbstractEntity}.
	 */
	E create(E entity);

	/**
	 * Update a previously persisted {@link AbstractEntity}.
	 * @param entity the {@link AbstractEntity}.
	 * @return the updated {@link AbstractEntity}.
	 */
	E update(E entity);

	/**
	 * Delete an {@link AbstractEntity}.
	 * @param entity the {@link AbstractEntity}.
	 */
	void delete(E entity);

	/**
	 * Delete an {@link AbstractEntity} using its id.
	 * @param id {@link Long} the entity's id.
	 */
	void deleteById(Long id);
	
	/**
	 * Get the {@link AbstractEntity}'s class.
	 * @return the class of the entity.
	 */
	Class<E> getEntityClass();
}