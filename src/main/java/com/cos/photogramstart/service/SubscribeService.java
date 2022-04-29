package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // Repository는 EntityManager를 구현해서 만들어져 있는 구현체

	@Transactional(readOnly=true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id,u.username,u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id),1,0) subscribeState, ");
		sb.append("if((?=u.id),1,0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.touserid ");
		sb.append("WHERE s.fromUserId = ? "); // ; 입력 x
		
		// 1. ? => principalId
		// 2. ? => principalId
		// 3. ? => pageUserId
		
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
			.setParameter(1,principalId)
			.setParameter(2,principalId)
			.setParameter(3,pageUserId);
		
		// 쿼리 실행(qlrm 라이브러리 필요 = DTO에 DB 결과를 mapping하기 위하여)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}

	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독 했어요");
		}
	}

	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
