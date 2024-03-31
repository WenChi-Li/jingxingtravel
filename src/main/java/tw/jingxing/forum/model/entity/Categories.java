package tw.jingxing.forum.model.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

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

@Entity @Table(name = "Categories")
@Component
public class Categories implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "FK_categories_forums")
	@JsonIgnoreProperties(value = {"categories"})
	private Forums forums;
	
	
	@OneToMany(mappedBy = "categories" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Articles> articles;
	
	
	public Categories() {
	}
	
	
	public Categories(String categoryName, Forums forums) {
		this.categoryName = categoryName;
		this.forums = forums;
	}



	public Categories(int categoryId, String categoryName, Forums forums) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.forums = forums;
	}



	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Forums getForums() {
		return forums;
	}


	public void setForums(Forums forums) {
		this.forums = forums;
	}


	public List<Articles> getArticles() {
		return articles;
	}


	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}


	
	
	
	
	
}
