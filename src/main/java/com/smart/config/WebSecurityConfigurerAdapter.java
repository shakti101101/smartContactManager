//package com.smart.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//public class WebSecurityConfigurerAdapter {
//
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**").hasRole("USER")
//		.requestMatchers("/**").permitAll().and().formLogin().loginPage("/signin").and().csrf().disable();
//		
//	}
//
//}
