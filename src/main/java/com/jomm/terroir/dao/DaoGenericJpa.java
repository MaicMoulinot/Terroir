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
import javax.persistence.TypedQuery;

/**
 * This abstract Class defines all CRUD operations involving a {@link Entity}.
 * It implements {@link Dao} and defines all its methods using JPA.
 * The no-arg constructor sets the attribute entityClass with the appropriate {@link Class}.
 * <br />It requires 3 conditions from any {@link Entity} :
 * <ul><li>the entity must declare a {@link NamedQuery} named "entityName.findAll",</li>
 * <li>the entity's id must be a {@link Long},</li>
 * <li>and the entity should implement {@link Serializable}.</li></ul>
 * @author Maic
 *
 * @param <E> {@link Entity} is the Entity's type, which extends {@link Serializable}.
 */
public abstract class DaoGenericJpa<E extends Serializable> implements Dao<E> {

	protected Class<E> entityClass;
	
	@Inject
	@PersistenceContext(name="terroirPU")
	protected EntityManager entityManager;

	/**
	 * Constructor with no argument.
	 * Set the attribute entityClass dynamically.
	 */
	@SuppressWarnings("unchecked")
	public DaoGenericJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = ((Class<E>) genericSuperclass.getActualTypeArguments()[0]);
	}
	
	@Override
	public E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	public E update(E entity) {
		return entityManager.merge(entity);
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
		List<E> result = new ArrayList<E>();
	    TypedQuery<E> query = entityManager.createNamedQuery(getEntityClass().getSimpleName() + ".findAll", getEntityClass());
	    if (query != null) {
	    	result = query.getResultList();
	    }
		return result;
	}

	@Override
	public Class<E> getEntityClass() {
		return entityClass;
	}

	/**
	 * @return the entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}