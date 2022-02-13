package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web.ignoring()
		.antMatchers("/webjars/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/h2-console/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ログイン不要ページの設定	
		http.authorizeHttpRequests()
			.antMatchers("/login").permitAll() // 直リンクOK
			.antMatchers("/user/signup").permitAll() // 直リンクOK
			.anyRequest().authenticated(); // それ以外は直リンクNG

		// ログイン処理
		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("userId")
			.passwordParameter("password")
			.defaultSuccessUrl("/user/list", true);
		
		// CSRF対策を無効に設定（一時的）
		http.csrf().disable();
	}

	/** 認証の設定 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		// インメモリ認証
//		auth.inMemoryAuthentication()
//			.withUser("user")
//				.password(encoder.encode("user"))
//				.roles("GENERAL")
//			.and()
//			.withUser("admin")
//				.password(encoder.encode("admin"))
//				.roles("ADMIN");

		
		// ユーザーデータ認証
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}

}