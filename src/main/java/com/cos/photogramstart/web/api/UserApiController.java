package com.cos.photogramstart.web.api;


import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	
	@PutMapping("/api/user/{id}")
	// @AuthenticationPrincipal -> session 정보에 접근
	public CMRespDto<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,// 꼭 @valid 적혀있는 파라메터 다음에 적어야 함
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
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
//			throw new CustomValidationApiException("유효성 검사 실패 :(",errorMap);
//		}else {
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity); // 세션 정보 변경
			return new CMRespDto<>(1, "회원수정완료", userEntity); // 응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
		
	}
}

