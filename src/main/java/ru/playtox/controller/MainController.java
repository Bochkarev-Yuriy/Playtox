package ru.playtox.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.playtox.model.roles.Role;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/index"})
	public String indexPage(Authentication authentication) {

		if (authentication == null) {
			return "index";
		} else if (authentication.getAuthorities().contains(new Role("ADMIN"))) {
			return "admin";
		} else if (authentication.getAuthorities().contains(new Role("USER"))) {
			return "user";
		} else {
			return "/";
		}
	}
}
