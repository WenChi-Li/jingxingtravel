package tw.jingxing.hotel.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.jingxing.hotel.model.bean.hOrder;
import tw.jingxing.hotel.model.bean.orderItem;
import tw.jingxing.hotel.model.service.horderService;
import tw.jingxing.hotel.model.service.orderItemService;

@Controller
@RequestMapping("/hotelOrder")
public class selectOrderController {
	
         @Autowired
	     horderService horderService;
         @Autowired
         orderItemService orderItemService;

	    @GetMapping("/all")
	    public String getAllhOrders(Model model) {
	        List<hOrder> allOrders = horderService.getAllhOrders();
	        model.addAttribute("allOrders", allOrders);
	        return "hotel/findAllOrder"; // 返回顯示所有訂單的頁面
	    }

	    @GetMapping("/MemberOrder/{mid}")
	    public String findByMid(@PathVariable int mid ,Model model) {
	        List<hOrder> ordersByMemberId = horderService.findByMid(mid);
	        model.addAttribute("ordersByMemberId", ordersByMemberId);
	        return "hotel/ordersByMemberId"; // 返回顯示特定會員訂單的頁面
	    }
	    @GetMapping("/viewOrder/{orderid}")
	    public String findById(@PathVariable int orderid,Model model) {
	    	List<orderItem> orderItem=orderItemService.findByHOrderId(orderid);
	    
	    		    	model.addAttribute("orderItem",orderItem);
	    		    	
	    		return	"hotel/orderItemByoid";
	    	
	    }
	    @GetMapping("/viewuserOrder/{orderid}")
	    public String findByoId(@PathVariable int orderid,Model model) {
	    	List<orderItem> orderItem=orderItemService.findByHOrderId(orderid);
	    
	    		    	model.addAttribute("orderItem",orderItem);
	    		    	
	    		return	"hotel/memberByoid";
	    	
	    }
}

