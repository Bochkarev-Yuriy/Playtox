package ru.playtox.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.playtox.models.products.Product;
import ru.playtox.models.purchases.Purchase;
import ru.playtox.models.users.User;
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

	@GetMapping(value = "")
	public ModelAndView getUserIndexPage() {
		return new ModelAndView("user");
	}

	@GetMapping(value = {"/products"})
	public ModelAndView getAllProduct() {
		ModelAndView modelAndView = new ModelAndView("allProductsUser");
		modelAndView.addObject("products", productService.getAllProduct());
		return modelAndView;
	}

	@PostMapping(value = {"/products/{id}/buy"})
	public String buyProduct(@RequestParam(value = "number", required = false) String number, @PathVariable("id") Long id) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer numbers = Integer.valueOf(number);

		if (numbers > 0) {
			// TODO если получать готовый обьек со вью, то можно сыкономить на запросе.
			Product product = productService.getProductById(id);
			product.setCount(product.getCount() - numbers);
			productService.updateProduct(product);
			BigDecimal total = product.getPrice().multiply(new BigDecimal(numbers));
			purchaseService.addPurchase(
					new Purchase(user, product, LocalDate.now(Clock.systemUTC()), total, numbers));
		}

		return "redirect:/user/products";
	}

}
