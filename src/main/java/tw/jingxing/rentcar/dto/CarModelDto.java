package tw.jingxing.rentcar.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;

import org.springframework.stereotype.Component;

import tw.jingxing.rentcar.model.Car;

@Component
public class CarModelDto {
	private int carmodelid;
	private String carmodelname;
	private String carType;
	private int seat;
	private int baggage;
//	private boolean airConditioning;
	private String transmission;
	private String fuelType;
	private byte[] image;
	private String base64;
	private Car cars;
	private int cost;
	
	
	
//	private MultipartFile test;
//
//	public MultipartFile getTest() {
//		return test;
//	}
//
//	public void setTest(MultipartFile test) {
//		this.test = test;
//	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Car getCars() {
		return cars;
	}
	
	public int getCarmodelid() {
		return carmodelid;
	}

	public void setCarmodelid(int carmodelid) {
		this.carmodelid = carmodelid;
	}

	public void setCars(Car cars) {
		this.cars = cars;
	}
	public String getBase64() {
		return base64;
	}


	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public CarModelDto() {
	}


	public String getCarmodelname() {
		return carmodelname;
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

//	public boolean isAirConditioning() {
//		return airConditioning;
//	}
//
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
