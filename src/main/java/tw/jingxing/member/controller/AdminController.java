package tw.jingxing.member.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import tw.jingxing.member.model.bean.AdminData;
import tw.jingxing.member.model.bean.Prize;
import tw.jingxing.member.model.bean.PrizePool;
import tw.jingxing.member.model.bean.ServiceRecord;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.model.bean.UserStorage;
import tw.jingxing.member.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private JavaMailSender javaMailSender;

	// 查全部資料
	@GetMapping("/findAll.controller")
	public String findAll() throws JSONException {
		List<UserData> showAll = aService.findAll();
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		for (UserData result : showAll) {
			JSONObject user = new JSONObject().put("userAcc", result.getUserAcc()).put("mid", result.getMid())
					.put("userPwd", result.getUserPwd()).put("userName", result.getUserName())
					.put("email", result.getEmail()).put("gender", result.getGender())
					.put("address", result.getaddress()).put("memberLv", result.getMemberLv())
					.put("userTel", result.getUserTel()).put("birthday", result.getBirthday())
					.put("userPic", result.getUserPic()).put("photoBase64", result.getPhotoBase64())
					.put("lotteryTimes", result.getLotteryTimes());

			array = array.put(user);
		}
		responseJson.put("showAll", array);
		return responseJson.toString();
	}

	// 單筆查詢會員編號(List)
	@PostMapping("/findMidList.controller")
	@Transactional
	public String findMidList(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		JSONArray array = new JSONArray();
		String mid = requestBodyJson.getString("mid");
		List<UserData> showQuery = aService.findMidList(mid);

		for (UserData result : showQuery) {
			JSONObject user = new JSONObject().put("userAcc", result.getUserAcc()).put("mid", result.getMid())
					.put("userPwd", result.getUserPwd()).put("userName", result.getUserName())
					.put("email", result.getEmail()).put("gender", result.getGender())
					.put("address", result.getaddress()).put("memberLv", result.getMemberLv())
					.put("userTel", result.getUserTel()).put("birthday", result.getBirthday())
					.put("userPic", result.getUserPic()).put("photoBase64", result.getPhotoBase64())
					.put("lotteryTimes", result.getLotteryTimes()).put("lastLotteryDate", result.getLastLotteryDate());

			array = array.put(user);
		}

		responseJson.put("showAll", array);
		return responseJson.toString();
	}

	// 單筆查詢會員編號(String)
		@PostMapping("/findMidString.controller")
		@Transactional
		public String findMidString(@RequestBody String body) throws JSONException, IOException {
			JSONObject responseJson = new JSONObject();
			JSONObject requestBodyJson = new JSONObject(body);
			String mid = requestBodyJson.getString("mid");
			UserData showQuery = aService.findMidString(mid);
			JSONArray prizeArray = new JSONArray();

			List<UserStorage> userStorages = showQuery.getUserStorage();
			for (UserStorage userStorage : userStorages) {
				JSONObject prizeJson = new JSONObject();
				Prize prize = userStorage.getPrize();
				byte[] prizePic = prize.getPrizePic();

				if (prizePic != null) {
					String base64 = Base64.getEncoder().encodeToString(prizePic);
					ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(prizePic);
					String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
					String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
					prize.setPrizePicBase64(photoBase64);
				}
				prizeJson.put("prizeID", prize.getPrizeID()).put("prizeName", prize.getPrizeName())
						.put("discount", prize.getDiscount()).put("prizePicBase64", prize.getPrizePicBase64())
						.put("usid", userStorage.getUsid());

				prizeArray.put(prizeJson);
			}

			byte[] userPic = showQuery.getUserPic();
			if (userPic != null) {
				String base64 = Base64.getEncoder().encodeToString(userPic);
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
				String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
				String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
				showQuery.setPhotoBase64(photoBase64);
			}
			byte[] userBackGround = showQuery.getUserBackGround();
			if(userBackGround != null) {
				String base64Back = Base64.getEncoder().encodeToString(userBackGround);
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userBackGround);
				String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
				String photoBase64Back = "data:%s;base64,".formatted(mimeType) + base64Back;
				showQuery.setUserBackGround64(photoBase64Back);
			}

			JSONObject user = new JSONObject().put("userAcc", showQuery.getUserAcc()).put("mid", showQuery.getMid())
					.put("userPwd", showQuery.getUserPwd()).put("userName", showQuery.getUserName())
					.put("email", showQuery.getEmail()).put("gender", showQuery.getGender())
					.put("address", showQuery.getaddress()).put("memberLv", showQuery.getMemberLv())
					.put("userTel", showQuery.getUserTel()).put("birthday", showQuery.getBirthday())
					.put("userPic", showQuery.getUserPic()).put("photoBase64", showQuery.getPhotoBase64())
					.put("lotteryTimes", showQuery.getLotteryTimes()).put("lastLotteryDate", showQuery.getLastLotteryDate())
					.put("prizes", prizeArray).put("validation", showQuery.getValidation()).put("userBackGround64", showQuery.getUserBackGround64());
			;

			responseJson.put("showAll", user);
			return responseJson.toString();
		}


	// 模糊查詢
	@PostMapping("/fuzzySearch.controller")
	public String fuzzySearch(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		JSONArray array = new JSONArray();
		String fuzzySearch = requestBodyJson.getString("fuzzySearch");
		List<UserData> showQuery = aService.findMember(fuzzySearch, fuzzySearch, fuzzySearch);
		for (UserData result : showQuery) {
			JSONObject user = new JSONObject().put("userAcc", result.getUserAcc()).put("mid", result.getMid())
					.put("userPwd", result.getUserPwd()).put("userName", result.getUserName())
					.put("email", result.getEmail()).put("gender", result.getGender())
					.put("address", result.getaddress()).put("memberLv", result.getMemberLv())
					.put("userTel", result.getUserTel()).put("birthday", result.getBirthday())
					.put("userPic", result.getUserPic()).put("photoBase64", result.getPhotoBase64())
					.put("lotteryTimes", result.getLotteryTimes()).put("lastLotteryDate", result.getLastLotteryDate());

			array = array.put(user);
		}
		responseJson.put("showAll", array);
		return responseJson.toString();
	}

	// Admin更新個人資料
	@PostMapping("/adminMemberUpdate.controller")
	public String adminMemberUpdate(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String userAcc = requestBodyJson.getString("userAcc");
		String userPwd = requestBodyJson.getString("userPwd");
		String userName = requestBodyJson.getString("userName");
		String email = requestBodyJson.getString("email");
		String gender = requestBodyJson.getString("gender");
		String address = requestBodyJson.getString("address");
		String memberLv = requestBodyJson.getString("memberLv");
		String userTel = requestBodyJson.getString("userTel");
		String birthday = requestBodyJson.getString("birthday");
		String lotteryTimes = requestBodyJson.getString("lotteryTimes");
		String mid = requestBodyJson.getString("mid");
		aService.AdminUpdateMemberData(userAcc, userPwd, userName, email, gender, address, memberLv, userTel, birthday, lotteryTimes,
				mid);
		return responseJson.toString();
	}

	// 刪除單筆會員資料
	@PostMapping("/adminMemberDelete.controller")
	public String adminMemberDelete(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String mid = requestBodyJson.getString("mid");
		aService.deleteMember(mid);
		return responseJson.toString();
	}
	
	//刪除用戶獎品
	@PostMapping("/deleteByUsid.controller")
	public String deleteByUsid(@RequestBody String body) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String usid = requestBodyJson.getString("usid");
		aService.deleteByUsid(usid);
		return responseJson.toString();
	}

	// 列出獎品
	@PostMapping("/listPrize.controller")
	public String listPrize() throws IOException, JSONException {
		List<Prize> showAll = aService.listPrize();
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();

		for (Prize result : showAll) {
			byte[] userPic = result.getPrizePic();
			if (userPic != null) {
				String base64 = Base64.getEncoder().encodeToString(userPic);
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
				String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
				String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
				result.setPrizePicBase64(photoBase64);
			}
			JSONObject user = new JSONObject().put("prizeID", result.getPrizeID())
					.put("prizeName", result.getPrizeName()).put("discount", result.getDiscount())
					.put("prizePicBase64", result.getPrizePicBase64());

			array = array.put(user);
		}
		responseJson.put("prizepool", array);
		return responseJson.toString();

	}

	// 單筆查詢獎品內容(String)
	@PostMapping("/findPrizeString.controller")
	public String findPrizeString(@RequestBody String body) throws JSONException, IOException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String prizeID = requestBodyJson.getString("prizeID");
		Prize showQuery = aService.findPrizeString(prizeID);
		byte[] userPic = showQuery.getPrizePic();
		if (userPic != null) {
			String base64 = Base64.getEncoder().encodeToString(userPic);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
			String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
			String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
			showQuery.setPrizePicBase64(photoBase64);
		}
		JSONObject prizepool = new JSONObject().put("prizeID", showQuery.getPrizeID())
				.put("prizeName", showQuery.getPrizeName()).put("discount", showQuery.getDiscount())
				.put("prizePicBase64", showQuery.getPrizePicBase64()).put("prizePic", showQuery.getPrizePic());

		responseJson.put("prizepool", prizepool);
		return responseJson.toString();
	}

//	 更新獎品
	@PostMapping("/uploadPrizePool.controller")
	public String uploadPrizePool(@RequestParam(value = "prizePic", required = false) MultipartFile prizePic,
			@RequestParam("prizeID") String prizeID, @RequestParam("prizeName") String prizeName,
			@RequestParam("discount") String discount) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		if (prizePic == null) {
			aService.updatePrizePoolNoPic(prizeName, discount, prizeID);
		} else {
			byte[] picBytes = prizePic.getBytes();
			aService.updatePrizePool(prizeName, discount, picBytes, prizeID);
			responseJson.put("messages", "更新成功!!");
		}

		return responseJson.toString();
	}

	// 列出獎池
	@PostMapping("/listPrizePool.controller")
	@Transactional
	public String listPrizePool() throws IOException, JSONException {
		List<PrizePool> showAll = aService.listPrizePool();
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();

		for (PrizePool result : showAll) {
			Prize prize = result.getPrize();
			if (prize != null) {

				byte[] userPic = result.getPrize().getPrizePic();
				if (userPic != null) {
					String base64 = Base64.getEncoder().encodeToString(userPic);
					ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
					String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
					String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
					prize.setPrizePicBase64(photoBase64);
				}
				JSONObject user = new JSONObject().put("prizepoolID", result.getPrizepoolID())
						.put("prizeID", result.getPrize().getPrizeID())
						.put("prizeName", result.getPrize().getPrizeName())
						.put("discount", result.getPrize().getDiscount())
						.put("prizePicBase64", result.getPrize().getPrizePicBase64());

				array = array.put(user);
			}
		}
		responseJson.put("prizepool", array);
		return responseJson.toString();

	}

	// 單筆查詢獎池(String)
	@PostMapping("/findPrizePoolString.controller")
	@Transactional
	public String findPrizePoolString(@RequestBody String body) throws JSONException, IOException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String prizepoolID = requestBodyJson.getString("prizepoolID");
		PrizePool showQuery = aService.findPrizePoolString(prizepoolID);

		byte[] userPic = showQuery.getPrize().getPrizePic();
		if (userPic != null) {
			String base64 = Base64.getEncoder().encodeToString(userPic);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
			String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
			String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
			showQuery.getPrize().setPrizePicBase64(photoBase64);
		}

		JSONObject prizepool = new JSONObject().put("prizepoolID", showQuery.getPrizepoolID())
				.put("prizeID", showQuery.getPrize().getPrizeID()).put("prizeName", showQuery.getPrize().getPrizeName())
				.put("discount", showQuery.getPrize().getDiscount())
				.put("prizePicBase64", showQuery.getPrize().getPrizePicBase64());

		responseJson.put("prizepool", prizepool);

		return responseJson.toString();

	}

	// 新增獎品
	@PostMapping("/newPrize.controller")
	@Transactional
	public String newPrize(@RequestParam(value = "prizePic", required = false) MultipartFile prizePic,
			@RequestParam("prizeName") String prizeName, @RequestParam("discount") String discount)
			throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		byte[] picBytes = prizePic.getBytes();
		aService.newPrize(prizeName, discount, picBytes);
		responseJson.put("messages", "更新成功!!");

		return responseJson.toString();
	}

	// 刪除獎品
	@PostMapping("/deletePrize.controller")
	@Transactional
	public String deletePrize(@RequestBody String body) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String prizeID = requestBodyJson.getString("prizeID");
		aService.DeletePrize(prizeID);
		return responseJson.toString();
	}

	// 更換獎池
	@PostMapping("/changePrizePool.controller")
	@Transactional
	public String changePrizePool(@RequestBody String body) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String newPrizeID = requestBodyJson.getString("newPrizeID");
		String prizepoolID = requestBodyJson.getString("prizepoolID");
		aService.changePrizePool(newPrizeID, prizepoolID);
		return responseJson.toString();
	}

	// 寄送驗證碼
	@PostMapping("/sendVerificationEmail")
	public String sendVerificationEmail(@RequestBody String body)
			throws jakarta.mail.MessagingException, JSONException {
		JSONObject requestBodyJson = new JSONObject(body);
		JSONObject responseJson = new JSONObject();
		String userEmail = requestBodyJson.getString("userEmail");
		String verificationCode = generateRandomCode();

		try {
			sendEmail(userEmail, verificationCode);

		} catch (MessagingException e) {
			e.printStackTrace();
			return "Email發送失敗!!";
		}
		responseJson.put("verificationCode", verificationCode);
		return responseJson.toString();
	}

	// 寄送Email(方法)
	private void sendEmail(String userEmail, String verificationCode)
			throws MessagingException, jakarta.mail.MessagingException {
		jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("gne8522@gmail.com");
		helper.setTo(userEmail);
		helper.setSubject("境行旅遊");
		helper.setText("你的驗證碼是：" + verificationCode);
		javaMailSender.send(message);
	}

	// 產生數字驗證碼(方法)
	private String generateRandomCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}

	// 寄送密碼至使用者信箱
	@PostMapping("/sendPasswordEmail")
	public String sendPasswordEmail(@RequestBody String body) throws jakarta.mail.MessagingException, JSONException {
		JSONObject requestBodyJson = new JSONObject(body);
		JSONObject responseJson = new JSONObject();
		String userEmail = requestBodyJson.getString("userEmail");
		String userPwd = requestBodyJson.getString("userPwd");

		try {
			sendPassword(userEmail, userPwd);

		} catch (MessagingException e) {
			e.printStackTrace();
			return "Email發送失敗!!";
		}
		responseJson.put("userPwd", userPwd);
		return responseJson.toString();
	}

	// 寄送密碼(方法)
	private void sendPassword(String userEmail, String userPwd)
			throws MessagingException, jakarta.mail.MessagingException {
		jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("gne8522@gmail.com");
		helper.setTo(userEmail);
		helper.setSubject("境行旅遊");
		helper.setText("你的密碼是：" + userPwd);
		javaMailSender.send(message);
	}

	// 新增客服紀錄
	@PostMapping("/insertServiceRecord.controller")
	public ResponseEntity<String> InsertServiceRecord(@RequestBody ServiceRecord serviceRecord) throws IOException {
		aService.insertServiceRecord(serviceRecord);
		return ResponseEntity.ok("serviceRecord registered successfully!");
	}

	// 列出客服紀錄
	@GetMapping("/listServiceRecord.controller")
	public String listServiceRecord() throws JSONException {
		List<ServiceRecord> showAll = aService.listServiceRecord();
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		for (ServiceRecord result : showAll) {
			JSONObject record = new JSONObject().put("rid", result.getRid())
					.put("serviceTarget", result.getServiceTarget()).put("recordDate", result.getRecordDate())
					.put("recordText", result.getRecordText()).put("recordType", result.getRecordType());

			array = array.put(record);
		}
		responseJson.put("showAll", array);
		return responseJson.toString();
	}
	
	
	
	// Admin呼叫狀態
	@PostMapping("/AdminUpdateOncall.controller")
	@Transactional
	public String AdminUpdateOncall(@RequestBody String body) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String oncall = requestBodyJson.getString("oncall");
		String problemType = requestBodyJson.getString("problemType");
		aService.AdminUpdateOncall(oncall, problemType);
		return responseJson.toString();
	}
	
	// 查詢Admin
	@PostMapping("/findAdminString.controller")
	@Transactional
	public String findAdminString(@RequestBody String body) throws JSONException, IOException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String adminID = requestBodyJson.getString("adminID");
		AdminData showQuery = aService.findAdminString(adminID);
		JSONObject AdminShowStatus = new JSONObject().put("oncall", showQuery.getOncall())
				.put("problemType", showQuery.getProblemType());

		responseJson.put("AdminShowStatus", AdminShowStatus);
		return responseJson.toString();
	}
	
	// 查詢Admin
	@PostMapping("/findRid.controller")
	@Transactional
	public String findRid(@RequestBody String body) throws JSONException, IOException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String rid = requestBodyJson.getString("rid");
		ServiceRecord showQuery = aService.findRid(rid);
		JSONObject allRecord = new JSONObject().put("rid", showQuery.getRid())
				.put("recordType", showQuery.getRecordType()).put("serviceTarget", showQuery.getServiceTarget())
						.put("recordDate", showQuery.getRecordDate()).put("recordText", showQuery.getRecordText());

		responseJson.put("allRecord", allRecord);
		return responseJson.toString();
	}


	// 刪除對話紀錄
	@PostMapping("/deleteRid.controller")
	@Transactional
	public String deleteRid(@RequestBody String body) throws IOException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject requestBodyJson = new JSONObject(body);
		String rid = requestBodyJson.getString("rid");
		aService.deleteRid(rid);
		return responseJson.toString();
	}
	

}
