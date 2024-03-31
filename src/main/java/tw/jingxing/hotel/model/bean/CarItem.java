package tw.jingxing.hotel.model.bean;

public class CarItem {
private hotel hotel;
private rooms rooms;
private Integer orderNum;
private String startDate;
private String EndDate;
private long numberOfDays;
public CarItem(rooms rooms,
		Integer orderNum, String startDate, String endDate, long numberOfDays) {
	super();
	
	this.rooms = rooms;
	this.orderNum = orderNum;
	this.startDate = startDate;
	EndDate = endDate;
	this.numberOfDays = numberOfDays;
}
public hotel getHotel() {
	return hotel;
}
public void setHotel(hotel hotel) {
	this.hotel = hotel;
}
public rooms getRooms() {
	return rooms;
}
public void setRooms(rooms rooms) {
	this.rooms = rooms;
}
public Integer getOrderNum() {
	return orderNum;
}
public void setOrderNum(Integer orderNum) {
	this.orderNum = orderNum;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return EndDate;
}
public void setEndDate(String endDate) {
	EndDate = endDate;
}
public long getNumberOfDays() {
	return numberOfDays;
}
public void setNumberOfDays(long numberOfDays) {
	this.numberOfDays = numberOfDays;
}


}