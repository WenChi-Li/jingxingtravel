package tw.jingxing.member.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.jingxing.member.exception.UserStorageException;
import tw.jingxing.member.model.bean.AdminData;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.model.bean.UserStorage;
import tw.jingxing.member.service.AdminService;
import tw.jingxing.member.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService mService;

	@Autowired
	private AdminService aService;

	// 預設圖片
	public static byte[] convertImageToByteArray(String imagePath) throws IOException {
		Path path = Paths.get(imagePath);
		return Files.readAllBytes(path);
	}

//  更新背景圖片
	@PostMapping("/uploadBackGround.controller")
	public String uploadBackGround(@RequestParam("userBackGround") MultipartFile userBackGround,
			@RequestParam("mid") String mid) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		byte[] picBytes = userBackGround.getBytes();
		mService.updateBackGround(picBytes, mid);
		return responseJson.toString();
	}

	// 註冊資料
	@PostMapping("/userRegister.controller")
	public ResponseEntity<String> userRegister(@RequestBody UserData userData) throws IOException {

		
		String imagePath = "C:\\Java 3月\\workSpace\\jingxingtravel\\src\\main\\webapp\\image\\memberDefault.jpg";
		  byte[] imageBytes = convertImageToByteArray(imagePath);
		  
		  String BackImagePath = "C:\\Java 3月\\workSpace\\jingxingtravel\\src\\main\\webapp\\image\\BackDefault.png";
		  byte[] BackImageBytes = convertImageToByteArray(BackImagePath);
		  
		  String address = "未填寫";
		  String memLv = "0";
		  String lotteryTimes = "7";
		  

		  UserData newUser = new UserData();
		  newUser.setUserPic(imageBytes);
		  newUser.setUserName(userData.getUserName());
		  newUser.setEmail(userData.getEmail());
		  newUser.setUserAcc(userData.getUserAcc());
		  newUser.setBirthday(userData.getBirthday());
		  newUser.setUserPwd(userData.getUserPwd());
		  newUser.setGender(userData.getGender());
		  newUser.setaddress(address);
		  newUser.setUserTel(userData.getUserTel());
		  newUser.setValidation(userData.getValidation());
		  newUser.setMessaging(userData.getMessaging());
		  newUser.setMemberLv(memLv);
		  newUser.setLotteryTimes(lotteryTimes);
		  newUser.setUserBackGround(BackImageBytes);
		  

		  mService.insert(newUser);
		  return ResponseEntity.ok("Member registered successfully!");
	}

	// 更改成預設圖片
	@PostMapping("/updateDefaultPic.controller")
	public String updateDefaultPic(@RequestBody String body) throws IOException, JSONException {

		String imagePath = "C:\\Workspace\\development\\images\\memberDefault.jpg";
		byte[] imageBytes = convertImageToByteArray(imagePath);
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);

		String mid = requestBodyJson.getString("mid");

		mService.updateDefaultPic(imageBytes, mid);
		return responseJson.toString();
	}

	// 登入
	@PostMapping("/userlogin.controller")
	public String userlogin(@RequestBody String body) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);

		String userAcc = requestBodyJson.getString("userAcc");
		String userPwd = requestBodyJson.getString("userPwd");
		AdminData adminlogged = aService.login(userAcc, userPwd);
		UserData logged = mService.login(userAcc, userAcc, userPwd);

		if (userAcc == null || userAcc.length() == 0 || userPwd == null || userPwd.length() == 0) {
			responseJson.put("messages", "請輸入帳號密碼!");
			responseJson.put("loginStatus", false);
			return responseJson.toString();
		} else {
			if (adminlogged == null) {
				if (logged == null) {
					responseJson.put("messages", "帳號密碼錯誤!!");
					responseJson.put("loginStatus", false);
					return responseJson.toString();
				} else {
					int mid = logged.getMid();
					UserData userData = mService.findMidInt(mid);
					byte[] userPic = logged.getUserPic();
					if (userPic != null) {
						String base64 = Base64.getEncoder().encodeToString(userPic);
						ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
						String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
						String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
						logged.setPhotoBase64(photoBase64);
					}
					JSONObject user = new JSONObject().put("userAcc", logged.getUserAcc()).put("mid", logged.getMid())
							.put("validation", userData.getValidation()).put("email", userData.getEmail());
					responseJson.put("messages", "歡迎光臨!!");
					responseJson.put("loginStatus", user);
					return responseJson.toString();
				}

			} else {
				JSONObject admin = new JSONObject().put("adminID", adminlogged.getAdminID()).put("adminName",
						adminlogged.getAdminName());
				responseJson.put("loginStatus", "Admin");
				responseJson.put("adminData", admin);
				return responseJson.toString();
			}

		}

	}

//	 更新個人圖片
	@PostMapping("/uploadImage.controller")
	public String uploadImage(@RequestParam("userPic") MultipartFile userPic, @RequestParam("mid") String mid)
			throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		byte[] picBytes = userPic.getBytes();
		mService.uploadImage(picBytes, mid);
		responseJson.put("messages", "圖片上傳成功!!");
		return responseJson.toString();
	}

	// 更新個人資料
	@PostMapping("/userUpdate.controller")
	public String userUpdate(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String userName = requestBodyJson.getString("userName");
		String email = requestBodyJson.getString("email");
		String gender = requestBodyJson.getString("gender");
		String birthday = requestBodyJson.getString("birthday");
		String address = requestBodyJson.getString("address");
		String userTel = requestBodyJson.getString("userTel");
		String validation = requestBodyJson.getString("validation");
		System.out.println(validation);
		String mid = requestBodyJson.getString("mid");
		mService.uploadData(userName, email, gender, birthday, address, userTel, validation, mid);
		return responseJson.toString();
	}

	// 找尋密碼
	@PostMapping("/searchPwd.controller")
	public String searchPwd(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String mid = requestBodyJson.getString("mid");
		String pwd = mService.searchPwd(mid);
		responseJson.put("pwd", pwd);
		return responseJson.toString();
	}

	// 更新密碼
	@PostMapping("/updatePwd.controller")
	public String updatePwd(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String userPwd = requestBodyJson.getString("userPwd");
		String mid = requestBodyJson.getString("mid");
		mService.updatePwd(userPwd, mid);
		return responseJson.toString();
	}

	// 會員抽抽樂
	@GetMapping("/memberLottery.controller")
	public String memberLottery() throws JSONException {
		int random = 0;
		random = (int) (Math.random() * 5) + 1;
		JSONObject responseJson = new JSONObject();
		responseJson.put("lotteryResult", random);
		return responseJson.toString();

	}

	// 更新抽獎次數
	@PostMapping("/lotteryTimesUpdate.controller")
	public String lotteryTimesUpdate(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String lotteryTimes = requestBodyJson.getString("lotteryTimes");
		String lastLotteryDate = requestBodyJson.getString("lastLotteryDate");
		String mid = requestBodyJson.getString("mid");
		mService.updatelotteryTimes(lotteryTimes, lastLotteryDate, mid);
		return responseJson.toString();
	}

	// 更新抽獎次數(沒日期)
	@PostMapping("/updatelotteryTimesNoDate.controller")
	public String updatelotteryTimesNoDate(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String lotteryTimes = requestBodyJson.getString("lotteryTimes");
		String mid = requestBodyJson.getString("mid");
		mService.updatelotteryTimesNoDate(lotteryTimes, mid);
		return responseJson.toString();
	}

	// 取得伺服器時間
	@GetMapping("/getServerTime.controller")
	public String getServerTime() throws JSONException {

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String dateString = String.format("%04d-%02d-%02d", year, month, day);

		JSONObject responseJson = new JSONObject();
		responseJson.put("dateString", dateString);

		return responseJson.toString();
	}

	// 新增會員獎品
	@PostMapping("/insertlotteryPrize.controller")
	public ResponseEntity<String> InsertlotteryPrize(@RequestBody UserStorage userStorage) {
		mService.insertlotteryPrize(userStorage);
		return ResponseEntity.ok("Member registered successfully!");
	}

	// 新增獎品到使用者倉庫
	@PostMapping("/InsertPrizetoUserStorage.controller")
	public String InsertPrizetoUserStorage(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson;
		try {
			requestBodyJson = new JSONObject(body);
			String newPrizeID = requestBodyJson.getString("newPrizeID");
			String newMid = requestBodyJson.getString("newMid");
			aService.InsertPrizetoUserStorage(newPrizeID, newMid);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UserStorageException e) {
			e.printStackTrace();
		}

		return responseJson.toString();
	}

	// 初始化圖片資料
	@PostMapping("/initUserPic.controller")
	public String InitUserPic(@RequestParam("userPic") MultipartFile userPic) throws IOException {
		// 根据用户ID从数据库中检索用户数据
		byte[] picBytes = userPic.getBytes();
		mService.InitUserPic(picBytes);
		return "成功!";
	}

	// 檢查帳號重複
	@PostMapping("/finduserAcc.controller")
	public String finduserAcc(@RequestBody String body) throws IOException, JSONException {
		JSONObject requestBodyJson = new JSONObject(body);
		String userAcc = requestBodyJson.getString("userAcc");
		UserData yesNo = mService.finduserAcc(userAcc);
		if (yesNo == null) {
			return "no";
		} else {
			return "yes";
		}
	}

	// 檢查帳號重複 + 回傳mid
	@PostMapping("/finduserAccAndMid.controller")
	public String finduserAccAndMid(@RequestBody String body) throws IOException, JSONException {
		JSONObject requestBodyJson = new JSONObject(body);
		String userAcc = requestBodyJson.getString("userAcc");
		UserData yesNo = mService.finduserAcc(userAcc);
		if (yesNo == null) {
			return "no";
		} else {
			JSONObject responseJson = new JSONObject();
			responseJson.put("mid", yesNo.getMid());
			responseJson.put("status", "yes");
			return responseJson.toString();
		}
	}

}
