package tw.jingxing.hotel.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.hotel.model.bean.hotel;
import tw.jingxing.hotel.model.bean.rooms;
import tw.jingxing.hotel.model.repository.roomsRepository;

@Service @Transactional
public class roomService {

	@Autowired
	hotelService hotelService;
	@Autowired
	roomsRepository roomsRepository;
	
	 public List<rooms> getAllRooms() {
	        return roomsRepository.findAll();
	    }
	 public rooms getRoomsbyId(int id){
		 return roomsRepository.findById(id).orElse(null);
	 }
	 public List<rooms> getRoomsbyRoomType(int roomType) {
		 return roomsRepository.findByRoomType(roomType);
	 }
	 
	 public List<rooms> getRoomsbyHotelId(int hotelId){
		 return roomsRepository.findByHotelHotelId(hotelId);
	 }
	
	 public rooms createRooms(rooms rooms, int hotelId) {
		 hotel hotel=hotelService.getHotelbyId(hotelId);
		 rooms.setHotel(hotel);
		 roomsRepository.save(rooms);
		 return rooms;
	 }
	 public String deleteRoom(int id) {
		 try {
		        roomsRepository.deleteById(id);
		        return "Room deleted successfully";
		    } catch (Exception e) {
		        return "Error deleting room: " + e.getMessage();
		    }
}
	 public rooms updateRoom(rooms newroom,int id,int hotelId) {
		 hotel hotel=hotelService.getHotelbyId(hotelId);
		 
		 newroom.setRoomId(id);
		 newroom.setHotel(hotel);
		
		 return roomsRepository.save(newroom);
	 }
}