package tw.jingxing.rentcar.model;
import org.hibernate.annotations.CascadeType;

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
@Table(name = "CAR")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARID")
    private int carId;
    
    
    @Column(name = "LICENSEPLATE", nullable = true, length = 50)
    private String licensePlate;
    
    @Column(name = "STATUS", nullable = true, length = 50)
    private String status;
    
//    @Column(name = "CARCOST", nullable = true)
//    private int carCost;
//    
//    @Transient
//	private int carTypeID;
// 
    
//    (cascade = CascadeType.REMOVE)
    @ManyToOne
    @JoinColumn(name = "CARMODELID")
    @JsonIgnoreProperties(value = "cars")
    private CarModel carModel;
    
  
//
//	public int getCarTypeID() {
//		return carTypeID;
//	}
//
//	public void setCarTypeID(int carTypeID) {
//		this.carTypeID = carTypeID;
//	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}
    
    

	public CarModel getCarModel() {
		return carModel;
	}

//	public int getCarid() {
//		return carid;
//	}
//
//	public void setCarid(int carid) {
//		this.carid = carid;
//	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public int getCarCost() {
//		return carCost;
//	}
//
//	public void setCarCost(int carCost) {
//		this.carCost = carCost;
//	}

    // Getters and setters
}

