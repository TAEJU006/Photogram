package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 1. IOC, 2. 파일을 리턴하는 controller
public class AuthController {

	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원가입 버튼 -> /auth/signup -> /auth/signin
	//회원가입 버튼 x
	@PostMapping("/auth/signup")
	public String signup() {
		System.out.println("signup실행됨");
		return "auth/signin";
	}
}
