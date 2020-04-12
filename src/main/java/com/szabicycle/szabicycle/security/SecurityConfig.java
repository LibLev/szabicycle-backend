package com.szabicycle.szabicycle.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
This class configures Spring Security.
By default Spring Security locks down all your endpoints and one must use HTTP Basic authentication to access anything,
we need to override this with our configuration.
*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    // By overriding this function and
    // adding the @Bean annotation we can inject the AuthenticationManager into other classes.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // The main point for configuring Spring Security.
    // In Spring Security every request goes trough a chain of filters; each filter checks the request for something
    // and if one fails the request will fail.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // By default Spring Security uses HTTP Basic authentication, we disable this filter.
                .csrf().disable() // Disable CSRF. Leaving it enabled would ignore GET, HEAD, TRACE, OPTIONS
                .cors().and()
                // Disable Tomcat's session management. This causes HttpSession to be null and no session cookie to be created
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // restrict access based on the config below:
                .antMatchers("/auth/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/get-all-product").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/get-all-bicycle").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/get-all-component").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/product/{productId}").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/downloadFile/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.POST,"/send-mail").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.POST, "/saveProduct").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.POST, "/uploadFile").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.POST, "/uploadMultipleFiles").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.DELETE, "/deleteProduct/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .antMatchers(HttpMethod.POST, "/updateProduct").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .anyRequest().denyAll() // anything else is denied; this is a safeguard in case we left something out.
                .and()
                // Here we define our custom filter that uses the JWT tokens for authentication.
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }
}

