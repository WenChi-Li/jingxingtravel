package tw.jingxing.rentcar.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSURANCE")
@Component
public class insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSURANCEID")
    private int insuranceID;

    @Column(name = "INSURANCETYPE", nullable = false)
    private String insuranceType;

    @Column(name = "COVERAGEDESCRIPTION", nullable = false)
    private String coverageDescription;

    @Column(name = "INSURANCECOST", nullable = false)
    private int insuranceCost;

    @ManyToOne
    @JoinColumn(name = "CARMODELID")
    private CarModel carModel;

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getCoverageDescription() {
		return coverageDescription;
	}

	public void setCoverageDescription(String coverageDescription) {
		this.coverageDescription = coverageDescription;
	}

	public int getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(int insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

    // Constructors, getters, and setters
}

