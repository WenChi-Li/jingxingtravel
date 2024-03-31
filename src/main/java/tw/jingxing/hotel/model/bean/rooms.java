package tw.jingxing.hotel.model.bean;

import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
import jakarta.persistence.Transient;

@Entity
@Table(name ="Rooms")	
@Component
public class rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomTypeName;
	private int roomType;
	private int roomsNumber;
	private Long price;
	
	private String roomsImage;
	
	@Transient
    MultipartFile multipartFile;
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
		try {
			this.roomsImage="data:/image/png;base64,"+Base64.getEncoder().encodeToString(multipartFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @OneToMany(mappedBy="rooms", cascade=CascadeType.ALL)
    Set<orderItem> items = new LinkedHashSet<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotelId")
	private hotel hotel;

	public rooms() {
		super();
		
	}

	public rooms(int roomId, String roomTypeName, int roomType, int roomsNumber, Long price, int hotelId,
			String roomsImage, hotel hotel) {
		super();
		this.roomId = roomId;
		this.roomTypeName = roomTypeName;
		this.roomType = roomType;
		this.roomsNumber = roomsNumber;
		this.price = price;
		this.hotel = hotel;
		this.roomsImage = roomsImage;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	

	public String getRoomsImage() {
		return roomsImage;
	}

	public void setRoomsImage(String roomsImage) {
		this.roomsImage = roomsImage;
	}

	public hotel getHotel() {
		return hotel;
	}

	public void setHotel(hotel hotel) {
		this.hotel = hotel;
	}

	
	
}
