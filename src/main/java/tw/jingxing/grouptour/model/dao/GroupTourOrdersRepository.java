package tw.jingxing.grouptour.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jingxing.grouptour.model.bean.GroupTourOrdersBean;



@Repository
public interface GroupTourOrdersRepository extends JpaRepository<GroupTourOrdersBean, Integer> {


	
}
