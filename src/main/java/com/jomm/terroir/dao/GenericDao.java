package com.jomm.terroir.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 * This abstract Class describes all persisting operations for an {@link Entity} using JPA.
 * It implements {@link Dao} and defines all its business methods.
 * It requires each {@link Entity} to declare a {@link NamedQuery} "entityName.findAll".
 * When constructed with the no-arg constructor, it sets the attribute entityClass.
 * @author Maic
 *
 * @param <K> {@link Long} is the Key's type.
 * @param <E> {@link Entity} is the Entity's type.
 */
public abstract class DaoJpa<K, E> implements Dao<K, E> {

	protected Class<E> entityClass;
	
	@Inject
	@PersistenceContext(name="terroirPU")
	protected EntityManager entityManager;

	/**
	 * Constructor with no argument.
	 * Set the attribute entityClass properly.
	 */
	@SuppressWarnings("unchecked")
	public DaoJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	@Override
	public void persist(E entity) {
		entityManager.persist(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public void remove(E entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<E> findAll() {
		return entityManager.createNamedQuery(entityClass.getSimpleName() + ".findAll").getResultList();
	}
}
