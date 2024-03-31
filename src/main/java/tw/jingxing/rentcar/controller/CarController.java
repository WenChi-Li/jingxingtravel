package tw.jingxing.rentcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.jingxing.rentcar.model.Car;
import tw.jingxing.rentcar.service.CarModelService;
import tw.jingxing.rentcar.service.CarService;

@RequestMapping("/cars")
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private CarModelService carmodelservicer;
	
	//從所有車款那邊按下內容後去查詢這台車款的車
	 @GetMapping("/cars/{id}")
	 @ResponseBody
	    public List<Car> home(@PathVariable("id")int id ) {
	        
		
		 
	        return carService.findById(id);
	    }
	 
	 
	    @GetMapping("/carsByStatus")
	    public List<Car> getCarsByStatus(@RequestParam String status) {
	        return carService.getCarsByStatus(status);
	    }
	
	
	
	
	
	
	
//
//	@Autowired
//	private CarModelService cmService;
////	@Autowired
////	@Autowired
////	private CarModel carModel;
//	
//	
//	@GetMapping("/findAllCarModel")  //搜尋全部
//	@ResponseBody
//	public List<CarModel> findAllcar() {
////		List< CarModel>   carmodelList=cmService.findAll();
//		List<CarModelDto> cmdtoList=new ArrayList<>();
//		for(CarModel carmodel:carmodelList) {
//		String base64String=Base64.getEncoder().encodeToString(carmodel.getImage());
//
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(carmodel.getImage());
//		String mimeType;
//		try {
//			mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream);
//			String photoBase64 = "data:%s;base64,".formatted(mimeType) + base64String;
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//	return carService.findAll();
//	}
////	@GetMapping("/findCarModel/{id}")
////	public List<CarModel> findCarModelById(@PathVariable("id")int id){
////		
////		
////		
////		return cmService.findById(id);
////	}
//	
//	//用id進行搜尋
//	@PostMapping("/findCarModelById")
//	@ResponseBody
//	public CarModel findCarModelById(@RequestBody CarModel carModel){
//		
//		System.out.println(carModel.getCarTypeID());
//		
//		return cmService.findById(carModel.getCarTypeID());
//	}
//	//用name進行搜尋
//	@PostMapping("/findCarModelByName")
//	@ResponseBody
//	public CarModel findCarModelByName(@RequestBody CarModel carModel){
//		String stringName=carModel.getCarmodelname();
//		System.out.println(stringName);
//		System.out.println(carModel.getCarmodelname());
//		
//		return cmService.findByName(carModel.getCarmodelname());
//	}
//	
//	
//	@GetMapping("/FindcarModel.controller") // 搜尋
//	@ResponseBody //
//	public CarModel findcarModel(@RequestParam("inputValue") String CarModelName ) {
////		 ArrayList<CarModel> result = new ArrayList<CarModel>();
//		CarModel result = cmService.findByName(CarModelName);
//
//		return result;
//	}
	 
	 
	 
	 
	 
	 //進行資料修改
	@PutMapping("/updateData") // 搜尋
	@ResponseBody 
	public Car findBycarModelId(@RequestBody Car car) {
//		System.err.println(car.getCarTypeID());
		System.err.println(car.getCarId());
		System.err.println(car.getCarModel().getCarmodelid());
		System.err.println(car.getCarModel().getCarmodelname());
		System.err.println("controller cars update");
//		car.setCarModel(carmodelservicer.findById(car.getCarTypeID()));
//		carModel.setCarTypeID(car.getCarTypeID());.
		return carService.updateCar(car);
	}
//典編輯按鈕的查詢
	@PutMapping("/update/{id}") // 搜尋
	@ResponseBody 
	public Car findBycarModelId(@PathVariable("id")int id) {
		return carService.findByIdUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}") // 搜尋
	@ResponseBody 
	public void deleteById(@PathVariable("id")int id ) {
		 carService.deleteByID(id);
		 
	}
//
//
//    @PostMapping("/insert.data")  //新增
//    @ResponseBody
//    public CarModelDto insertdata(@ModelAttribute  CarModel carmodel,CarModelDto cmdto) {
////    	@RequestParam("test") MultipartFile imageFile,
//      
//        System.out.println("Car Type: " + carmodel.getCarType()); 
//        System.out.println("Seat: " + carmodel.getSeat());
//        System.out.println("Transmission: " + carmodel.getTransmission());
//        System.out.println("image"+carmodel.getMultipartFile());      
//        // 处理上传的图像文件
////        MultipartFile imageFile = carmodel.getTest();
//        // 在这里你可以对上传的图像文件进行处理，比如保存到磁盘或者存储到数据库
////        System.out.println("image:"+imageFile);
////        if (imageFile != null && !imageFile.isEmpty()) {
//        if (carmodel.getMultipartFile() != null && !carmodel.getMultipartFile().isEmpty()) {
//            try {
//                byte[] imageBytes = carmodel.getMultipartFile().getBytes();
//                System.out.println("bytes"+imageBytes);
//
//                carmodel.setImage(imageBytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("error");
//            }
//        }else {
//        	
//        	 try { 
//        	    	String imagePath = "C:\\Action\\workspace\\RentCarProject\\src\\main\\resources\\static\\unnamed.jpg";
//        	    	File imageFile = new File(imagePath);
//        			FileInputStream fis = new FileInputStream(imageFile);
//        			byte[] unnamed=fis.readAllBytes();
//        			System.out.println(unnamed);
//        			carmodel.setImage(unnamed);
//        			fis.close();
//        	    	
//        		} catch (IOException e) {
//        			e.printStackTrace();
//        		}
//        	
//        }
//        cmService.insert(carmodel);
//        cmdto.setCarType(carmodel.getCarType());
////        cmdto.setAirConditioning(carmodel.getAirConditioning());
//        cmdto.setBaggage(carmodel.getBaggage());
//        cmdto.setCarmodelname(carmodel.getCarmodelname());
//        cmdto.setFuelType(carmodel.getFuelType());
//        cmdto.setImage(carmodel.getImage());
//        cmdto.setSeat(carmodel.getSeat());
//        cmdto.setBase64(carmodel.getBase64());
//        cmdto.setTransmission(carmodel.getTransmission());
//        return cmdto;
//    }
//
// 
    @PostMapping("/insert.data")  //新增
    @ResponseBody
    public Car insertdata(@RequestBody Car car) {
//    	@RequestParam("test") MultipartFile imageFile,
      
//        System.out.println("Car Type: " + carmodel.getCarType()); 
//        System.out.println("Seat: " + carmodel.getSeat());
//        System.out.println("Transmission: " + carmodel.getTransmission());
//        System.out.println("image"+carmodel.getMultipartFile());      
       
     carService.insert(car);
       return null;
//        return cmdto;
    }
//
// 
	
	
	
}
