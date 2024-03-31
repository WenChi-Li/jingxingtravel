package tw.jingxing.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import tw.jingxing.hotel.model.service.hotelService;
import tw.jingxing.hotel.model.service.roomService;

@Controller
public class hotelspathController {
	@Autowired
	hotelService hotelService;
	
	@Autowired
	roomService roomService;
	
	@GetMapping("/findAllHotels")
	public String gofindAllHotel() {
		return "forward:hotel";
	}
	
	@GetMapping("/modifyHotel/{hotelId}")
	public String gomodifyHotel(@PathVariable int hotelId, Model model) {
		 model.addAttribute("hotel",hotelService.getHotelbyId(hotelId));
		return "hotel/modifyHotel";
	}
	@GetMapping("/addNewHotel")
	public String goaddNewHotel() {
		return "hotel/addNewHotel";
}
	@GetMapping("/viewHotel/{hotelId}")
	public String goviewHotel(@PathVariable int hotelId) {
		return "/viewRooms/{hotelId}";
	}
	@GetMapping("/modifyRoom/{roomId}")
	public String gomodifyRooms(@PathVariable int roomId, @RequestParam int hotelId, Model model) {
	    model.addAttribute("room", roomService.getRoomsbyId(roomId));
	    model.addAttribute("hotelId", hotelId);
	    return "hotel/modifyRoom";
	}
	@GetMapping("/addRoom/{hotelId}")
	public String goaddNewRoom(@PathVariable int hotelId) {
		return "hotel/addNewRoom";
	}
	@GetMapping("/searchpage")
	public String goserchpage() {
		return "hotel/searchpage";
	}
	@GetMapping("/roomscheak/{hotelId}")
	public String gohotelrooms(@PathVariable int hotelId,Model model) {
		model.addAttribute("hotel",hotelService.getHotelbyId(hotelId));
		model.addAttribute("rooms",roomService.getRoomsbyHotelId(hotelId));
		return "hotel/roomscheak";
	}
	@GetMapping("/Allorder")
	public String gohotelorder(){
		return"forward:hotelOrder/all";
	}
	@GetMapping("/viewUserO/{mid}")
	public String goviewOrder(@PathVariable int mid) {
		return "redirect:/hotelOrder/MemberOrder/"+mid;
	}
	}
