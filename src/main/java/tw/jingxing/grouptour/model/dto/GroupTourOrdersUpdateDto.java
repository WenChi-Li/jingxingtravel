package tw.jingxing.grouptour.model.dto;

public class GroupTourOrdersUpdateDto {

	private int GroupTourOrdersID ;
	
	private int groupTourID;
	
	private String customerName;
	
	private String customerPhone;
	
	private String customerEmail;
	
	private int quantity ;

	public int getGroupTourOrdersID() {
		return GroupTourOrdersID;
	}

	public void setGroupTourOrdersID(int groupTourOrdersID) {
		GroupTourOrdersID = groupTourOrdersID;
	}

	public int getGroupTourID() {
		return groupTourID;
	}

	public void setGroupTourID(int groupTourID) {
		this.groupTourID = groupTourID;
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

	@Override
	public String toString() {
		return "GroupTourOrdersUpdateDto [GroupTourOrdersID=" + GroupTourOrdersID + ", groupTourID=" + groupTourID
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerEmail="
				+ customerEmail + ", quantity=" + quantity + "]";
	}

	
}
