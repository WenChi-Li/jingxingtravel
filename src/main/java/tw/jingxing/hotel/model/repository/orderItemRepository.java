package tw.jingxing.hotel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.jingxing.hotel.model.bean.orderItem;
@Repository
public interface orderItemRepository extends JpaRepository<orderItem, Integer> {

	@Modifying
	@Query("UPDATE rooms SET roomsNumber = roomsNumber - :bookedroom WHERE roomId = :roomId")
	void updateroomsNumber(Integer bookedroom, Integer roomId);
	List<orderItem> findByhOrderOrderid(int orderId);

}
