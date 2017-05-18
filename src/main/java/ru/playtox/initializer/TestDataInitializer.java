package ru.playtox.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import ru.playtox.models.products.Product;
import ru.playtox.models.roles.Role;
import ru.playtox.models.users.User;
import ru.playtox.service.abstr.ProductService;
import ru.playtox.service.abstr.RoleService;
import ru.playtox.service.abstr.UserService;

import java.math.BigDecimal;
import java.util.*;

public class TestDataInitializer {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	private void init() {

//		<---Creating roles--->
		Role roleAdmin = new Role("ADMIN");
		Role roleUser = new Role("USER");

//		<---Adding roles into a DB--->
		roleService.addRole(roleAdmin);
		roleService.addRole(roleUser);

//		<---Creating users--->
		Set<Role> roleListAdmin = new HashSet();
		roleListAdmin.add(roleAdmin);
		User admin = new User("admin@mail.ru", "admin", roleListAdmin);

//		<---Adding users into a DB--->
		userService.addUser(admin);

//		<---Creating product--->
		Product book = new Product("Head First Java", "Java Programming", new BigDecimal(199.9), 10);
		Product lamp = new Product("Lamp", "Book lamp", new BigDecimal(110), 30);
		Product pencil = new Product("Pencil", "wooden", new BigDecimal(49.99), 1000);

//		<---Adding product into a DB--->
		productService.addProduct(book);
		productService.addProduct(lamp);
		productService.addProduct(pencil);
	}

}
