package tw.jingxing.forum.model.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
@Component
public class Forums {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer forumId;

	private String forumName;

	private String forumIntroduction;

	private String forumImage;

	@OneToMany(mappedBy = "forums",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Categories> categories;

	public Forums() {
	}

	public Forums(String forumName, String forumIntroduction, String forumImage) {
		this.forumName = forumName;
		this.forumIntroduction = forumIntroduction;
		this.forumImage = forumImage;
	}

	public Forums(int forumId, String forumName, String forumIntroduction, String forumImage) {
		this.forumId = forumId;
		this.forumName = forumName;
		this.forumIntroduction = forumIntroduction;
		this.forumImage = forumImage;
	}

	public int getAllPopular() {
		int popular = 0;

		popular += this.getAllLikeNo();

		popular += this.getAllCollectNo();

		return popular;

	}

	public int getAllCollectNo() {
		int collectNo = 0;

		for (Categories categories2 : categories) {
			for (Articles a : categories2.getArticles()) {
				collectNo += a.getCollectionNum();
			}
		}
		return collectNo;

	}

	public int getAllLikeNo() {

		int likeNo = 0;

		for (Categories categories2 : categories) {
			for (Articles a : categories2.getArticles()) {
				likeNo += a.getLikedNum();
			}
		}
		return likeNo;
	}


	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumIntroduction() {
		return forumIntroduction;
	}

	public void setForumIntroduction(String forumIntroduction) {
		this.forumIntroduction = forumIntroduction;
	}

	public String getForumImage() {
		return forumImage;
	}

	public void setForumImage(String forumImage) {
		this.forumImage = forumImage;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

}
