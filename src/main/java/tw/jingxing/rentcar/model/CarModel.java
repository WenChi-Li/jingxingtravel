package tw.jingxing.rentcar.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "CARMODEL")
@Component
public class CarModel {

	@Id
	@Column(name = "CARMODELID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carmodelid;
	@Column(name = "CARMODELNAME")
	private String carmodelname;
	@Column(name = "CARTYPE")
	private String carType;
	@Column(name = "SEAT")
	private int seat;
	@Column(name = "BAGGAGE")
	private int baggage;
//	@Column(name = "AIRCONDITIONING")
//	private boolean airConditioning;
	@Column(name = "TRANSMISSION")
	private String transmission;
	@Column(name = "FUELTYPE")
	private String fuelType;
	@Column(name = "IMAGE")
	private byte[] image;
	@Column(name = "COST", nullable = true, length = 50)
	private int cost;
	@Transient
	private String base64;
	@Transient
	private MultipartFile multipartFile;

	@OneToMany(mappedBy = "carModel", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Car> cars;
	
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}




	public List<Car> getCars() {
		return cars;
	}
	
	
	

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

//	public List<insurance> getInsurance() {
//		return insurance;
//	}
//
//	public void setInsurance(List<insurance> insurance) {
//		this.insurance = insurance;
//	}
//
//	//	@OneToMany(mappedBy = "carModel",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//	@OneToMany(mappedBy = "carModel")
//	private List<insurance> insurance;
////
//	public List<insurance> getInsurance() {
//		return insurance;
//	}
//
//	public void setInsurance(List<insurance> insurance) {
//		this.insurance = insurance;
//	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

//	public CarModel() {
//	}

//	public int getCarTypeID() {
//		return carTypeID;
//	}
//
//	public void setCarTypeID(int carTypeID) {
//		this.carTypeID = carTypeID;
//	}
	

	public String getCarmodelname() {
		return carmodelname;
	}

	public int getCarmodelid() {
		return carmodelid;
	}

	public void setCarmodelid(int carmodelid) {
		this.carmodelid = carmodelid;
	}

	public void setCarmodelname(String carmodelname) {
		this.carmodelname = carmodelname;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getBaggage() {
		return baggage;
	}

	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}

//	public boolean getAirConditioning() {
//		return airConditioning;
//	}
//	public void setAirConditioning(boolean airConditioning) {
//		this.airConditioning = airConditioning;
//	}
	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		try {
			String base64String = Base64.getEncoder().encodeToString(image);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
			String mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);

			String base64 = "data:%s;base64,".formatted(mimeType) + base64String;

			this.base64 = base64;

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.image = image;
	}

}
