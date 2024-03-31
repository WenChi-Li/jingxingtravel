package tw.jingxing.hotel.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import tw.jingxing.hotel.model.bean.CarItem;
import tw.jingxing.hotel.model.bean.hOrder;
import tw.jingxing.hotel.model.bean.orderItem;
import tw.jingxing.hotel.model.service.horderService;
import tw.jingxing.hotel.model.service.orderItemService;
import tw.jingxing.hotel.model.service.roomService;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.service.MemberService;

@Controller

public class ordersprocess {

	@Autowired
    horderService hOrderService;
	@Autowired
    MemberService memberService;
	@Autowired
	orderItemService orderItemService;
	@Autowired
	roomService roomService;
    
	@PostMapping("/ordersProcess")
	protected String ordersProcess(Model model,
            @RequestParam("mid") int mid,
            @RequestParam("userName") String userName,          
            HttpSession session,
            SessionStatus status) {
        // 從 session 中取得購物車和 totalPrice
        Map<Integer, CarItem> car = (Map<Integer, CarItem>) session.getAttribute("car");
        

        // 從 session 中取得登錄的使用者
        Optional<UserData> member = memberService.getUserById(mid);
        if (member == null) {
            // 如果使用者未登錄，重定向到登錄頁面
            return "redirect:/login";
        }

  
       
        // 取得當前日期
        Date today = new Date();
        
        
        
        //計算總價
        
    	
    		double totalPrice = 0 ;
    		Set<Integer> set = car.keySet();
    		for(Integer n : set){
    			CarItem carid = car.get(n);
    			double price    = carid.getRooms().getPrice();
    			int qty      = carid.getOrderNum();
    			long statday = carid.getNumberOfDays();
    			int roomId=carid.getRooms().getRoomId();
    			totalPrice +=  price * statday * qty;
    		}
    		
    	
        // 創建訂單
        hOrder hOrder = new hOrder();
//        hOrder.setMid(memberId);
        hOrder.setMid(mid);
        
        hOrder.setTotalPrice(totalPrice);
        hOrder.setOrderDate(today);
         Set<orderItem> orderlist = new HashSet<>();
        for(Integer n : set){
            CarItem carItem = car.get(n);
            orderItem orderItem = new orderItem();
            orderItem.setRooms(carItem.getRooms());
            orderItem.setCheckinDate(carItem.getStartDate());
            orderItem.setCheckoutDate(carItem.getEndDate());
            orderItem.setBookedRooms(carItem.getOrderNum());
            orderItem.setPrice(carItem.getRooms().getPrice());
            orderItem.setStayDays(carItem.getNumberOfDays());
            // 保存 orderItem 到資料庫中
          orderlist.add(orderItem);  
          orderItem.sethOrder(hOrder);
          orderItemService.updateRoomsNumber(carItem.getOrderNum(),carItem.getRooms().getRoomId());
        }
        hOrder.setItems(orderlist);
        // 儲存訂單到資料庫
        hOrderService.save(hOrder);
        
        // 清除 session 中的購物車和 totalPrice
        session.removeAttribute("car");
       

        // 標記 session 為完成狀態
        status.setComplete();

        // 重定向到訂單完成頁面或其他適合的頁面
        return "hotel/orderComplete";
    }
}
