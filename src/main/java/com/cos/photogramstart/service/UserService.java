package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호는 암호화를 해서 넣어줘야 함

	@Transactional // 데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산을 사용할때 활용하는 annotation
	public User 회원수정(int id,User user) {
		// 1. 영속화
		// 회원이 있는지 없는지 찾아서 userEntity에 담는다
		// 1. 무조건 찾았다. 걱정마 get() 2. 못 찾았어 exception 실행 시킬게 orElseThrow()
		// CustomValidationException이 발생이 되면 controllerExceptionHandler가 낚아 챔
		User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");});
		
		// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
				userEntity.setName(user.getName());
		
				String rawPassword = user.getPassword();
				String encPassword = bCryptPasswordEncoder.encode(rawPassword);
				
				userEntity.setPassword(encPassword);
				userEntity.setBio(user.getBio());
				userEntity.setWebsite(user.getWebsite());
				userEntity.setPhone(user.getPhone());
				userEntity.setGender(user.getGender());
				return userEntity;
			} // 더티체킹이 일어나서 업데이트가 완료됨.
			

		}
