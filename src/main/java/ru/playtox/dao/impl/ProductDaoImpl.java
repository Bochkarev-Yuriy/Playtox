package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abstr.ProductDao;
import ru.playtox.model.products.Product;

import java.util.List;

@Transactional
@Repository
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

	@Override
	public void deleteByKey(Long id) {
		entityManager.createQuery(
				"UPDATE Product SET isAvailable = false WHERE id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Product> getAllEntity() {
		return entityManager.createQuery(
				"SELECT ot FROM Product ot WHERE ot.isAvailable = true", Product.class).getResultList();
	}
}
