package tw.jingxing.hotel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jingxing.hotel.model.bean.hotel;


@Repository
public interface hotelRepository extends JpaRepository<hotel, Integer> {
	List<hotel> findByArea(String area);
	List<hotel> findByhotelNameContaining(String hotelName);
	List<hotel> findByRoomsRoomType(int roomtype);
}