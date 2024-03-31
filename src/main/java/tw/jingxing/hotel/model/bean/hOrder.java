package tw.jingxing.hotel.model.bean;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="HORDER")
public class hOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
    int orderid;
	@Column(name="MID")
     int mid;

    double totalPrice;
	
     Date orderDate;
	
     String orderStatus;
     @OneToMany(mappedBy="hOrder", cascade=CascadeType.ALL)
     Set<orderItem> items = new LinkedHashSet<>();
    public hOrder() {
		
	}
	public hOrder(int orderid, int mid, double totalPrice, Date orderDate, String orderStatus, Set<orderItem> items) {
		super();
		this.orderid = orderid;
		this.mid = mid;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.items = items;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Set<orderItem> getItems() {
		return items;
	}
	public void setItems(Set<orderItem> items) {
		this.items = items;
	}

	
}
