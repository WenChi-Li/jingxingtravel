package tw.jingxing.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import tw.jingxing.member.exception.UserStorageException;
import tw.jingxing.member.model.bean.AdminData;
import tw.jingxing.member.model.bean.Prize;
import tw.jingxing.member.model.bean.PrizePool;
import tw.jingxing.member.model.bean.ServiceRecord;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.model.bean.UserStorage;
import tw.jingxing.member.model.dao.AdminDataRepository;
import tw.jingxing.member.model.dao.PrizePoolRepository;
import tw.jingxing.member.model.dao.PrizeRepository;
import tw.jingxing.member.model.dao.ServiceRecordRepository;
import tw.jingxing.member.model.dao.UserDataRepository;
import tw.jingxing.member.model.dao.UserStorageRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminDataRepository adminRespo;

	@Autowired
	private UserDataRepository userRespo;

	@Autowired
	private PrizeRepository prizeRespo;
	
	@Autowired
	private UserStorageRepository userStorageRespo;


	@Autowired
	private PrizePoolRepository prizePoolRespo;
	
	@Autowired
	private ServiceRecordRepository serviceRecordRespo;
	
    @Autowired
    private EntityManager entityManager;

	// 登入
	public AdminData login(String adminAcc, String adminPwd) {
		return adminRespo.findByAdminAccAndAdminPwd(adminAcc, adminPwd);
	}

	// 列出全部
	public List<UserData> findAll() {
		return userRespo.findAll();
	}

	// 模糊查詢資料
	public List<UserData> findMember(String userAcc, String userName, String address) {
		return adminRespo.findMember(userAcc, userName, address);
	}

	// 單筆ID查詢會員資料(List)
	public List<UserData> findMidList(String mid) {
		return adminRespo.findMidList(mid);
	}

	// 單筆ID查詢會員資料(String)
	public UserData findMidString(String mid) {
		return adminRespo.findMidString(mid);
	}
	
	// 單筆查詢Admin(String)
	public AdminData findAdminString(String adminID) {
		return adminRespo.findAdminString(adminID);
	}
	
	// 單筆查詢對話紀錄(String)
	public ServiceRecord findRid(String rid) {
		return serviceRecordRespo.findRid(rid);
	}

	// 刪除帳號
	public void deleteMember(String mid) {
		adminRespo.deleteMidData(mid);
	}
	
	// 刪除對話紀錄
	public void deleteRid(String rid) {
		serviceRecordRespo.deleteRid(rid);
	}
	
	//刪除用戶獎品
	public void deleteByUsid(String usid) {
		userStorageRespo.deleteByUsid(usid);
	}

	// Admin更新會員資料
	public void AdminUpdateMemberData(String userAcc, String userPwd, String userName, String email, String gender,
			String address, String memberLv, String userTel, String birthday, String lotteryTimes, String mid) {
		adminRespo.AdminUpdateMemberData(userAcc, userPwd, userName, email, gender, address, memberLv, userTel,
				birthday,lotteryTimes, mid);
	}

	// 列出獎品
	public List<Prize> listPrize() {
		return prizeRespo.findAll();
	}
	
	// 列出客服紀錄
	public List<ServiceRecord> listServiceRecord() {
		return serviceRecordRespo.findAll();
	}

	// 單筆查詢獎品(String)
	public Prize findPrizeString(String prizeID) {
		return prizeRespo.findPrizeString(prizeID);
	}
	
	
	// 單筆查詢會員編號(String)(Optional)
	public Optional<Prize> findPrizeStringOptional(String prizeID) {
		return prizeRespo.findPrizeStringOptional(prizeID);
	}

//	 更新獎品資訊
	public void updatePrizePool(String prizeName, String discount, byte[] picBytes, String prizeID) {
		prizeRespo.updatePrizePool(prizeName, discount, picBytes, prizeID);
	}

//	 更新獎品資訊無圖片
	public void updatePrizePoolNoPic(String prizeName, String discount, String prizeID) {
		prizeRespo.updatePrizePoolNoPic(prizeName, discount, prizeID);
	}

	// 列出獎池
	public List<PrizePool> listPrizePool() {
		return prizePoolRespo.findAll();
	}

	// 單筆查詢獎池(String)
	public PrizePool findPrizePoolString(String prizepoolID) {
		return prizePoolRespo.findPrizePoolString(prizepoolID);
	}

	// 新增獎品
	public void newPrize(String prizeName, String discount, byte[] prizePic) {
		prizeRespo.newPrize(prizeName, discount, prizePic);
	}

	// 刪除獎品
	public void DeletePrize(String prizeID) {
		prizeRespo.deleteByPrizeID(prizeID);
	}

	// 更換獎池
	public void changePrizePool(String newPrizeID, String prizepoolID) {
		prizePoolRespo.changePrizePool(newPrizeID, prizepoolID);
	}
	
	//新增獎品到使用者倉庫
	@Transactional
    public void InsertPrizetoUserStorage(String newPrizeID, String newMid) {
        UserStorage userStorage = new UserStorage();
        
        Optional<Prize> prizeOptional = prizeRespo.findPrizeStringOptional(newPrizeID);
        Optional<UserData> userOptional = userRespo.findByMidOptional(newMid);
        if(prizeOptional.isPresent() && userOptional.isPresent()) {
           Prize prize = prizeOptional.get();
            UserData user = userOptional.get();
            userStorage.setPrize(prize);
            userStorage.setUserData(user);
            entityManager.persist(userStorage);
        }else {
        	throw new UserStorageException("查無此獎勵或會員!!");
        }

    }
	
	// 新增通訊紀錄
	public ServiceRecord insertServiceRecord(ServiceRecord serviceRecord) {
		return serviceRecordRespo.save(serviceRecord);
	}
	
	// Admin呼叫狀態
	public void AdminUpdateOncall(String oncall, String problemType) {
		adminRespo.AdminUpdateOncall(oncall, problemType);
	}


}
