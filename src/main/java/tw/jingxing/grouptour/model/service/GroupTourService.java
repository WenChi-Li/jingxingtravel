package tw.jingxing.grouptour.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.jingxing.grouptour.model.bean.GroupTourBean;
import tw.jingxing.grouptour.model.dao.GroupTourRepository;

@Service
@Transactional
public class GroupTourService {
	
	@Autowired
	private GroupTourRepository groupTourRepository;
	
//	後台新增資料
	public GroupTourBean Insert(GroupTourBean T ) throws IOException {
		Optional<GroupTourBean> GroupTourName = 
				groupTourRepository.findByItineraryName(T.getItineraryName());
		if(!GroupTourName.isPresent()) {
			return groupTourRepository.save(T);			
		}else {
			throw new RuntimeException("此行程名稱已存在，無法新增");
		}
	}
	 
//	後台修改資料(Id)
	public GroupTourBean updateById(GroupTourBean T) {
		Optional<GroupTourBean> GroupTourId = 
				groupTourRepository.findById(T.getGroupTourID());
		if(GroupTourId.isPresent()) {
			return groupTourRepository.save(T);			
		}else {	
			throw new RuntimeException("此行程id不存在，請新增資料");
		}		
	}
	
//	後台刪除資料(Id)
	public void  deleteById(Integer id) {
		Optional<GroupTourBean> GroupTourId = groupTourRepository.findById(id);	
		if (GroupTourId.isPresent()) {
			groupTourRepository.deleteById(id);	
		}else {
			throw new RuntimeException("此行程id不存在，無法刪除");
		}
	}
	
	
// 後台查詢旅行社名子
	public GroupTourBean findById(Integer id) {
		Optional<GroupTourBean> GroupTourId = groupTourRepository.findById(id);
		return GroupTourId.orElse(null);
	}
	
// 後台查詢旅行社名子
	public GroupTourBean findByName(GroupTourBean T) {
		Optional<GroupTourBean> GroupTourName = groupTourRepository.findByItineraryName(T.getItineraryName());
		return GroupTourName.orElse(null);
	}
	
//	後台查詢旅行社代碼
	public GroupTourBean findByCode(GroupTourBean T) {
		Optional<GroupTourBean> GroupTourCode = groupTourRepository.findByitineraryCode(T.getItineraryCode());
		return GroupTourCode.orElse(null);
	}
	
// 查詢全部
	public List<GroupTourBean> findAll(){
		return groupTourRepository.findAll();
	}
}
