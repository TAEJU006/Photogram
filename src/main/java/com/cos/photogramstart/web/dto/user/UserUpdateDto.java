package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	private String name; // 필수
	private String password; // 필수
	private String website;
	private String bio;
	private String phone;
	private String gender;

	// 조금 위험함, 코드 수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.name(name) // 이름을 미입력 했을 경우에 문제! Validation 체크
				.password(password) // 사용자가 패스워드를 미입력 했을 경우에 DB에 아무것도 없는 비밀번호가 들어옴! Validation 체크
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
