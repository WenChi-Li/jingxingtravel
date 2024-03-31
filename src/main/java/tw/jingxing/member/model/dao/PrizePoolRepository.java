package tw.jingxing.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.PrizePool;

public interface PrizePoolRepository extends JpaRepository<PrizePool, Integer> {
	
	// 單筆獎池(String)
	@Query(value = "from PrizePool where prizepoolID = ?1")
	public PrizePool findPrizePoolString(String prizepoolID);
	
	@Modifying
	@Transactional
	@Query("UPDATE PrizePool p SET p.prize.prizeID = :newPrizeID WHERE p.prizepoolID = :prizepoolID")
	void changePrizePool(@Param("newPrizeID") String newPrizeID, @Param("prizepoolID") String prizepoolID);
	

}
