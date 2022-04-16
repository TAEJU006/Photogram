package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// annotation 없어도 JpaRepository를 상속했으면 IOC 등록이 자동으로 됨
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA query method
	User findByUsername(String username);
}
