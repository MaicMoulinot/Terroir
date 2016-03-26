package com.jomm.terroir.dao;

import static com.jomm.terroir.util.Constants.PERSISTENCE_UNIT;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jomm.terroir.business.model.AbstractEntity;

/**
 * This abstract Class defines all CRUD operations involving an {@link AbstractEntity}.
 * It implements {@link Dao} and defines all its methods using JPA.
 * The no-arg constructor sets the attribute {@link DaoGenericJpa#entityClass} with the appropriate {@link Class}.
 * @author Maic
 *
 * @param <E> the entity's type extending {@link AbstractEntity}.
 */
public abstract class DaoGenericJpa<E extends AbstractEntity> implements Dao<E> {

	protected Class<E> entityClass;
	
	@Inject
	@PersistenceContext(name = PERSISTENCE_UNIT)
	protected EntityManager entityManager;

	/**
	 * Constructor with no argument.
	 * Set the {@code entityClass} dynamically.
	 * It is annotated {@link SuppressWarnings} to suppress the warning 
	 * {@code Type safety: Unchecked cast from Type to Class<E>}.
	 */
	@SuppressWarnings("unchecked")
	public DaoGenericJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
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
		List<E> result = new ArrayList<>();
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
}