package tw.jingxing.rentcar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.jingxing.rentcar.dto.CarModelDto;
import tw.jingxing.rentcar.model.CarModel;
import tw.jingxing.rentcar.service.CarModelService;

@Controller
@jakarta.transaction.Transactional
public class CarModelController {

	@Autowired
	private CarModelService cmService;
//	@Autowired
//	@Autowired
//	private CarModel carModel;
	
	
	 @GetMapping("/home")
	    public String home() {
	        // 返回要跳转的页面名称，这里假设页面名称为 "home"
	        return "cars.html";
	    }
	
	 @Transactional
	@GetMapping("/findAllCarModel")  //搜尋全部
	@ResponseBody
	public List<CarModel> findAllcar() {
		System.out.println("測試");
//		List< CarModel>   carmodelList=cmService.findAll();
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
		
//		List<CarModel> findAll = cmService.findAll();
//		ArrayList<Car> rscars = new ArrayList<>();
//		
//		for (CarModel carModel : findAll) {
//			List<Car> cars = carModel.getCars();
//			for (Car car : cars) {
//				
////				System.out.println(car);
//				rscars.add(car);
//			}
//			carModel.setCars(rscars);
//			rscars.clear();
//		}	
	return	cmService.findAll();
//	return findAll;
	}
//	@GetMapping("/findCarModel/{id}")
//	public List<CarModel> findCarModelById(@PathVariable("id")int id){
//		
//		
//		
//		return cmService.findById(id);
//	}
	
	//用id進行搜尋
	@PostMapping("/findCarModelById")
	@ResponseBody
	public CarModelDto findCarModelById(@RequestBody CarModel carModel,CarModelDto cmdto){
		
		System.out.println(carModel.getCarmodelid());
		CarModel carmodeldata= cmService.findById(carModel.getCarmodelid());
		
		cmdto.setCarmodelid(carmodeldata.getCarmodelid());
		cmdto.setCarType(carmodeldata.getCarType());
//      cmdto.setAirConditioning(carmodel.getAirConditioning());
      cmdto.setBaggage(carmodeldata.getBaggage());
      cmdto.setCarmodelname(carmodeldata.getCarmodelname());
      cmdto.setFuelType(carmodeldata.getFuelType());
      cmdto.setImage(carmodeldata.getImage());
      cmdto.setSeat(carmodeldata.getSeat());
      cmdto.setBase64(carmodeldata.getBase64());
      cmdto.setCost(carmodeldata.getCost());
      
//      return cmService.findById(carModel.getCarmodelid());
//      return carmodeldata;
      return cmdto;
	}
	//用name進行搜尋
	@PostMapping("/findCarModelByName")
	@ResponseBody
	public CarModel findCarModelByName(@RequestBody CarModel carModel){
		String stringName=carModel.getCarmodelname();
		System.out.println(stringName);
		System.out.println(carModel.getCarmodelname());
		
		return cmService.findByName(carModel.getCarmodelname());
	}
	
	
	@GetMapping("/FindcarModel.controller") // 搜尋
	@ResponseBody //
	public CarModel findcarModel(@RequestParam("inputValue") String CarModelName ) {
//		 ArrayList<CarModel> result = new ArrayList<CarModel>();
		CarModel result = cmService.findByName(CarModelName);

		return result;
	}
	@PutMapping("/updateData") // 搜尋
	@ResponseBody 
	public CarModel findBycarModelId(@ModelAttribute CarModel carModel) {
		
		System.err.println("controller  update");
		return cmService.update(carModel);
	}
	
	@PutMapping("/update/{id}")//修改前先找出確認有無這筆資料
	@ResponseBody 
	public CarModel findBycarModelId(@PathVariable("id")int id) {
		
		
		
		return cmService.findByIdUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}") // 搜尋
	@ResponseBody 
	public void deleteById(@PathVariable("id")int id ) {
		 cmService.deleteByID(id);
//		 return null;
	}


    @PostMapping("/insert.data")  //新增
    @ResponseBody
    public CarModelDto insertdata(@ModelAttribute  CarModel carmodel,CarModelDto cmdto) {
//    	@RequestParam("test") MultipartFile imageFile,
      
        System.out.println("Car Type: " + carmodel.getCarType()); 
        System.out.println("Seat: " + carmodel.getSeat());
        System.out.println("Transmission: " + carmodel.getTransmission());
        System.out.println("image"+carmodel.getMultipartFile());      
        // 处理上传的图像文件
//        MultipartFile imageFile = carmodel.getTest();
        // 在这里你可以对上传的图像文件进行处理，比如保存到磁盘或者存储到数据库
//        System.out.println("image:"+imageFile);
//        if (imageFile != null && !imageFile.isEmpty()) {
        if (carmodel.getMultipartFile() != null && !carmodel.getMultipartFile().isEmpty()) {
            try {
                byte[] imageBytes = carmodel.getMultipartFile().getBytes();
                System.out.println("bytes"+imageBytes);

                carmodel.setImage(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error");
            }
        }else {
        	
        	 try { 
        	    	String imagePath = "C:\\Java 3月\\workSpace\\jingxingtravel\\src\\main\\resources\\static\\unnamed.jpg";
        	    	System.out.println("前端沒有給圖片插入預設");
        	    	File imageFile = new File(imagePath);
        			FileInputStream fis = new FileInputStream(imageFile);
        			byte[] unnamed=fis.readAllBytes();
        			System.out.println(unnamed);
        			carmodel.setImage(unnamed);
        			fis.close();
        	    	
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	
        }
        cmService.insert(carmodel);
        cmdto.setCarType(carmodel.getCarType());
//        cmdto.setAirConditioning(carmodel.getAirConditioning());
        cmdto.setBaggage(carmodel.getBaggage());
        cmdto.setCarmodelname(carmodel.getCarmodelname());
        cmdto.setFuelType(carmodel.getFuelType());
        cmdto.setImage(carmodel.getImage());
        cmdto.setSeat(carmodel.getSeat());
        cmdto.setBase64(carmodel.getBase64());
        cmdto.setTransmission(carmodel.getTransmission());
        return cmdto;
    }

 
}

