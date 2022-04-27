package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (java로 데이터를 영구적으로 저장(DB)할 수 있는 API 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 100,  unique = true) // OAuth2 로그인을 위해 칼럼 늘리기
	private String username; 
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website; // 웹 사이트
	private String bio; // 자기 소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; // 사진
	private String role; // 권한
	
	// 나는 연관관계의 주인이 아니야 그러므로 테이블에 컬럼을 만들지 마
	// user을 select 할 경우에 해당 user Id로 등록된 이미지들을 다 가져와
	// Lazy = user을 select 할때 해당 user Id로 등록된 image들을 가져오지마 - 대신 getImages() 함수가 호출될때 가져와
	// Eager = user를 select 할때 해당 user Id로 등록된 image들을 전부 join 해서 가져와
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) 
	@JsonIgnoreProperties({"user"}) // user getter 호출안되게 함, 무한참조 방지
	private List<Image> images; // 양방향 매핑
	
	// --------- 위의 값만 넣어주면 아래의 createDate는 자동으로 값이 들어감------
	
	private LocalDateTime createDate;
	
	@PrePersist// DB에 insert 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	
}
