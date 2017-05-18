package ru.playtox.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.impl.exceptions.MergeException;
import ru.playtox.dao.impl.exceptions.PersistException;
import ru.playtox.dao.impl.exceptions.RemoveException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {


	@PersistenceContext
	EntityManager entityManager;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@SuppressWarnings("unchecked")
	public T getEntityByKey(PK key) {
		return entityManager.find(persistentClass, key);
	}

	public void update(T entity) {
		entityManager.merge(entity);
//		try {
//			entityManager.merge(entity);
//		} catch (HibernateException e) {
//			throw new MergeException("Failed to merge an object", e);
//		}
	}

	public List<T> getAllEntity() {
		String genericClassName = persistentClass.toGenericString();
		genericClassName = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);
		String hql = "FROM " + genericClassName;
		return entityManager.createQuery(hql).getResultList();
	}

	public void persist(T entity) {
		entityManager.persist(entity);
//		try {
//			entityManager.persist(entity);
//		} catch (HibernateException e) {
//			throw new PersistException("Failed to add an object", e);
//		}
	}

	public void deleteByKey(PK pk) {
		T entity = entityManager.find(persistentClass, pk);
		entityManager.remove(entity);
//		try {
//			entityManager.remove(entity);
//		} catch (HibernateException e) {
//			throw new RemoveException("Failed to deleting an object", e);
//		}
	}

	protected Session getSession() {
		return (Session) entityManager.getDelegate();
	}
}