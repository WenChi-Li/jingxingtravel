package tw.jingxing.member.model.bean;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicerecord")
@Component
public class ServiceRecord {
	
	@Id
	@Column(name = "RID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rid;

	@Column(name = "SERVICETARGET")
	private String serviceTarget;
	
	@Column(name = "RECORDTYPE")
	private String recordType;
	
	@Column(name = "RECORDDATE")
	private String recordDate;
	
	@Column(name = "RECORDTEXT")
	private String recordText;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getServiceTarget() {
		return serviceTarget;
	}

	public void setServiceTarget(String serviceTarget) {
		this.serviceTarget = serviceTarget;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordText() {
		return recordText;
	}

	public void setRecordText(String recordText) {
		this.recordText = recordText;
	}
	
	
	
	
	
}
