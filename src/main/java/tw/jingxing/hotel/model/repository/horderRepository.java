package tw.jingxing.hotel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jingxing.hotel.model.bean.hOrder;

@Repository
public interface  horderRepository extends JpaRepository<hOrder, Integer> {
	
	 List<hOrder> findByMid(int mid);
    	

}
