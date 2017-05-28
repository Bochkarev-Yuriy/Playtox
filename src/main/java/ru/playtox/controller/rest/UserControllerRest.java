package ru.playtox.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.playtox.model.Product;
import ru.playtox.model.Purchase;
import ru.playtox.model.User;
import ru.playtox.service.abstr.ProductService;
import ru.playtox.service.abstr.PurchaseService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class UserControllerRest {

	@Autowired
	private PurchaseService purchaseService;

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

	@PostMapping(value = "/purchases")
	public ResponseEntity buyProduct(@RequestBody Purchase purchase) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Long idProduct = purchase.getProduct().getId();
		Product product = productService.getProductById(idProduct);

		if (product != null) {
			Integer buyProductCount = purchase.getCount();
			BigDecimal priceProduct = product.getPrice();

			if (product.getCount() >= buyProductCount) {
				product.setCount(product.getCount() - buyProductCount);
				BigDecimal totalPrice = priceProduct.multiply(new BigDecimal(buyProductCount));
				Purchase newPurchase = new Purchase(user, product, LocalDate.now(Clock.systemUTC()), totalPrice, buyProductCount);
				productService.updateProduct(product);
				purchaseService.addPurchase(newPurchase);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
