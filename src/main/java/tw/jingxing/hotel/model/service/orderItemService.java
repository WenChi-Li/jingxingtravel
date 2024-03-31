package tw.jingxing.hotel.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.hotel.model.bean.orderItem;
import tw.jingxing.hotel.model.repository.orderItemRepository;
import tw.jingxing.hotel.model.repository.roomsRepository;

@Service@Transactional
public class orderItemService {
	@Autowired
	orderItemRepository orderItemRepository;
	@Autowired
	 roomsRepository roomsRepository;
	
    
    public void updateRoomsNumber(Integer bookedRooms, Integer roomId) {
        roomsRepository.updateRoomsNumber(bookedRooms, roomId);
    }
    public List<orderItem> findByHOrderId(int orderId) {
        return orderItemRepository.findByhOrderOrderid(orderId);
    }
    

}
