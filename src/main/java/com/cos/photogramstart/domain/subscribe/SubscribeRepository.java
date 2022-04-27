package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	// INSERT, DELETE, UPDATE를 native 쿼리로 작성하려면 해당 annotation이 필요함
	@Modifying
	// :의 의미 : int fromUserId 변수를 바인드해서 넣겠다!
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId, now())",nativeQuery=true)
	void mSubscribe(int fromUserId, int toUserId);
		
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId",nativeQuery=true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId =:principalId AND toUserId = :pageUserId",nativeQuery=true)
	int mSubscribeState(int principalId, int pageUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId =:pageUserId",nativeQuery = true)
	int mSubscribeCount(int pageUserId);
}
