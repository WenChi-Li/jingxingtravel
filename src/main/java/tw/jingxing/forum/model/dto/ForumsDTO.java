package tw.jingxing.forum.model.dto;

public class ForumsDTO {

	private int forumId;

	private String forumName;

	private String forumIntroduction;

	private String forumImage;


	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
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

	@Override
	public String toString() {
		return "ForumsDTO [forumId=" + forumId + ", forumName=" + forumName + ", forumIntroduction=" + forumIntroduction
				+ ", forumImage=" + forumImage + "]";
	}



}
