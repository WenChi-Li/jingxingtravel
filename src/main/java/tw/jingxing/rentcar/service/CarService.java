package tw.jingxing.rentcar.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.jingxing.rentcar.dao.CarModelRepository;
import tw.jingxing.rentcar.dao.CarRepository;
import tw.jingxing.rentcar.dto.CarModelDto;
import tw.jingxing.rentcar.model.Car;
import tw.jingxing.rentcar.model.CarModel;

@Service
//@Transactional
public class CarService {

	@Autowired
	private CarRepository repository;

	@Autowired
	private CarModelRepository carModelRepository;

	@Autowired
	private CarModelDto cmdto;

	public List<Car> getCarsByStatus(String status) {
		return repository.findByStatus(status);
	}

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

	// 修改資料 先找出這筆資料
	public Car findByIdUpdate(int id) {
//		Optional<CarModel> op = pResp.findById( id);
		Optional<Car> op = repository.findById(id);
		CarModel carModelDatabase = null;// 資料庫內現在的資料
		Car carDatabase = null;// 資料庫內現在的資料
		if (op.isPresent()) {
			System.out.println("取出這筆資料" + op.get());
//			 carModelDatabase = op.get();
			carDatabase = op.get();

//				carModelDatabase.setBase64(bytesToBase64(carModelDatabase.getImage()));

//			 return carModelDatabase;
			return carDatabase;
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
//	public List<CarModel> findAll() {
//
//		List<CarModel> carList = repository.findAll();
////		
//////		List<CarModel> carModels=new ArrayList<>();
//		for (CarModel carmodel : carmodelList) {
//			//如果沒有圖片 
//			if (carmodel.getImage() != null) {
//
//				carmodel.setBase64(bytesToBase64(carmodel.getImage()));
//			} else {
////				carmodel.setBase64();
//			}
//////	carModels.add(carmodel);
//////	carmodel.getImage();
////			System.out.println(carmodel);
////			System.out.println(carmodel.getImage());
////			System.out.println(carmodel.getBase64());
////			
//		}
//////		return pResp.findAll();
////		System.out.println(carmodelList);
////		return carmodelList;
//	}

//	public Page<CarModel> findAllByPage(Pageable pageable) {
//		return pResp.findAll(pageable);
//	}
//
	public Car insert(Car p) {
		return repository.save(p);
	}

	public Car updateCar(Car car) {
		System.out.println("確認有這筆資料準備進行查詢");
//		Optional<CarModel> updataCarModel=pResp.findById(carModel.getCarTypeID());
		Optional<Car> optionalCar = repository.findById(car.getCarId());
		if (optionalCar.isPresent()) {
			System.out.println("找到了要更新的记录");
			System.out.println("確認有這筆資料準備進行修改");
			Car databaseCar = optionalCar.get();
//			System.out.println(car.getCarModel().getCarTypeID());
			CarModel databaseCarModel = carModelRepository.findById(car.getCarModel().getCarmodelid()).orElse(null);
			System.out.println("sdfgdfg" + databaseCarModel);
			if (databaseCarModel != null) {
				// 设置 Car 对象的 CarModel 属性
				databaseCar.setCarModel(databaseCarModel);
				databaseCar.setStatus(car.getStatus());
//				databaseCar.setCarCost(car.getCarCost());
				databaseCar.setLicensePlate(car.getLicensePlate());
				System.out.println("执行更新操作");
				// 设置不需要修改的其他属性
				// databaseCar.setStatus(car.getStatus());
				// databaseCar.setCarCost(car.getCarCost());
				// 其他不需要修改的属性也可以在这里设置
				System.out.println("修改");
				// 保存更新后的 Car 对象
				return repository.save(databaseCar);
			} else {
				System.out.println("找不到匹配的 CarModel 对象");
			}
		} else {
			System.out.println("未找到要更新的记录");
		}
		return null; // 或者根据实际情况返回其他响应
	}

//rentinfo更改 car狀態
	public Car updateByRentInfo(int carid) {
		Optional<Car> cardatabase = repository.findById(carid);
		Car car = null;
		if (cardatabase.isPresent()) {

			car = cardatabase.get();
		}
		return car;
	}

//
//	public void delete(CarModel p) {
//		pResp.delete(p);
//	}

//	public String deleteByID(int id) {
////		Optional<CarModel> op = pResp.findById(id);
//		Optional<Car> op = repository.findById(id);
//
//		if (op.isPresent()) {
//			Car car = op.get();
//		car.setCarModel(null);
//		repository.save(car);
//			
//			repository.deleteById(id);
//			System.out.println("刪除成功"+id);
//			return "刪除成功"+id;
//
//		}
//		return "刪除失敗  無此筆資料";
//
//	}

	public String deleteByID(int id) {
//		Optional<CarModel> op = pResp.findById(id);
		Optional<Car> op = repository.findById(id);

		if (op.isPresent()) {
			Car car = op.get();
			car.setCarModel(null);
			repository.save(car);

			repository.deleteById(id);
			System.out.println("刪除成功" + id);
			return "刪除成功" + id;

		}
		return "刪除失敗  無此筆資料";

	}

//	public CarModel findByName(String CarModelName) {
//		Optional<CarModel> check = pResp.findByCarmodelname(CarModelName);
//
//		if (check.isPresent()) {
//			CarModel result = check.get();
//			System.out.println("找到該筆資料");
//			return result;
//		}
//		return null;
//	}

	public List<Car> findById(int id) {
		Optional<Car> check = repository.findById(id);
		List<Car> findByCarModel_CarModelId = repository.findByCarModel_carmodelid(id);
//		repository.findAll()
		if (check.isPresent()) {
			System.out.println("找到該筆資料");
			Car result = check.get();

//			result.setBase64(bytesToBase64(result.getImage()));
		}
		return findByCarModel_CarModelId;
//		System.out.println("找不到該筆資料");
//		return null;
	}

}
