package com.cos.photogramstart.web.dto.user;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerState;// 페이지 주인인지 아닌지에 대한 데이터
	private int imageCount;
	private boolean subscribeState; // 구독을 한 상태 인가요
	private int subscribeCount;
	private User user;
}
