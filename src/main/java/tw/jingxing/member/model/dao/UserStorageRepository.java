package tw.jingxing.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.UserStorage;

public interface UserStorageRepository extends JpaRepository<UserStorage, Integer> {
	
	//刪除用戶獎品
	@Modifying
	@Transactional
	@Query("DELETE UserStorage WHERE usid = ?1")
	void deleteByUsid(String usid);
}
