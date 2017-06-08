package ru.playtox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.playtox.model.Product;
import ru.playtox.model.Purchase;
import ru.playtox.model.User;
import ru.playtox.service.abstr.ProductService;
import ru.playtox.service.abstr.PurchaseService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	@Autowired
	private ProductService productService;

	@Autowired
	private PurchaseService purchaseService;

	@GetMapping()
	public ModelAndView getUserIndexPage() {
		return new ModelAndView("user");
	}

	@GetMapping(value = {"/products"})
	public ModelAndView getAllProduct() {
		ModelAndView modelAndView = new ModelAndView("allProductsForUser");
		modelAndView.addObject("products", productService.getAllProduct());
		return modelAndView;
	}

	@PostMapping(value = {"/products/{id}/buy"})
	public synchronized ModelAndView buyProduct(
			@RequestParam(value = "count", required = false) Integer count,
			@PathVariable("id") Long id) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (count > 0) {
			Product product = productService.getProductById(id);

			if (count < product.getCount()) {

				product.setCount(product.getCount() - count);
				productService.updateProduct(product);

				BigDecimal total = product.getPrice().multiply(new BigDecimal(count));
				purchaseService.addPurchase(
						new Purchase(user, product, LocalDate.now(Clock.systemUTC()), total, count));
			} else {
				ModelAndView modelAndView = new ModelAndView("allProductsForUser");
				modelAndView.addObject("products", productService.getAllProduct());
				modelAndView.addObject(
						"error",
						product.getName() + " In stock " + product.getCount() + " it's necessary " + count);
				return modelAndView;
			}
		}
		return getAllProduct();
	}
}
