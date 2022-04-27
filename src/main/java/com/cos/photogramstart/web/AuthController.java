package com.cos.photogramstart.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI 할때 사용
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;

//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	// 회원가입 버튼 -> /auth/signup -> /auth/signin
	// 회원가입 버튼 x
	@PostMapping("/auth/signup")
	// SignupDto에서 오류가 발생하면 오류를 BindingResult에 다 모아줌
	// responsebody = data를 응답
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		log.info(signupDto.toString());
		// User <- SignupDto

//		if(bindingResult.hasErrors()) {
//			Map<String,String> errorMap = new HashMap<>();
//			
//			//bindingResult의 getFieldErrors에 다 모아줌, for문 돌면서 에러를  error에 담아줌
//			for(FieldError error:bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(),error.getDefaultMessage());
//				System.out.println("===============");
//				System.out.println(error.getDefaultMessage());
//				System.out.println("===============");
//			}
//			throw new CustomValidationException("유효성 검사 실패 :(",errorMap);
//		}else {
		
		// User < - SignupDto
				User user = signupDto.toEntity();
				authService.회원가입(user);
				// System.out.println(userEntity);

				// 로그를 남기는 후처리!!
				return "auth/signin";

			}
		}
