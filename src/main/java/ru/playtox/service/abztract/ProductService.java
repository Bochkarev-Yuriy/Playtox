package ru.playtox.service.abztract;


import ru.playtox.models.products.Product;

import java.util.List;

public interface ProductService {

	void addProduct(Product product);

	void deleteProductById(Long id);

	void updateProduct(Product product);

	Product getProductById(Long id);

	List<Product> getAllProduct();
}
