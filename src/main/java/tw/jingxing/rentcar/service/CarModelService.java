package tw.jingxing.rentcar.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.jingxing.rentcar.dao.CarModelRepository;
import tw.jingxing.rentcar.dto.CarModelDto;
import tw.jingxing.rentcar.model.CarModel;

@Service
@jakarta.transaction.Transactional
public class CarModelService {

	@Autowired
	private CarModelRepository pResp;

	@Autowired
	private CarModelDto cmdto;

	public String bytesToBase64(byte[] toBase64) {
//	public String bytesToBase64(List<CarModel> carmodelList) throws IOException {

//		List<CarModelDto> cmdtoList=new ArrayList<>();
//		String mimeType=null;
//		String photoBase64=null;
//		
//		for(CarModel carmodel:carmodelList) {
		if (toBase64 != null) {
			String base64String = Base64.getEncoder().encodeToString(toBase64);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(toBase64);

			String mimeType = null;
			try {
				mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64String;

//				 cmdto.setCarType(carmodel.getCarType());
//				 cmdto.setCarmodelname(carmodel.getCarType());
//				 cmdto.setSeat(carmodel.getSeat());
//				 cmdto.setBaggage(carmodel.getBaggage());
//				 cmdto.setAirConditioning(carmodel.getAirConditioning());
//				 cmdto.setFuelType(carmodel.getFuelType());
//				 cmdto.setTransmission(carmodel.getTransmission());
//				 cmdto.setImage(carmodel.getImage());
//				 cmdtoList.add(cmdto);
//			
//		}
//		return photoBase64;
			return photoBase64;
		} else {
			return null;
		}

	}

	// 修改資料
	public CarModel findByIdUpdate(int id) {
		Optional<CarModel> op = pResp.findById(id);
		CarModel carModelDatabase = null;// 資料庫內現在的資料
		if (op.isPresent()) {
			System.out.println("取出這筆資料" + op.get());
			carModelDatabase = op.get();
			carModelDatabase.setBase64(bytesToBase64(carModelDatabase.getImage()));
			return carModelDatabase;
		} else {
			System.out.println("沒有這筆資料  更新資料失敗");
			return null;
		}
//如果更新的時候沒有給圖片   圖片會是null  會去使用原來資料庫的那張圖片set進java bean裡面
//			if (carModel.getImage() == null) {
//		            try { 
//		            	String imagePath = "C:\\Action\\workspace\\RentCarProject\\src\\main\\resources\\static\\unnamed.jpg";
//		            	File imageFile = new File(imagePath);
//						FileInputStream fis = new FileInputStream(imageFile);
//						byte[] unnamed=fis.readAllBytes();
//						System.out.println(unnamed);
//						carModel.setImage(unnamed);
//						fis.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				carModel.setImage(carModelDatabase.getImage());
//			}
//如果有給圖片就直接存到資料庫
//			return pResp.save(carModelDatabase);

	}

//搜尋全部   順便把base64轉換好
	@Transactional
	public List<CarModel> findAll() {
		System.out.println("準備搜尋全部資料");
		List<CarModel> carmodelList = pResp.findAll();
		System.out.println("搜尋全部資料");
////		List<CarModel> carModels=new ArrayList<>();
		if (carmodelList != null) {
			for (CarModel carmodel : carmodelList) {
				// 强制初始化 cars 属性
				Hibernate.initialize(carmodel.getCars());				
//				carmodel.setCost(0);				
				if (carmodel.getImage() != null) {
					carmodel.setBase64(bytesToBase64(carmodel.getImage()));
				}

			}
//			for (CarModel carmodel : carmodelList) {
//				// 如果沒有圖片
//				if (carmodel.getImage() != null) {
//
//					carmodel.setBase64(bytesToBase64(carmodel.getImage()));
//				}
//
//			}

		} else {
			System.out.println("null 沒有資料");
			return null;
		}
////		return pResp.findAll();
//		System.out.println(carmodelList);
		return carmodelList;
	}

	public Page<CarModel> findAllByPage(Pageable pageable) {
		return pResp.findAll(pageable);
	}

	public CarModel insert(CarModel p) {
		return pResp.save(p);
	}

	public CarModel update(CarModel carModel) {
		System.out.println("確認有這筆資料準備進行查詢");
		Optional<CarModel> updataCarModel = pResp.findById(carModel.getCarmodelid());
		CarModel databaseCarModel;
		if (updataCarModel.isPresent()) {
			System.out.println("確認有這筆資料準備進行修改");
			databaseCarModel = updataCarModel.get();
			// 判斷前端有沒有給圖片如果有的話做以下處理
			if (carModel.getMultipartFile() != null && !carModel.getMultipartFile().isEmpty()) {
				try {
					byte[] imageBytes = carModel.getMultipartFile().getBytes();
					System.out.println("bytes" + imageBytes);

					carModel.setImage(imageBytes);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("error");
				}
			} else {
				// 前端如果沒有給圖片的話做以下處理
				carModel.setImage(databaseCarModel.getImage());

//        	 try { 
//        	    	String imagePath = "C:\\Action\\workspace\\RentCarProject\\src\\main\\resources\\static\\unnamed.jpg";
//        	    	File imageFile = new File(imagePath);
//        			FileInputStream fis = new FileInputStream(imageFile);
//        			byte[] unnamed=fis.readAllBytes();
//        			System.out.println(unnamed);
//        			carModel.setImage(unnamed);
//        			fis.close();
//        	    	
//        		} catch (IOException e) {
//        			e.printStackTrace();
//        		}  	
			}
//   pResp.save(carModel);
//        cmService.insert(carmodel);
			cmdto.setCarType(carModel.getCarType());
//        cmdto.setAirConditioning(carmodel.getAirConditioning());
			cmdto.setBaggage(carModel.getBaggage());
			cmdto.setCarmodelname(carModel.getCarmodelname());
			cmdto.setFuelType(carModel.getFuelType());
			cmdto.setImage(carModel.getImage());
			cmdto.setSeat(carModel.getSeat());
			cmdto.setBase64(carModel.getBase64());
			cmdto.setTransmission(carModel.getTransmission());
			return pResp.save(carModel);
//			return pResp.save(carModel);
		}
		return null;
//		return pResp.save(p);
	}

	public void delete(CarModel p) {
		pResp.delete(p);
	}

	public String deleteByID(int id) {
		Optional<CarModel> op = pResp.findById(id);

		if (op.isPresent()) {
			pResp.deleteById(id);
			System.out.println("刪除成功" + id);
			return "刪除成功" + id;

		}
		return "刪除失敗  無此筆資料";

	}

	public CarModel findByName(String CarModelName) {
		Optional<CarModel> check = pResp.findByCarmodelname(CarModelName);

		if (check.isPresent()) {
			CarModel result = check.get();
			System.out.println("找到該筆資料");
			return result;
		}
		return null;
	}

	public CarModel findById(int id) {
		Optional<CarModel> check = pResp.findById(id);
		if (check.isPresent()) {
			CarModel result = check.get();
			result.setBase64(bytesToBase64(result.getImage()));
			System.out.println("找到該筆資料");
			return result;
		}
		System.out.println("找不到該筆資料");
		return null;
	}
}
