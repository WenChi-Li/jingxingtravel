package tw.jingxing.hotel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import tw.jingxing.forum.util.DatetimeConverter;
import tw.jingxing.hotel.model.bean.CarItem;
import tw.jingxing.hotel.model.bean.rooms;
import tw.jingxing.hotel.model.service.hotelService;
import tw.jingxing.hotel.model.service.roomService;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.service.MemberService;

@Controller
@RequestMapping("/ShopingCar")

public class shopingcartController {
	@Autowired
	private hotelService hotelService;
	@Autowired
	private roomService roomService;
	@Autowired
	private MemberService memberService;
	

	@GetMapping("/viewCart")
	public String showCar(HttpSession session, Model model) {
		Map<Integer, CarItem> car = getCar(session);
		model.addAttribute("car", car);
		System.out.println(car);
		return "hotel/shopingcart";
	}

	@GetMapping("/showCart")
	@ResponseBody
	public Map<Integer, CarItem> showCartItem(HttpSession session) {
		Map<Integer, CarItem> car = getCar(session);
		return car;
	}
	@GetMapping("/submitCart")
	public String checkout(Model model, HttpSession session, @RequestParam(name = "mid") Integer mid) {
	    // 如果 mid 為 null，則重新導向到登入頁面或執行其他處理
	    if (mid == null) {
	        // 重新導向到登入頁面或其他處理
	        return "redirect:/login"; // 假設登入頁面的路徑是 /login
	    }

	    // 根據 mid 從資料庫中取得 UserData
	    Optional<UserData> memberOptional = memberService.getUserById(mid);

	    // 確認 Optional 是否存在
	    if (memberOptional.isPresent()) {
	        UserData member = memberOptional.get();
	        
	        // 將取得的 UserData 放入 model 中供視圖使用
	        model.addAttribute("member", member);

	        return "hotel/orderConfirm";
	    } else {
	        // 如果根據 mid 找不到相應的 UserData，則返回到某個錯誤頁面或其他處理
	        return "errorPage"; // 假設錯誤頁面的路徑是 errorPage
	    }
	}

	@GetMapping("/clearCart")	
	public String clearCart(HttpSession session) {
		session.removeAttribute("car");
		return "redirect:/ShopingCar/viewCart";
	}

	@GetMapping("/addtocart/{roomId}")
	public String addtoCart(@PathVariable("roomId") Integer roomId,
	                        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
	                        HttpSession session) {
	    Map<Integer, CarItem> car = getCar(session);
	    String roomN = roomService.getRoomsbyId(roomId).getRoomTypeName();

	    int orderNum = 1;
	    if (car.containsKey(roomId)) {
	        orderNum += car.get(roomId).getOrderNum();
	        car.get(roomId).setOrderNum(orderNum);
	    } else {
	        rooms room = roomService.getRoomsbyId(roomId);
	        // 计算入住天数
	        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	        long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	        // 创建 CarItem 对象并设置入住天数
	        CarItem newItem = new CarItem(room, orderNum, DatetimeConverter.toString(startDate, "yyyy-MM-dd"),
	                DatetimeConverter.toString(endDate, "yyyy-MM-dd"), numberOfDays);

	        car.put(roomId, newItem);
	    }
	    session.setAttribute("keyset", car.keySet());
	    session.setAttribute("car", car);
	    System.out.println(car.get(roomId).getRooms().getRoomTypeName());
	    return "redirect:/ShopingCar/viewCart";
	}

	@PostMapping("/modifyDate/{roomId}")
	public String updateCarItem(@PathVariable("roomId") Integer roomId, @RequestParam int orderNum,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,Long numberOfDays, HttpSession session) {
		Map<Integer, CarItem> car = getCar(session);
		rooms room = roomService.getRoomsbyId(roomId);
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
         numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		CarItem newItem = new CarItem(room, orderNum, DatetimeConverter.toString(startDate, "yyyy-MM-dd"),
				DatetimeConverter.toString(endDate, "yyyy-MM-dd"),numberOfDays);
		car.put(roomId, newItem);
		session.setAttribute("car", car);

		return "redirect:/ShopingCar/viewCart";
	}

	@GetMapping("/deleteCartItem/{roomId}")	
	public String deleteCar(@PathVariable("roomId") Integer roomId, HttpSession session) {
		Map<Integer, CarItem> car = getCar(session);
		car.remove(roomId);
		return "redirect:/ShopingCar/viewCart";
	}

	public Map<Integer, CarItem> getCar(HttpSession session) {
		Map<Integer, CarItem> car = (Map<Integer, CarItem>) session.getAttribute("car");
		if (car == null) {
			car = new HashMap<>();
			session.setAttribute("car", car);
		}
		return car;
	}
	

}
