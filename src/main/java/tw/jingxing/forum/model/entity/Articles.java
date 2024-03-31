package tw.jingxing.forum.model.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tw.jingxing.member.model.bean.UserData;

@Entity @Table
public class Articles {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId;
	
	
	private String articleTitle;
	
	
	private String articleContent;
	
	
	private Date articleDate;
	
	
	
	@ManyToOne
	@JoinColumn(name = "FK_articles_categories")
	@JsonIgnoreProperties(value = {"articles"})
	private Categories categories;
	
	@ManyToOne
	@JoinColumn(name = "FK_articles_article_types")
	@JsonIgnoreProperties(value = {"articles"})
	private ArticleTypes articleTypes;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_articles_user_data")
	@JsonIgnoreProperties(value = {"articles"})
	private UserData userData;

	
	@OneToMany(mappedBy = "likedArticle",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {"likeArticles"})
	private List<ArticleLikeRecords> likedUserDatas;
	
	
	@OneToMany(mappedBy = "collectedArticle",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ArticleCollectionRecords> collectedRecords;
	


	public int getLikedNum() {
		return this.likedUserDatas.size();
	}
	
	public int getCollectionNum() {
		return this.collectedRecords.size();
	}
	
	


	public Articles() {
	}


	public Articles(int articleId, String articleTitle, String articleContent, Date articleDate, Categories categories,
			ArticleTypes articleTypes, UserData userData) {
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleDate = articleDate;
		this.categories = categories;
		this.articleTypes = articleTypes;
		this.userData = userData;
	}


	public Articles(String articleTitle, String articleContent, Date articleDate, Categories categories,
			ArticleTypes articleTypes, UserData userData) {
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleDate = articleDate;
		this.categories = categories;
		this.articleTypes = articleTypes;
		this.userData = userData;
	}


	public List<ArticleCollectionRecords> getCollectedRecords() {
		return collectedRecords;
	}


	public void setCollectedRecords(List<ArticleCollectionRecords> collectedRecords) {
		this.collectedRecords = collectedRecords;
	}


	


	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}


	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}


	public String getArticleContent() {
		return articleContent;
	}


	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}


	public Date getArticleDate() {
		return articleDate;
	}


	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}


	public Categories getCategories() {
		return categories;
	}


	public void setCategories(Categories categories) {
		this.categories = categories;
	}


	public ArticleTypes getArticleTypes() {
		return articleTypes;
	}


	public void setArticleTypes(ArticleTypes articleTypes) {
		this.articleTypes = articleTypes;
	}


	public UserData getUserData() {
		return userData;
	}


	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public List<ArticleLikeRecords> getLikedUserDatas() {
		return likedUserDatas;
	}

	public void setLikedUserDatas(List<ArticleLikeRecords> likedUserDatas) {
		this.likedUserDatas = likedUserDatas;
	}
	
		
	
}
