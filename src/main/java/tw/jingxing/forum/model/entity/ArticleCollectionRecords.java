package tw.jingxing.forum.model.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tw.jingxing.member.model.bean.UserData;

@Entity
@Table
public class ArticleCollectionRecords {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleCollectionRecordId;

	private LocalDateTime articleCollectionRecordDate;

	@ManyToOne
	@JoinColumn(name = "FK_article_collection_records_articles")
	@JsonIgnoreProperties(value = { "userDataCollectedRecords" })
	private Articles collectedArticle;

	@ManyToOne
	@JoinColumn(name = "FK_article_collection_records_user_data")
	@JsonIgnoreProperties(value = { "myArticlesCollectionRecords" })
	private UserData collectUserData;

	public ArticleCollectionRecords() {
	}

	public ArticleCollectionRecords(LocalDateTime articleCollectionRecordDate, Articles collectedArticle,
			UserData collectUserData) {
		this.articleCollectionRecordDate = articleCollectionRecordDate;
		this.collectedArticle = collectedArticle;
		this.collectUserData = collectUserData;
	}

	public Articles getCollectedArticle() {
		return collectedArticle;
	}

	public void setCollectedArticle(Articles collectedArticle) {
		this.collectedArticle = collectedArticle;
	}


	public Integer getArticleCollectionRecordId() {
		return articleCollectionRecordId;
	}

	public void setArticleCollectionRecordId(Integer articleCollectionRecordId) {
		this.articleCollectionRecordId = articleCollectionRecordId;
	}

	public LocalDateTime getArticleCollectionRecordDate() {
		return articleCollectionRecordDate;
	}

	public void setArticleCollectionRecordDate(LocalDateTime articleCollectionRecordDate) {
		this.articleCollectionRecordDate = articleCollectionRecordDate;
	}

	public UserData getCollectUserData() {
		return collectUserData;
	}

	public void setCollectUserData(UserData collectUserData) {
		this.collectUserData = collectUserData;
	}

}
