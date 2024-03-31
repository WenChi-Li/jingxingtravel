package tw.jingxing.rentcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.rentcar.model.RentalInfo;
@Repository
public interface RentalInfoRepository extends JpaRepository<RentalInfo, Integer> {


    @Query("SELECT r.pickupDate, r.returnDate, r.totalCost FROM RentalInfo r WHERE r.userdata.mid = :mid")
    List<Object[]> findRentalInfoDetailsByMid(int mid);
	
  
}
