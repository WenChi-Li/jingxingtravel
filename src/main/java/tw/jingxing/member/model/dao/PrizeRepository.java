package tw.jingxing.member.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.Prize;

public interface PrizeRepository extends JpaRepository<Prize, Integer> {
	
	// 單筆查詢會員編號(String)
	@Query(value = "from Prize where prizeID = ?1")
	Prize findPrizeString(String mid);
	
	// 單筆查詢會員編號(String)(Optional)
	@Query(value = "from Prize where prizeID = ?1")
	Optional<Prize> findPrizeStringOptional(String mid);
	
	// 更新獎池
	@Modifying
	@Transactional
	@Query("UPDATE Prize SET prizeName = ?1, discount = ?2, prizePic = ?3 WHERE prizeID = ?4")
	void updatePrizePool(String prizeName, String discount, byte[] prizePic, String prizeID);
	
	// 更新獎池沒圖片
	@Modifying
	@Transactional
	@Query("UPDATE Prize SET prizeName = ?1, discount = ?2 WHERE prizeID = ?3")
	void updatePrizePoolNoPic(String prizeName, String discount, String prizeID);

	
	// 新增獎品
	@Modifying
	@Transactional
	@Query("INSERT INTO Prize (prizeName, discount, prizePic) VALUES (?1, ?2, ?3)")
	void newPrize(String prizeName, String discount, byte[] prizePic);
	
	//刪除獎品
	@Modifying
	@Transactional
	@Query("DELETE Prize WHERE prizeID = ?1")
	void deleteByPrizeID(String prizeID);
	


	
	
}
