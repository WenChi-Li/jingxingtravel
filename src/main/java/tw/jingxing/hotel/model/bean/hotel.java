package tw.jingxing.hotel.model.bean;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Hotels")
@Component
public class hotel {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int hotelId;
    private String hotelName;
    private String address;
    private String phone;
    private String area;
    private boolean swimmingpool;
    private boolean breakfast;
    private boolean tub;
    private boolean gym;
    private boolean parking;
    private String hotelImage;
    
    @Transient
    MultipartFile multipartFile;
    
    public MultipartFile getMultipartFile() {
	
		return multipartFile;
	}
	
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
		
		try {
			this.hotelImage= "data:/image/png;base64,"+Base64.getEncoder().encodeToString(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@OneToMany(mappedBy = "hotel",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<rooms> rooms;

    public int getHotelId() {
		return hotelId;
	}
    public hotel() {
    }

	public hotel(int hotelId, String hotelName, String address, String phone, String area, boolean swimmingpool,
			boolean breakfast, boolean tub, boolean gym, boolean parking, String hotelImage) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.address = address;
		this.phone = phone;
		this.area = area;
		this.swimmingpool = swimmingpool;
		this.breakfast = breakfast;
		this.tub = tub;
		this.gym = gym;
		this.parking = parking;
		this.hotelImage = hotelImage;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isSwimmingpool() {
		return swimmingpool;
	}

	public void setSwimmingpool(boolean swimmingpool) {
		this.swimmingpool = swimmingpool;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isTub() {
		return tub;
	}

	public void setTub(boolean tub) {
		this.tub = tub;
	}

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public String getHotelImage() {
		return hotelImage;
	}

	public void setHotelImage(String hotelImage) {
		this.hotelImage = hotelImage;
	}

	
	
	
	

	
}
