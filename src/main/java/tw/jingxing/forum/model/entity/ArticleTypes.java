package tw.jingxing.forum.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table
public class ArticleTypes {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleTypeId;
	
	private String articleTypeName;

	@OneToMany(mappedBy = "articleTypes",cascade = CascadeType.ALL)
	private List<Articles> articles;
	
	
	
	public ArticleTypes() {
	}

	public ArticleTypes(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public ArticleTypes(int articleTypeId, String articleTypeName) {
		this.articleTypeId = articleTypeId;
		this.articleTypeName = articleTypeName;
	}


	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
	
	
	
	
	
}
