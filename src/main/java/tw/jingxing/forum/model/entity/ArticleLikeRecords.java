package tw.jingxing.forum.model.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tw.jingxing.member.model.bean.UserData;

@Entity
@Table
public class ArticleLikeRecords {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleLikeRecordId;

	private LocalDateTime articleLikeRecordDate;

	@ManyToOne
	@JoinColumn(name = "FK_article_like_records_articles")
	@JsonIgnoreProperties(value = { "likedUserDatas" })
	private Articles likedArticle;

	@ManyToOne
	@JoinColumn(name = "FK_article_like_records_user_data")
	@JsonIgnoreProperties(value = { "likeArticles" })
	private UserData likedUserData;

	
	public ArticleLikeRecords() {
	}

	public ArticleLikeRecords(LocalDateTime articleLikeRecordDate, Articles likedArticle, UserData likedUserData) {
		this.articleLikeRecordDate = articleLikeRecordDate;
		this.likedArticle = likedArticle;
		this.likedUserData = likedUserData;
	}


	public Integer getArticleLikeRecordId() {
		return articleLikeRecordId;
	}

	public void setArticleLikeRecordId(Integer articleLikeRecordId) {
		this.articleLikeRecordId = articleLikeRecordId;
	}

	public LocalDateTime getArticleLikeRecordDate() {
		return articleLikeRecordDate;
	}

	public void setArticleLikeRecordDate(LocalDateTime articleLikeRecordDate) {
		this.articleLikeRecordDate = articleLikeRecordDate;
	}

	public Articles getLikedArticle() {
		return likedArticle;
	}

	public void setLikedArticle(Articles likedArticle) {
		this.likedArticle = likedArticle;
	}

	public UserData getLikedUserData() {
		return likedUserData;
	}

	public void setLikedUserData(UserData likedUserData) {
		this.likedUserData = likedUserData;
	}

	
	
	
}
