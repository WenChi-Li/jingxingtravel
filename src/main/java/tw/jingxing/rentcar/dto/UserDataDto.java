package tw.jingxing.rentcar.dto;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.member.model.bean.UserStorage;
import tw.jingxing.rentcar.model.RentalInfo;

public class UserDataDto {
	
		private int mid;

		private String userAcc;

		private String userPwd;

		private String userName;

		private String email;

		private String gender;

		private String address;

		private String memberLv;

		private String lotteryTimes;

		private String lastLotteryDate;

		private String userTel;

		private String birthday;

		private byte[] userPic;

		private String photoBase64;

		private List<UserStorage> userStorage;
		
		private List<Articles> articles;
		
		private List<Articles> likeArticles;

		private List<RentalInfo> rentalinfos;
		
		public List<Articles> getArticles() {
			return articles;
		}

		public void setArticles(List<Articles> articles) {
			this.articles = articles;
		}

		public List<Articles> getLikeArticles() {
			return likeArticles;
		}

		public void setLikeArticles(List<Articles> likeArticles) {
			this.likeArticles = likeArticles;
		}

		public String getLastLotteryDate() {
			return lastLotteryDate;
		}

		public void setLastLotteryDate(String lastLotteryDate) {
			this.lastLotteryDate = lastLotteryDate;
		}

		public String getLotteryTimes() {
			return lotteryTimes;
		}

		public void setLotteryTimes(String lotteryTimes) {
			this.lotteryTimes = lotteryTimes;
		}

		public String getPhotoBase64() {
			return photoBase64;
		}

		public void setPhotoBase64(String photoBase64) {
			this.photoBase64 = photoBase64;
		}

		public int getMid() {
			return mid;
		}

		public String getUserAcc() {
			return userAcc;
		}

		public String getUserPwd() {
			return userPwd;
		}

		public String getUserName() {
			return userName;
		}

		public String getEmail() {
			return email;
		}

		public String getGender() {
			return gender;
		}

		public String getaddress() {
			return address;
		}

		public String getMemberLv() {
			return memberLv;
		}

		public String getUserTel() {
			return userTel;
		}

		public String getBirthday() {
			return birthday;
		}

		public byte[] getUserPic() {
			return userPic;
		}

		public void setMid(int mid) {
			this.mid = mid;
		}

		public void setUserAcc(String userAcc) {
			this.userAcc = userAcc;
		}

		public void setUserPwd(String userPwd) {
			this.userPwd = userPwd;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public void setaddress(String address) {
			this.address = address;
		}

		public void setMemberLv(String memberLv) {
			this.memberLv = memberLv;
		}

		public void setUserTel(String userTel) {
			this.userTel = userTel;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public void setUserPic(byte[] userPic) {
			try {
				String base64 = Base64.getEncoder().encodeToString(userPic);

				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userPic);
				String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
				String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64;
				this.photoBase64 = photoBase64;
			} catch (Exception e) {

			}

			this.userPic = userPic;
		}

		@Override
		public String toString() {
			return "UserData [mid=" + mid + ", userAcc=" + userAcc + ", userPwd=" + userPwd + ", userName=" + userName
					+ ", email=" + email + ", gender=" + gender + ", address=" + address + ", memberLv=" + memberLv
					+ ", lotteryTimes=" + lotteryTimes + ", lastLotteryDate=" + lastLotteryDate + ", userTel=" + userTel
					+ ", birthday=" + birthday + ", userPic=" + Arrays.toString(userPic) + ", photoBase64=" + photoBase64
					+ ", userStorage=" + userStorage + ", articles=" + articles + ", likeArticles=" + likeArticles + "]";
		}

	}


