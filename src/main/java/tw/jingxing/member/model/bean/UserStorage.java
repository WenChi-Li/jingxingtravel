package tw.jingxing.member.model.bean;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Component
@Table(name = "userstorage")
public class UserStorage {

	@Id
	@Column(name = "USID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usid;

	@Column(name = "EXPIRATION")
	private String expiration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_MID")
	private UserData userData;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PRIZEID")
	private Prize prize;

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

}
