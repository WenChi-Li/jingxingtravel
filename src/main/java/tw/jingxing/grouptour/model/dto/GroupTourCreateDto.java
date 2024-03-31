package tw.jingxing.grouptour.model.dto;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class GroupTourCreateDto {
	
	
	private String itineraryName;
	
	private MultipartFile itineraryImage;
	
	private String itineraryImageBase64;
	
	private String itineraryCode;
	
	private MultipartFile featureImage1;
	
	private String featureImage1Base64;

	private MultipartFile featureImage2;
	
	private String featureImage2Base64;
	
	
	private String feature1;
	
	private String feature2;
	
	private String itineraryContent;
	
	private String seatCapacity;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date departureDate;
	
	private String departureTransport;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	
	private String returnTransport;
	
	private int price;
	
	private String note;

	public String getItineraryName() {
		return itineraryName;
	}

	public void setItineraryName(String itineraryName) {
		this.itineraryName = itineraryName;
	}

	public MultipartFile getItineraryImage() {
		return itineraryImage;
	}

	public void setItineraryImage(MultipartFile itineraryImage) {
		
		String base64Image;
		try {
			base64Image = Base64.getEncoder().encodeToString(itineraryImage.getBytes());
			this.itineraryImageBase64=base64Image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.itineraryImage = itineraryImage;
	}

	public String getItineraryImageBase64() {
		return itineraryImageBase64;
	}

	public void setItineraryImageBase64(String itineraryImageBase64) {
		this.itineraryImageBase64 = itineraryImageBase64;
	}

	public String getItineraryCode() {
		return itineraryCode;
	}

	public void setItineraryCode(String itineraryCode) {
		this.itineraryCode = itineraryCode;
	}

	public MultipartFile getFeatureImage1() {
		return featureImage1;
	}

	public void setFeatureImage1(MultipartFile featureImage1) {
		
		String base64Image;
		try {
			base64Image = Base64.getEncoder().encodeToString(featureImage1.getBytes());
			this.featureImage1Base64=base64Image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.featureImage1 = featureImage1;
	}

	public String getFeatureImage1Base64() {
		return featureImage1Base64;
	}

	public void setFeatureImage1Base64(String featureImage1Base64) {
		this.featureImage1Base64 = featureImage1Base64;
	}

	public MultipartFile getFeatureImage2() {
		return featureImage2;
	}

	public void setFeatureImage2(MultipartFile featureImage2) {
		
		String base64Image;
		try {
			base64Image = Base64.getEncoder().encodeToString(featureImage2.getBytes());
			this.featureImage2Base64=base64Image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.featureImage2 = featureImage2;
	}

	public String getFeatureImage2Base64() {
		return featureImage2Base64;
	}

	public void setFeatureImage2Base64(String featureImage2Base64) {
		this.featureImage2Base64 = featureImage2Base64;
	}

	public String getFeature1() {
		return feature1;
	}

	public void setFeature1(String feature1) {
		this.feature1 = feature1;
	}

	public String getFeature2() {
		return feature2;
	}

	public void setFeature2(String feature2) {
		this.feature2 = feature2;
	}

	public String getItineraryContent() {
		return itineraryContent;
	}

	public void setItineraryContent(String itineraryContent) {
		this.itineraryContent = itineraryContent;
	}

	public String getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(String seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTransport() {
		return departureTransport;
	}

	public void setDepartureTransport(String departureTransport) {
		this.departureTransport = departureTransport;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnTransport() {
		return returnTransport;
	}

	public void setReturnTransport(String returnTransport) {
		this.returnTransport = returnTransport;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "GroupTourCreateDto [itineraryName=" + itineraryName + ", itineraryImage=" + itineraryImage
				+ ", itineraryImageBase64=" + itineraryImageBase64 + ", itineraryCode=" + itineraryCode
				+ ", featureImage1=" + featureImage1 + ", featureImage1Base64=" + featureImage1Base64
				+ ", featureImage2=" + featureImage2 + ", featureImage2Base64=" + featureImage2Base64 + ", feature1="
				+ feature1 + ", feature2=" + feature2 + ", itineraryContent=" + itineraryContent + ", seatCapacity="
				+ seatCapacity + ", departureDate=" + departureDate + ", departureTransport=" + departureTransport
				+ ", returnDate=" + returnDate + ", returnTransport=" + returnTransport + ", price=" + price + ", note="
				+ note + "]";
	}


}
