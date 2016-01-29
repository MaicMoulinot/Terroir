package com.jomm.terroir.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

/**
 * This Interface describes all CRUD operations involving a {@link Entity}.
 * @author Maic
 *
 * @param <E> {@link Entity} is the Entity's type, which extends {@link Serializable}.
 */
public interface Dao<E extends Serializable> {
	
	/**
	 * Fetch an {@link Entity}.
	 * @param id {@link Long} the entity's id.
	 * @return E the {@link Entity}.
	 */
	public E find(Long id);

	/**
	 * Fetch all {@link Entity}.
	 * @return a list of {@link Entity}.
	 */
	public List<E> findAll();

	/**
	 * Create and persist a new {@link Entity}.
	 * @param entity the {@link Entity}.
	 */
	public void create(E entity);

	/**
	 * Update a previously persisted {@link Entity}.
	 * @param entity the {@link Entity}.
	 * @return the updated {@link Entity}.
	 */
	public E update(E entity);

	/**
	 * Delete an {@link Entity}.
	 * @param entity the {@link Entity}.
	 */
	public void delete(E entity);

	/**
	 * Delete an {@link Entity} using its id.
	 * @param entityId {@link Long} the entity's id.
	 */
	public void deleteById(Long entityId);
}
