package tw.jingxing.rentcar.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import tw.jingxing.member.model.bean.UserData;

@Entity
@Table(name = "RENTALINFO")
@Component
public class RentalInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTALID")
    private int rentalId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CARID")
    private Car car;
    
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="mid")
    @JsonIgnoreProperties(value="rentalinfos")
    private UserData userdata;
 
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "PICKUPDATE")
    private Date pickupDate;
    
    @Column(name = "RETURNDATE")
    private Date returnDate;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carmodelid")
    @JsonIgnoreProperties
    private CarModel carmodel;
    
    @Column(name="PICKUPDATETIME",nullable = true, length = 50)
    private LocalDateTime pickupDateTime;
    
    
    @Column(name="RETURNDATETIME",nullable = true, length = 50)
    private LocalDateTime returnDateTime;
//    
//    @Column(name="RETURNTIME",nullable = true, length = 50)
//    private Time returnTime;
//    
//    @Column(name="PICKUPTIME",nullable = true, length = 50)
//    private Time pickupTime;
//
//    
//    @ManyToOne
//    @JoinColumn(name = "mid")
//    private UserData userData;
    
    @Column(name = "TOTALCOST",nullable = true, length = 50)
    private BigDecimal totalCost;
    
    @Column(name = "STATUS", nullable = true, length = 50)
    private String status;
    
    @Column(name = "LICENSEPLATE", nullable = true, length = 50)
    private String licensePlate;
    
    @Transient
    private String formattedReturnDateTime;
    
    
    @Transient
    private String formattedPickUPDateTime;
//
//	public Time getReturnTime() {
//		return returnTime;
//	}
//
//	public void setReturnTime(Time returnTime) {
//		this.returnTime = returnTime;
//	}
//
//	public Time getPickupTime() {
//		return pickupTime;
//	}
//
//	public void setPickupTime(Time pickupTime) {
//		this.pickupTime = pickupTime;
//	}

	

	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}


	public String getFormattedReturnDateTime() {
		return formattedReturnDateTime;
	}


	public void setFormattedReturnDateTime(String formattedReturnDateTime) {
		this.formattedReturnDateTime = formattedReturnDateTime;
	}


	public String getFormattedPickUPDateTime() {
		return formattedPickUPDateTime;
	}


	public void setFormattedPickUPDateTime(String formattedPickUPDateTime) {
		this.formattedPickUPDateTime = formattedPickUPDateTime;
	}


	public LocalDateTime getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(LocalDateTime returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public CarModel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(CarModel carmodel) {
		this.carmodel = carmodel;
	}

	public UserData getUserdata() {
		return userdata;
	}

	public void setUserdata(UserData userdata) {
		this.userdata = userdata;
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

    // Getters and setters
}
