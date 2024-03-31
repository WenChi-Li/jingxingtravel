package tw.jingxing.rentcar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.jingxing.member.model.dao.UserDataRepository;
import tw.jingxing.rentcar.dao.CarModelRepository;
import tw.jingxing.rentcar.dao.CarRepository;
import tw.jingxing.rentcar.dao.RentalInfoRepository;
import tw.jingxing.rentcar.model.Car;
import tw.jingxing.rentcar.model.RentalInfo;

@Service
@Transactional
public class RentalInfoService {

	@Autowired
	private CarModelRepository carModelRepository;

	@Autowired
	private RentalInfoRepository rentalInfoRepository;

	@Autowired
	private UserDataRepository userDataRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarService carservice;

	public RentalInfo insert(RentalInfo rentalInfo) {

		return rentalInfoRepository.save(rentalInfo);
	}

	public List<RentalInfo> findAll() {
		List<RentalInfo> rentalinfofindAll = rentalInfoRepository.findAll();
		for (RentalInfo rentalinfo : rentalinfofindAll) {

			LocalDateTime pickupDateTime = rentalinfo.getPickupDateTime();
			LocalDateTime returnDateTime = rentalinfo.getReturnDateTime();

			// 假设你的datetime数据是从数据库或其他地方获取的
			LocalDateTime dateTime = LocalDateTime.parse("2024-03-25T10:00:00.000");

			// 定义日期时间格式化器，只包含小时和分钟
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			// 格式化日期时间
			String formattedPickDateTime = pickupDateTime.format(formatter);
			String formattedReturnDateTime = returnDateTime.format(formatter);

			// 输出结果
			System.out.println("取車格式化后的时间: " + formattedPickDateTime);
			System.out.println("還車格式化后的时间: " + formattedReturnDateTime);

			rentalinfo.setFormattedPickUPDateTime(formattedPickDateTime);
			rentalinfo.setFormattedReturnDateTime(formattedReturnDateTime);

//			System.out.println("用mid查詢");
//			UserData findByMiddata = userDataRepository.findByMid(rentalinfo.getUserdata().getMid());
//			System.out.println(findByMiddata.getMid());
//			rentalinfo.setUserdata(findByMiddata);
		}

//		carModelRepository.findById()
		return rentalinfofindAll;
	}

	public List<Object[]> getRentalInfoDetailsByUserId(int mid) {
		return rentalInfoRepository.findRentalInfoDetailsByMid(mid);
	}

	public RentalInfo UpdateCarToRent(int carid, int rentinfoId) {

		Car CarDataById = carservice.findByIdUpdate(carid);
		Optional<RentalInfo> RentInfofindById = rentalInfoRepository.findById(rentinfoId);
//	 Optional<Car> cardabase = carRepository.findById(carid);
//	 Car car =null;
//	 if(cardabase.isPresent()) {
//		 car = cardabase.get();
//	 }
//	 
		RentalInfo rentalInfo = null;
		if (RentInfofindById.isPresent()) {
			rentalInfo = RentInfofindById.get();

			if (CarDataById.getStatus().equals("可用的")) {
				System.out.println("可用的車");
				// 如果车辆状态为“可用”，则更新为“已出租”
				rentalInfo.setCar(CarDataById);
				rentalInfoRepository.save(rentalInfo);
				// 更新车辆状态为“已出租”
				CarDataById.setStatus("已出租");
				System.out.println("狀態準備變更");
				carRepository.save(CarDataById); // 保存更新后的车辆信息
				
			} else {

				System.out.println("車輛狀態已出租，無法出租");
				// 在此处可以返回适当的信息，例如告知用户车辆不可用
				return null;
			}

//			rentalInfo.setCar(CarDataById);
//			rentalInfoRepository.save(rentalInfo);

		} else {
			System.out.println("找不到此筆租車資訊");
			return null;
		}

		return rentalInfo;
	}

}
