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

import tw.jingxing.hotel.model.bean.hotel;
import tw.jingxing.hotel.model.service.hotelService;

@Controller
@RequestMapping("/hotel")
public class hotelController {
    @Autowired
    private hotelService hotelService;
    
    

    @GetMapping
    public String getAllHotels(Model model){
        List<hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotel/findAllHotels"; // Assuming you have a view named hotelList.jsp
    }

    @GetMapping("/a/{id}")
    public hotel getHotelbyId(@PathVariable int id){
        return hotelService.getHotelbyId(id);
    }
    
    @GetMapping("/b/{area}")
    public List<hotel> getHotelbyArea(@PathVariable String area) {
    	return hotelService.getHotelbyAear(area);
    }
    
    @GetMapping("/c/{hotelName}")
    public List<hotel> getHotelbyNameLike(@PathVariable String hotelName){
    	return hotelService.getHotelbyNameLike(hotelName);
    }
    @PostMapping("/create")
    public String  createHotel(@ModelAttribute hotel hotel){
       hotelService.createHotel(hotel);
      
        return "redirect:/hotel";
      }
    
    @GetMapping("/delete/{id}")  // 添加 DeleteMapping 註解
    public String deleteHotel(@PathVariable int id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotel";
    }
    @PostMapping("/update/{id}") // Adding PostMapping annotation is recommended
    public String updateHotel(@ModelAttribute hotel hotel, @PathVariable int id, Model model) {
      hotelService.updateHotel(hotel, id);
      model.addAttribute("message", "Update OK"); // Added message for view
      return "redirect:/hotel"; // Assuming you have a view named updateResult.jsp
    }
    @PostMapping("/search")
    public String search(@RequestParam(required = false) String hotelName,
                         @RequestParam(required = false) Integer roomType,
                         @RequestParam(required = false) String area, Model model) {
         System.out.println(hotelName);
         System.out.println(area);
         System.out.println(roomType);
        if (hotelName != null && !hotelName.trim().isEmpty()) {
            model.addAttribute("hotels", hotelService.getHotelbyNameLike(hotelName));
            System.out.println(hotelName);
        } else if (area != null && !area.trim().isEmpty()) {
            model.addAttribute("hotels", hotelService.getHotelbyAear(area));
            System.out.println(area);
        } else if (roomType != null) {
            System.out.println(roomType);
            model.addAttribute("hotels", hotelService.getHotelbyRoomsRoomType(roomType));
        } else {
            System.out.println("4");
        }
        return "hotel/index";
    }

   
}
