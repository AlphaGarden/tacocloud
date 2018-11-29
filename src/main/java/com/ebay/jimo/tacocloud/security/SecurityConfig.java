package com.ebay.jimo.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepositoryUserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder encoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
  }

  /**
   * @throws Exception Things we can do with HttpSecurity On Web Level. 1. Requiring that certain security condition be met before allowing a request to be served. 2. Configuring a
   * custom login page. 3. Enabling users to log out of the application 4. Configuration cross-site request forgery protection. （This configuration will intercept the request
   * ensure that the user has proper authority to be served.）
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/design", "/orders")
        .hasRole("USER") // only allow user with role user to be served
        .antMatchers("/", "/*").permitAll()
        .and()
        .formLogin()
        .loginPage("/login")// allow all request be served.
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/design", true)
        .and()
        .logout()
        .logoutSuccessUrl("/"); // Force navigate to design page.
  }
}
