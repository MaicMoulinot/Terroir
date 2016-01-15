package com.jomm.terroir.dao;

import java.util.Collection;

/**
* K is the Key's type (usually Long or String).
* E is the Entity's type (managed by this DAO).
*/
public interface Dao<K, E> {
	
	/**
	 * Persist an entity.
	 * @param entity E the entity to persist.
	 */
	void persist(E entity);
	
	/**
	 * Remove an entity.
	 * @param entity E the entity to delete.
	 */
	void remove(E entity);
	
	/**
	 * Fetch an entity
	 * @param id K the index of the entity.
	 * @return E an entity.
	 */
	E findById(K id);
	
	/**
	 * Fetch all entities.
	 * @return a collection of entities.
	 */
	Collection<E> findAll();
}
