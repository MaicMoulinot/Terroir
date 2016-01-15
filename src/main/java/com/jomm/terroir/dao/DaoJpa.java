package com.jomm.terroir.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DaoJpa<K, E> implements Dao<K, E> {

	protected Class<E> entityClass;
	
	@Inject
	@PersistenceContext(name="terroirPU")
	protected EntityManager entityManager;

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
