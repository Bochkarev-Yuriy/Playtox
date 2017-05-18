package ru.playtox.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.playtox.model.roles.Role;
import ru.playtox.model.users.User;

@Controller
public class HelloController {

	@GetMapping(value = {"/", "/index"})
	public String indexPage(Authentication authentication) {

		if (authentication == null) {
			return "index";
		} else if (authentication.getAuthorities().contains(new Role("ADMIN"))) {
			return "admin";
		} else if (authentication.getAuthorities().contains(new Role("USER"))) {
			return "user";
		} else {
			return "/hello";
		}
	}

	@GetMapping(value = "/hello")
	public ModelAndView getUserIndexPage() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView("hello");
		modelAndView.addObject("hello", String.format("Hello, %s!", user.getUsername()));
		return modelAndView;
	}
}
