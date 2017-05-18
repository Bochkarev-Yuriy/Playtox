package ru.playtox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.playtox.models.products.Product;
import ru.playtox.service.abstr.ProductService;
import ru.playtox.service.abstr.PurchaseService;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

	@Autowired
	private ProductService productService;

	@Autowired
	private PurchaseService purchaseService;

	@GetMapping(value = "")
	public ModelAndView getAdminIndexPage() {
		return new ModelAndView("admin");
	}

	@GetMapping(value = "/products")
	public ModelAndView allProductsGet() {
		ModelAndView modelAndView = new ModelAndView("allProducts");
		modelAndView.addObject("products", productService.getAllProduct());
		return modelAndView;
	}

	@GetMapping(value = "/product/add")
	public ModelAndView addProductsGet() {
		ModelAndView modelAndView = new ModelAndView("addProduct");
		modelAndView.addObject("product", new Product());
		return modelAndView;
	}

	@PostMapping(value = "/product/add")
	public String addProductsPost(Product product) {
		productService.addProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping(value = "/product/{id}/delete")
	public String deleteProductsGet(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping(value = "/product/{id}/update")
	public ModelAndView updateProductsGet(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		ModelAndView modelAndView = new ModelAndView("updateProduct");
		modelAndView.addObject("product", product);
		return modelAndView;
	}

	@PostMapping(value = "/product/{id}/update")
	public String updateProductsPost(@PathVariable("id") Long id, Product product) {
		product.setId(id);
		productService.updateProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping(value = "/purchases")
	public ModelAndView allpurchasesGet() {
		ModelAndView modelAndView = new ModelAndView("allPurchases");
		modelAndView.addObject("purchases", purchaseService.getAllPurchase());
		return modelAndView;
	}

}
