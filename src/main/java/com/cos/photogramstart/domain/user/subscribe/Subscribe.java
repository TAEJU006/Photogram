package com.cos.photogramstart.domain.user.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="subscribe_uk",
						columnNames = {"fromUserId"," toUserId"}
						)
				
		}
		)
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name="fromUserId") // 이렇게 컬럼명 만들어! 스키마 만들땐 항상 create로 수정 후 생성함
	@ManyToOne // subscribe table => many , 이 annotation을 붙이면 자동으로 subscribe table 생성함
	private User fromUser;

	@JoinColumn(name="toUserId")
	@ManyToOne
	private User toUser;

	private LocalDateTime createDate;

	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
