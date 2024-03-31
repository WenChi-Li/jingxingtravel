package tw.jingxing.rentcar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.rentcar.dto.RentalInfoDto;
import tw.jingxing.rentcar.dto.UserDataDto;
import tw.jingxing.rentcar.model.RentalInfo;
import tw.jingxing.rentcar.service.RentalInfoService;

@Controller
public class RentalInfoController {

	@Autowired
	private RentalInfoService rentalInfoService;

	@PostMapping("/rentinfo/insert")
	@ResponseBody
	public RentalInfo rentalinfoinsert(@RequestBody RentalInfo rentalInfo) {

		System.out.println("rentinfo");
		System.out.println(rentalInfo.getPickupDateTime());
		String datetimeValue = rentalInfo.getPickupDateTime().toString();
		System.out.println(datetimeValue);
//		System.out.println(rentalInfo.getPickupTime());
//		rentalInfoService.insert(rentalInfo);
//		   Timestamp timestamp = Timestamp.valueOf(datetimeValue.replace("T", " "));
		System.out.println(rentalInfo.getCarmodel());
//		System.out.println(timestamp);
		return rentalInfoService.insert(rentalInfo);
	}

	@GetMapping("/rentinfo/findAll")
	@ResponseBody
	public ResponseEntity<List<RentalInfo>> rentalinfoFindAll() {
		List<RentalInfo> Allrentalinfo = rentalInfoService.findAll();
		String pickupDate = Allrentalinfo.get(0).getPickupDate().toString();
		String userdata = Allrentalinfo.get(0).getUserdata().toString();

		System.out.println(pickupDate);
		System.out.println(userdata);
//		List<RentalInfoDto> AllrentalinfoDto = new ArrayList<>();
//		for (RentalInfo rentalinfo : Allrentalinfo) {
//			RentalInfoDto rentalInfoDto = new RentalInfoDto();
//			rentalInfoDto.setRentalId(rentalinfo.getRentalId());
////		rentalInfoDto.setCarmodel(rentalinfo.getCarmodel());
//			rentalInfoDto.setPickupDateTime(rentalinfo.getPickupDateTime());
//			rentalInfoDto.setReturnDateTime(rentalinfo.getReturnDateTime());
//			rentalInfoDto.setCar(rentalinfo.getCar());
//			rentalInfoDto.setStatus(rentalinfo.getStatus());
//			rentalInfoDto.setTotalCost(rentalinfo.getTotalCost());
//			rentalInfoDto.setContent(rentalinfo.getContent());
//			rentalInfoDto.setReturnDate(rentalinfo.getReturnDate());
//			rentalInfoDto.setPickupDate(rentalinfo.getPickupDate());
////			if (rentalinfo.getCar() == null) {
////				System.out.println("get car == null");
////			}
//			if (rentalinfo.getCar() == null) {
//				System.out.println("get car == null");
//			}
//			if (rentalinfo.getCarmodel() == null) {
//				System.out.println("get carmodel == null");
//			}
//			if (rentalinfo.getUserdata() == null) {
//				System.out.println("get userdata == null");
//			}
//			UserData userdataNew = new UserData();
//			UserDataDto userdatadto = new UserDataDto();
//			userdatadto.setMid(rentalinfo.getUserdata().getMid());
//			userdatadto.setUserName(rentalinfo.getUserdata().getUserName());
//			userdatadto.setUserTel(rentalinfo.getUserdata().getUserTel());
//			userdatadto.setEmail(rentalinfo.getUserdata().getEmail());
//			userdatadto.setMid(rentalinfo.getUserdata().getMid());
//			userdatadto.setMid(rentalinfo.getUserdata().getMid());
//			userdatadto.setUserAcc(rentalinfo.getUserdata().getUserAcc());
//
//			System.out.println(rentalinfo.getUserdata().getBirthday());
//			rentalInfoDto.setCar(rentalinfo.getCar());
//			rentalInfoDto.setCarmodel(rentalinfo.getCarmodel());
//			rentalInfoDto.setUserdataDto(userdatadto);
//			System.out.println(rentalinfo.getUserdata().getBirthday());
//			rentalInfoDto.setUserdata(rentalinfo.getUserdata());
//			System.out.println(rentalinfo.getUserdata().getBirthday());
//			rentalInfoDto.setUserdata(rentalInfoDto.getUserdata());
//			AllrentalinfoDto.add(rentalInfoDto);
//
//		}
		
		 CacheControl cacheControl = CacheControl.noCache().noStore().mustRevalidate().sMaxAge(0, TimeUnit.SECONDS);
//		return Allrentalinfo;
		 return ResponseEntity.ok().cacheControl(cacheControl).body(Allrentalinfo);
	}

	@PutMapping("/rentinfo/update")
	@ResponseBody
//	 @ResponseStatus(HttpStatus.BAD_REQUEST) // 设置响应状态码为 400
	public RentalInfo rentalinfoUpdate(@RequestParam int carId, @RequestParam int rentinfoId) {
//		List<RentalInfo> Allrentalinfo = rentalInfoService.findAll();
		System.out.println("null1 ");
		RentalInfo updateCarToRent = rentalInfoService.UpdateCarToRent(carId, rentinfoId);
		   if (updateCarToRent == null) {
			   System.out.println("請求體不能為空null ");
	            // 如果请求体为 null，则返回 400 状态码
			   throw new IllegalArgumentException("請求體不能為空");
//			   throwerror();
	        }
		   System.out.println("狀態變更完成 ");
		return updateCarToRent;
	}
//	 @ResponseStatus(HttpStatus.BAD_REQUEST) // 设置响应状态码为 400
//	public RentalInfo  throwerror() {
//		
//	return null;
//	 }
	
	
	
//	@RequestParam int carId, @RequestParam int rentinfoId
}
