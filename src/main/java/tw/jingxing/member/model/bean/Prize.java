package tw.jingxing.member.model.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Component
@Table(name = "prize")
public class Prize {
	
	@Id
	@Column(name = "PRIZEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prizeID;
	
	@Column(name = "PRIZENAME")
	private String prizeName;
	
	@Column(name = "DISCOUNT")
	private double discount;
	
	@Column(name = "PRIZEPIC")
	private byte[] prizePic;

	@Transient
	private String prizePicBase64;
	
	
	public List<UserStorage> getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(List<UserStorage> userStorage) {
		this.userStorage = userStorage;
	}

	public List<PrizePool> getPrizePool() {
		return prizePool;
	}

	public void setPrizePool(List<PrizePool> prizePool) {
		this.prizePool = prizePool;
	}

	@OneToMany(mappedBy = "prize", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserStorage> userStorage;
	
	@OneToMany(mappedBy = "prize", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PrizePool> prizePool;
	
	
	public int getPrizeID() {
		return prizeID;
	}

	public void setPrizeID(int prizeID) {
		this.prizeID = prizeID;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public byte[] getPrizePic() {
		return prizePic;
	}

	public void setPrizePic(byte[] prizePic) {
		this.prizePic = prizePic;
	}

	public String getPrizePicBase64() {
		return prizePicBase64;
	}

	public void setPrizePicBase64(String prizePicBase64) {
		this.prizePicBase64 = prizePicBase64;
	}



}
