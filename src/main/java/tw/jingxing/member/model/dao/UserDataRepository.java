package tw.jingxing.member.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {

	// 登入檢查帳號
	@Query(value = "SELECT * FROM UserData where (userAcc COLLATE Latin1_General_CS_AS = ?1 "
			+ "OR email COLLATE Latin1_General_CS_AS= ?2) AND userPwd COLLATE Latin1_General_CS_AS= ?3", nativeQuery = true)
	UserData accountCheck(String userAcc, String email, String userPwd);

	
	// 上傳背景照片
	 @Modifying
	 @Transactional
	 @Query("UPDATE UserData SET userBackGround = ?1 WHERE mid = ?2")
	 void updateBackGround(byte[] userBackGround, String mid);
	
	
	
	// 上傳個人照片
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userPic = ?1 WHERE mid = ?2")
	void updateUserPic(byte[] userPic, String mid);

	// 更改成預設圖片
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userPic = ?1 WHERE mid = ?2")
	void updateDefaultPic(byte[] userPic, String mid);

	// 更新會員資料
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userName = ?1, email = ?2, gender = ?3, birthday = ?4, address = ?5, userTel = ?6, validation = ?7 WHERE mid = ?8")
	void updateData(String userName, String email, String gender, String birthday, String address, String userTel,
			String validation, String mid);

	// 查詢密碼
	@Query(value = "SELECT userPwd FROM UserData where mid = ?1")
	String searchPwd(String mid);

	// 更新密碼
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userPwd = ?1 WHERE mid = ?2")
	void updatePwd(String userPwd, String mid);

	// 更新抽獎次數
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET lotteryTimes = ?1, lastLotteryDate = ?2 WHERE mid = ?3")
	void updatelotteryTimes(String lotteryTimes, String lastLotteryDate, String mid);

	// 更新抽獎次數(沒日期)
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET lotteryTimes = ?1 WHERE mid = ?2")
	void updatelotteryTimesNoDate(String lotteryTimes, String mid);

	@Query(value = "FROM UserData where mid = ?1")
	Optional<UserData> findByMidOptional(String mid);

	// 單筆查詢會員編號(int)
	@Query(value = "from UserData where mid = ?1")
	public UserData findMidInt(int mid);

	// 查詢是否佔用客服
	@Query(value = "SELECT COUNT(*) FROM UserData WHERE messaging = 1")
	public int countMessaging();

	// 更新User客服狀態
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET messaging = ?1 WHERE mid = ?2")
	void updateMessagingStatus(String messaging, String mid);

	// 初始化圖片資料
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userPic = ?1")
	void initUserPic(byte[] userPic);

	// 檢查帳號重複
	@Query(value = "from UserData where userAcc = ?1")
	public UserData finduserAcc(String userAcc);

}
