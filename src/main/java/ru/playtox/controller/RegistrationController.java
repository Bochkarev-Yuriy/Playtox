package ru.playtox.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.playtox.model.roles.Role;
import ru.playtox.model.users.User;
import ru.playtox.service.abstr.RoleService;
import ru.playtox.service.abstr.UserService;
import ru.playtox.util.PasswordValidator;

import javax.validation.Valid;
import java.util.*;

@Controller
public class RegistrationController {

	private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView saveNewUser() {
		ModelAndView model = new ModelAndView("registration");
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView getRegistrationForm(@RequestParam(value = "confirmPassword") String confirmPassword,
											@Valid User userFromPage, BindingResult bindingResult) {


		ModelAndView model = new ModelAndView("registration");
		model.addObject("user", userFromPage);

		boolean isConfEqualToPass =
				PasswordValidator.validatePassword(userFromPage.getPassword(), confirmPassword);
		if (bindingResult.hasErrors()) {
			return model;
		} else if (!isConfEqualToPass) {
			model.addObject("errorConfirm", "Error confirm pass");
			return model;
		}
		String username = userFromPage.getUsername();
		boolean isUser = userService.getUserByUsername(username) != null;
		if (isUser) {
			model.addObject("errorEmail", "Duplicate email");
			return model;
		} else {
			Role role = roleService.getRoleByRoleName("USER");
			Set<Role> roles = new LinkedHashSet<>();
			roles.add(role);
			userFromPage.setRoles(roles);
			userService.addUser(userFromPage);
			model.addObject("congratulations", "registration completed successfully");
		}

		return model;
	}
}
