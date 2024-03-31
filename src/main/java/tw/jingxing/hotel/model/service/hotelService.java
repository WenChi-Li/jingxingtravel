package tw.jingxing.hotel.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.hotel.model.bean.hotel;
import tw.jingxing.hotel.model.repository.hotelRepository;
import tw.jingxing.hotel.model.repository.roomsRepository;

@Service @Transactional
public class hotelService {
    @Autowired
    private hotelRepository hotelRepository;
    
    @Autowired
    private roomsRepository roomsRepository;

    public hotel createHotel(hotel hotel){
        return hotelRepository.save(hotel);
    }

    public hotel getHotelbyId(int id){
        return hotelRepository.findById(id).orElse(null);
    }
    
    public List<hotel> getHotelbyAear(String area) {
    	return hotelRepository.findByArea(area);
    }
    public List<hotel> getHotelbyNameLike(String hotelName){
    	return hotelRepository.findByhotelNameContaining(hotelName);
    }
    public List<hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public  hotel updateHotel(hotel hotel, int id) {  // Using int for Hotel ID
        hotel existingHotel = hotelRepository.findById(id).orElse(null);
        if (existingHotel != null) {
            existingHotel.setHotelName(hotel.getHotelName());  // Using appropriate setter methods
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setPhone(hotel.getPhone());
            existingHotel.setArea(hotel.getArea());
            existingHotel.setSwimmingpool(hotel.isSwimmingpool());
            existingHotel.setBreakfast(hotel.isBreakfast());
            existingHotel.setTub(hotel.isTub());
            existingHotel.setGym(hotel.isGym());
            existingHotel.setParking(hotel.isParking());
            existingHotel.setHotelImage(hotel.getHotelImage());
            return hotelRepository.save(existingHotel);
        } else {
            return  null;
        }
    }
    public String deleteHotel(int id){
        hotelRepository.deleteById(id);
        return "Hotel Deleted";
    }
    public List<hotel> getHotelbyRoomsRoomType(int roomtype){
    	return hotelRepository.findByRoomsRoomType(roomtype);
    }
}
