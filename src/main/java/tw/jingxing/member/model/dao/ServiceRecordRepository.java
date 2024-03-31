package tw.jingxing.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.ServiceRecord;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Integer> {
	
	// 單筆查詢會員編號(String)
	@Query(value = "from ServiceRecord where rid = ?1")
	public ServiceRecord findRid(String rid);
	
	// 刪除單筆會員資料
	@Modifying
	@Transactional
	@Query(value = "Delete ServiceRecord where rid = ?1")
	public void deleteRid(String rid);
	
}
