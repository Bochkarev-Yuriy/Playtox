package ru.playtox.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abstr.ProductDao;
import ru.playtox.dao.impl.exceptions.MergeException;
import ru.playtox.dao.impl.exceptions.PersistException;
import ru.playtox.dao.impl.exceptions.RemoveException;
import ru.playtox.model.products.Product;
import ru.playtox.service.abstr.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		try {
			productDao.persist(product);
			logger.info("Added : " + product);
		} catch (HibernateException e) {
			logger.error("Failed to add an product " + product);
			throw new PersistException("Failed to add an product", e);
		}
	}

	@Override
	public void deleteProductById(Long id) {
		try {
			productDao.deleteByKey(id);
			logger.info("Deleted product id=" + id);
		} catch (HibernateException e) {
			logger.error("Failed to deleted an product id=" + id);
			throw new RemoveException("Failed to deleted an product", e);
		}
	}

	@Override
	public void updateProduct(Product product) {
		try {
			productDao.update(product);
			logger.info("Update : " + product);
		} catch (HibernateException e) {
			logger.error("Failed to update an product " + product);
			throw new MergeException("Failed to update an product", e);
		}
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getEntityByKey(id);
	}

	@Override
	public List<Product> getAllProduct() {
		return productDao.getAllEntity();
	}
}
