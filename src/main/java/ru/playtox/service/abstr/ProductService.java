package ru.playtox.service.abstr;

import ru.playtox.model.Product;

import java.util.List;

public interface ProductService {

	void addProduct(Product product);

	void deleteProductById(Long id);

	void updateProduct(Product product);

	Product getProductById(Long id);

	List<Product> getAllProduct();
}
