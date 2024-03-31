package tw.jingxing.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.model.bean.UserStorage;
import tw.jingxing.member.model.dao.UserDataRepository;
import tw.jingxing.member.model.dao.UserStorageRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	private UserDataRepository userRespo;

	@Autowired
	private UserStorageRepository userStorageRespo;

	// 註冊
	public UserData insert(UserData user) {
		return userRespo.save(user);
	}

	// 登入
	public UserData login(String userAcc, String email, String userPwd) {
		return userRespo.accountCheck(userAcc, email, userPwd);
	}

	// 上傳個人圖片
	public void uploadImage(byte[] userPic, String mid) {
		userRespo.updateUserPic(userPic, mid);
	}

	// 上傳個人圖片
	public void updateDefaultPic(byte[] userPic, String mid) {
		userRespo.updateDefaultPic(userPic, mid);
	}

	// 更新個人檔案
	public void uploadData(String userName, String email, String gender, String birthday, String address,
			String userTel, String validation, String mid) {
		userRespo.updateData(userName, email, gender, birthday, address, userTel, validation, mid);
	}

	// 初始化圖片資料
	public void InitUserPic(byte[] userPic) {
		userRespo.initUserPic(userPic);
	}

	// 找尋密碼
	public String searchPwd(String mid) {
		return userRespo.searchPwd(mid);
	}

	// 檢查帳號重複
	public UserData finduserAcc(String userAcc) {
		return userRespo.finduserAcc(userAcc);
	}

	// 單筆ID查詢會員資料(int)
	public UserData findMidInt(int mid) {
		return userRespo.findMidInt(mid);
	}

	// 查詢是否佔用客服
	public int countMessaging() {
		return userRespo.countMessaging();
	}

	// 更新密碼
	public void updatePwd(String userPwd, String mid) {
		userRespo.updatePwd(userPwd, mid);
	}

	// 更新抽獎次數
	public void updatelotteryTimes(String lotteryTimes, String lastLotteryDate, String mid) {
		userRespo.updatelotteryTimes(lotteryTimes, lastLotteryDate, mid);
	}

	// 更新抽獎次數(沒日期)
	public void updatelotteryTimesNoDate(String lotteryTimes, String mid) {
		userRespo.updatelotteryTimesNoDate(lotteryTimes, mid);
	}

	// 更新session
	public Optional<UserData> getUserById(int userId) {
		return userRespo.findById(userId);
	}

	// 新增會員獎品
	public void insertlotteryPrize(UserStorage userStorage) {
		userStorageRespo.save(userStorage);
	}

	// 更新User客服狀態
	public void updateMessagingStatus(String messaging, String mid) {
		userRespo.updateMessagingStatus(messaging, mid);
	}

	// 上傳背景圖片
	 public void updateBackGround(byte[] userBackGround, String mid) {
	  userRespo.updateBackGround(userBackGround, mid);
	 }
	
}
