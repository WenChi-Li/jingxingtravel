package tw.jingxing.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.jingxing.hotel.model.bean.rooms;
import tw.jingxing.hotel.model.service.hotelService;
import tw.jingxing.hotel.model.service.roomService;

@Controller
@RequestMapping("/rooms")
public class roomsController {
	
	@Autowired
	hotelService hotelService;
	
	@Autowired
private roomService roomService;
	 @GetMapping
	    public List<rooms> getAllRooms(){
	        return roomService.getAllRooms();
	    }
	 @GetMapping("/a/{id}")
	  public rooms getRoomsbyId(@PathVariable int id) {
		  return roomService.getRoomsbyId(id);
	  }
	 @GetMapping("/b/{roomType}")
	 public List<rooms> getRoomsbyRoomType(@PathVariable int roomType){
		 return roomService.getRoomsbyRoomType(roomType);
	 }
	 @GetMapping("/viewRooms/{hotelId}")
	 public String  getRoomsbyHotelId(@PathVariable int hotelId,Model model){
	List<rooms> rooms= roomService.getRoomsbyHotelId(hotelId);
	model.addAttribute("hotelId",hotelId);
	model.addAttribute("rooms",rooms);
	return "hotel/viewHotel";
	 }
	 
	 @PostMapping("/addNewRoom/{hotelId}")
	 public String createRooms(@ModelAttribute rooms rooms, @RequestParam int hotelId) {
	     roomService.createRooms(rooms, hotelId);
	    
	     return "redirect:/rooms/viewRooms/" + hotelId;
	 }



	 @GetMapping("/delete/{id}")
	 public String deleteRoom(@PathVariable int id,@RequestParam int hotelId) {
		 roomService.deleteRoom(id);
		return "redirect:/rooms/viewRooms/" + hotelId;
	 }
	 @PostMapping("/update/{id}")
	 public String updateRoom(@ModelAttribute rooms newroom, @PathVariable int id, @RequestParam int hotelId) {
	     roomService.updateRoom(newroom, id, hotelId);
	     return "redirect:/rooms/viewRooms/" + hotelId;
	 }
	 
	 
}
