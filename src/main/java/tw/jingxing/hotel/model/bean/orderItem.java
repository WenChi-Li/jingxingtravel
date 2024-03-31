package tw.jingxing.hotel.model.bean;

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
@Table(name = "HORDERITEM")
public class orderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="CHECKINDATE")
	String checkinDate;
	@Column(name="CHECKOUTDATE")
	String checkoutDate;
	@Column(name="BOOKEDROOMS")
	int bookedRooms;
	@Column(name="PRICE")
	double price;

	long stayDays;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ROOMID")
	private rooms rooms;
	@ManyToOne
	@JoinColumn(name = "ORDERID")
	private hOrder hOrder;

	public orderItem() {

	}

	public orderItem(int id,  String checkinDate, String checkoutDate, int bookedRooms,
			double price, long stayDays) {
		super();
		this.id = id;
		
	
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.bookedRooms = bookedRooms;
		this.price = price;
		this.stayDays = stayDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getBookedRooms() {
		return bookedRooms;
	}

	public void setBookedRooms(int bookedRooms) {
		this.bookedRooms = bookedRooms;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getStayDays() {
		return stayDays;
	}

	public void setStayDays(long stayDays) {
		this.stayDays = stayDays;
	}

	public rooms getRooms() {
		return rooms;
	}

	public void setRooms(rooms rooms) {
		this.rooms = rooms;
	}

	public hOrder gethOrder() {
		return hOrder;
	}

	public void sethOrder(hOrder hOrder) {
		this.hOrder = hOrder;
	}

}
