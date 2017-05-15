package ru.playtox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abztract.ProductDao;
import ru.playtox.models.products.Product;
import ru.playtox.service.abztract.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.persist(product);
	}

	@Override
	public void deleteProductById(Long id) {
		productDao.deleteByKey(id);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.update(product);
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
