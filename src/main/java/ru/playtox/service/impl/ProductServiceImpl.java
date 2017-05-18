package ru.playtox.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abstr.ProductDao;
import ru.playtox.model.products.Product;
import ru.playtox.service.abstr.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(Product.class);

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.persist(product);
		logger.info("Added product " + product);
	}

	@Override
	public void deleteProductById(Long id) {
		productDao.deleteByKey(id);
		//TODO нужна инфа о продукте
		logger.info("Deleted product " + id);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.update(product);
		//TODO нужна старая инфа о продукте
		logger.info("Updated product " + product);
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
