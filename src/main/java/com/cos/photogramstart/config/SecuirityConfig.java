package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // loc
public class SecuirityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화
		http.csrf().disable(); // csrf 토큰 비활성화
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**")
				.authenticated() // 여기에 해당하는 페이지는 인증을 해야만 접근이 가능함
				.anyRequest().permitAll() // 위 주소 이외의 다른 주소는 접근 가능함
				.and().formLogin().loginPage("/auth/signin") // 위 주소 접근 요청을 하면 이곳으로 가게 하겠음
				.defaultSuccessUrl("/"); // 로그인을 잘 처리하면 /로 이동을 허락함
	}
}
