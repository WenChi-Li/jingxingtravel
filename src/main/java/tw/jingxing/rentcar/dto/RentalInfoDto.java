package tw.jingxing.rentcar.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.rentcar.model.Car;
import tw.jingxing.rentcar.model.CarModel;

public class RentalInfoDto {

	private int rentalId;

	private Car car;

	private UserData userdata;

	private UserDataDto userdataDto;

	private String content;

	private Date pickupDate;

	private Date returnDate;

	private CarModel carmodel;

	private LocalDateTime pickupDateTime;

	private LocalDateTime returnDateTime;

	private BigDecimal totalCost;

	private String status;

	private String licensePlate;

	public UserDataDto getUserdataDto() {
		return userdataDto;
	}

	public void setUserdataDto(UserDataDto userdataDto) {
		this.userdataDto = userdataDto;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public UserData getUserdata() {
		return userdata;
	}

	public void setUserdata(UserData userdata) {
		this.userdata = userdata;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public CarModel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(CarModel carmodel) {
		this.carmodel = carmodel;
	}

	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public LocalDateTime getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(LocalDateTime returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	//
//		public Time getReturnTime() {
//			return returnTime;
//		}
	//
//		public void setReturnTime(Time returnTime) {
//			this.returnTime = returnTime;
//		}
	//
//		public Time getPickupTime() {
//			return pickupTime;
//		}
	//
//		public void setPickupTime(Time pickupTime) {
//			this.pickupTime = pickupTime;
//		}

}
