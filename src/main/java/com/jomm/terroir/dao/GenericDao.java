package com.jomm.terroir.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 * This abstract Class describes all persisting operations for any {@link Entity}.
 * It implements {@link Dao} and defines all its CRUD operations using JPA.
 * It requires each {@link Entity} to declare a {@link NamedQuery} "entityName.findAll", 
 * and each entity's id must be a {@link Long}.
 * The no-arg constructor sets the attribute entityClass with the appropriate {@link Class}.
 * @author Maic
 *
 * @param <E> {@link Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class GenericDao<E extends Serializable> implements Dao<E> {

	protected Class<E> entityClass;
	
	@Inject
	@PersistenceContext(name="terroirPU")
	protected EntityManager entityManager;

	/**
	 * Constructor with no argument.
	 * Set the attribute entityClass dynamically.
	 */
	@SuppressWarnings("unchecked")
	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		setEntityClass((Class<E>) genericSuperclass.getActualTypeArguments()[1]);
	}
	
	@Override
	public void create(E entity) {
		entityManager.persist(entity);
	}
	
	@Override
	public E update(E entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	public void delete(E entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	@Override
	public void deleteById(Long entityId) {
		entityManager.remove(entityManager.getReference(getEntityClass(), entityId));
	}
    
	@Override
	public E find(Long id) {
		return entityManager.find(getEntityClass(), id);
	}
	
	@Override
	public List<E> findAll() {
	    List<E> castedList = new ArrayList<E>();
	    for(Object obj : entityManager.createNamedQuery(getEntityClass().getSimpleName() + ".findAll").getResultList()) {
	    	castedList.add(getEntityClass().cast(obj));
	    }
		return castedList;
	}

	/**
	 * @return the entityClass
	 */
	public Class<E> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
}
