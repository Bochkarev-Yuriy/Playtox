package ru.playtox.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.playtox.security.handlers.CustomAuthenticationSuccessHandler;
import ru.playtox.security.service.AuthenticationService;

@Configuration
@ComponentScan("ru.playtox")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.csrf().disable().addFilterBefore(filter, CsrfFilter.class);
		http.authorizeRequests()
				.antMatchers("/user/**").hasAnyAuthority("USER")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.successHandler(customAuthenticationSuccessHandler)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				.logout().logoutSuccessUrl("/login?logout");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService);
	}
}
