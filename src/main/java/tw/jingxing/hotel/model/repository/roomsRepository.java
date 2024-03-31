package tw.jingxing.hotel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.jingxing.hotel.model.bean.rooms;

@Repository
public interface roomsRepository extends JpaRepository<rooms,Integer> {

 public	List<rooms> findByRoomType(int roomType);
 
 
 @EntityGraph(attributePaths = {"hotel"})
 void deleteById(Integer id) ;

 List<rooms> findByHotelHotelId(int hotelId);

 @Modifying
 @Query("UPDATE rooms r SET r.roomsNumber = r.roomsNumber - :bookedRooms WHERE r.roomId = :roomId")
 void updateRoomsNumber(Integer bookedRooms, Integer roomId);
 
}
