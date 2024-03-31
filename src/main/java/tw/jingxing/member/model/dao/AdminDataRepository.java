package tw.jingxing.member.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.AdminData;
import tw.jingxing.member.model.bean.UserData;

public interface AdminDataRepository extends JpaRepository<AdminData, Integer> {

	// 登入檢查帳號
	AdminData findByAdminAccAndAdminPwd(String adminAcc, String adminPwd);

	// 模糊查詢會員資料
	@Query(value = "from UserData where userAcc like concat('%',?1,'%') or userName like concat('%',?2,'%')"
			+ "or address like concat('%',?3,'%')")
	public List<UserData> findMember(String userAcc, String userName, String address);

	// 單筆查詢會員編號(List)
	@Query(value = "from UserData where mid = ?1")
	public List<UserData> findMidList(String mid);

	// 單筆查詢會員編號(String)
	@Query(value = "from UserData where mid = ?1")
	public UserData findMidString(String mid);

	// 刪除單筆會員資料
	@Modifying
	@Transactional
	@Query(value = "Delete UserData where mid = ?1")
	public void deleteMidData(String mid);

	// Admin更新會員資料
	@Modifying
	@Transactional
	@Query("UPDATE UserData SET userAcc = ?1, userPwd = ?2, userName = ?3, "
			+ "email = ?4, gender = ?5, address = ?6, memberLv = ?7, "
			+ "userTel = ?8, birthday = ?9, lotteryTimes = ?10 WHERE mid = ?11")
	void AdminUpdateMemberData(String userAcc, String userPwd, String userName, String email, String gender,
			String address, String memberLv, String userTel, String birthday, String lotteryTimes, String mid);

	// Admin呼叫狀態
	@Modifying
	@Transactional
	@Query("UPDATE AdminData SET oncall = ?1, problemType = ?2")
	void AdminUpdateOncall(String oncall, String problemType);

	// 單筆查詢Admin(String)
	@Query(value = "from AdminData where adminID = ?1")
	public AdminData findAdminString(String adminID);

}
