package tw.jingxing.forum.model.dto;

import java.util.Date;

public class ArticlesDTO {

	
	private int articleId;
	
	
	private String articleTitle;
	
	
	private String articleContent;
	
	
	private Date articleDate;
	
	private int categoryId;
	
	private int articleTypeId;
	
	private int mId;

	
	
	@Override
	public String toString() {
		return "ArticlesDTO [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articleDate=" + articleDate + ", categoryId=" + categoryId + ", articleTypeId="
				+ articleTypeId + ", mId=" + mId + "]";
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(int articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}
	
	
}
