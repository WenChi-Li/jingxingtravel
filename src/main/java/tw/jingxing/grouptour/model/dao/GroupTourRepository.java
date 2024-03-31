package tw.jingxing.grouptour.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jingxing.grouptour.model.bean.GroupTourBean;



@Repository
public interface GroupTourRepository extends JpaRepository<GroupTourBean, Integer> {

//	前台 - 查詢名稱
	Optional<GroupTourBean> findByItineraryName(String ItineraryName);
	
//	後台 - 查詢行程代碼
	Optional<GroupTourBean> findByitineraryCode(String ItineraryCode);
	
//	Optional<GroupTourBean> deleteByGroupTourID(Integer GroupTourID);
	
	
}
