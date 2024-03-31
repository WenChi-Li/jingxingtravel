package tw.jingxing.grouptour.model.bean;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grouptourorders")
@Component
public class GroupTourOrdersBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grouptourordersid")
	private int groupTourOrdersID;

//	一筆行程可在很多個訂單內，一筆訂單只能有一筆行程

//	產品FK
	@ManyToOne(targetEntity = GroupTourBean.class)
	@JoinColumn(name = "grouptourid")
//	@JsonIgnoreProperties(value = "groupTourOrdersBean")
//	@JsonIgnore
	private GroupTourBean groupTourBean;

	@Column(name = "customername")
	private String customerName;

	@Column(name = "customerphone")
	private String customerPhone;

	@Column(name = "customeremail")
	private String customerEmail;

	@Column(name = "quantity")
	private int quantity;

	public GroupTourOrdersBean() {
	}

	public int getGroupTourOrdersID() {
		return groupTourOrdersID;
	}

	public void setGroupTourOrdersID(int groupTourOrdersID) {
		this.groupTourOrdersID = groupTourOrdersID;
	}

	public GroupTourBean getGroupTourBean() {
		return groupTourBean;
	}

	public void setGroupTourBean(GroupTourBean groupTourBean) {
		this.groupTourBean = groupTourBean;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
