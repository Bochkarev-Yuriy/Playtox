package ru.playtox.dao.abstr;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {

	void persist(T entity);

	T getEntityByKey(PK id);

	List<T> getAllEntity();

	void update(T entity);

	void deleteByKey(PK id);
}
