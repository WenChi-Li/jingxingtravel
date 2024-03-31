package tw.jingxing.member.model.bean;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admindata")
@Component
public class AdminData {

	@Id
	@Column(name="ADMINID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminID;
	
	@Column(name="ADMINNAME")
	private String adminName;
	
	@Column(name="ADMINACC")
	private String adminAcc;
	
	@Column(name="ADMINPWD")
	private String adminPwd;
	
	@Column(name="ADMINTEL")
	private String adminTel;
	
	@Column(name="ADMINEMAIL")
	private String adminEmail;
	
	@Column(name="ONCALL")
	private int oncall;
	
	@Column(name="PROBLEMTYPE")
	private String problemType;
	
	public int getOncall() {
		return oncall;
	}

	public void setOncall(int oncall) {
		this.oncall = oncall;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminAcc() {
		return adminAcc;
	}

	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

}
