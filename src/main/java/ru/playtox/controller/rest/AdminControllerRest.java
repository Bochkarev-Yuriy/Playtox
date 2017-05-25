package ru.playtox.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.playtox.model.products.Product;
import ru.playtox.service.abstr.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/admin")
public class AdminControllerRest {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, product != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProduct();
		return new ResponseEntity<>(products, products != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/products")
	public ResponseEntity addProduct(@RequestBody Product product) {

		if (product.getId() != null || product.getName().length() < 2) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		productService.addProduct(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(value = "/products/{productId}")
	public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody Product product) {
		Product productBd = productService.getProductById(productId);

		if (productBd != null && productBd.isAvailable() || product.getName().length() < 2) {
			product.setId(productId);
			productService.updateProduct(product);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity deleteProductById(@PathVariable Long productId) {
		Product productBd = null;

		if (productId > 0) {
			productBd = productService.getProductById(productId);
		}
		if (productBd != null && productBd.isAvailable()) {
			productService.deleteProductById(productId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
