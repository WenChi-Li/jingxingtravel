package tw.jingxing.grouptour.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.transaction.Transactional;
import tw.jingxing.grouptour.model.bean.GroupTourBean;
import tw.jingxing.grouptour.model.bean.GroupTourOrdersBean;
import tw.jingxing.grouptour.model.dao.GroupTourRepository;
import tw.jingxing.grouptour.model.dto.GroupTourOrdersUpdateDto;
import tw.jingxing.grouptour.model.service.GroupTourOrdersService;

	@Controller
	@CrossOrigin(origins = "http://localhost:8080")
	@Transactional
	public class GroupTourOrdersController {
	
		@Autowired
		private GroupTourOrdersService groupTourOrdersService;
		
		@Autowired
		private GroupTourRepository groupTourRepository;
				
		
	//	後台新增
		@PostMapping("/GroupTourOrdersInsert.controller")
		@ResponseBody
		public RedirectView processInsert(@ModelAttribute GroupTourOrdersUpdateDto dto ) {
			try {
				System.out.println(dto);
				GroupTourOrdersBean bean = new GroupTourOrdersBean();
				
				Optional<GroupTourBean> findByGroupTourID = 
						groupTourRepository.findById(dto.getGroupTourID());
				
				if(findByGroupTourID.isPresent()) {
					bean.setGroupTourBean(findByGroupTourID.get());
					bean.setCustomerName(dto.getCustomerName());				
					bean.setCustomerPhone(dto.getCustomerPhone());				
					bean.setCustomerEmail(dto.getCustomerEmail());				
					bean.setQuantity(dto.getQuantity());				
				}
						GroupTourOrdersBean insert = groupTourOrdersService.Insert(bean);
					//	寄信
					 String to = dto.getCustomerEmail();
					 System.out.println(to);
				        String subject = "JingXing Travel 報名表";
				        String text = "<p>Dear " + dto.getCustomerName() + ",</p><br>" +
				                      "<p>感謝您報名行程說明會，您的說明會訊息如下"+
				                      "<p>行程 ID: " + dto.getGroupTourID() + "</p>" +
				                      "<p>行程名稱: " + findByGroupTourID.get().getItineraryName() + "</p>" +				                      
				                      "<p>人數: " + dto.getQuantity() + "</p>" +
				                      "<p>會議時間 : 2024/04/02</p>" +				                      
				                      "<p>會議連結:</p>"+
				                      "<p>您可以點擊 <a href='http://localhost:8080/html/grouptour/ShowUserRegistration.html?groupTourOrdersID="
				                      	+ insert.getGroupTourOrdersID() + "'>這裡</a> 查看您報名的行程</p>"+
				                      "<p>如果您有任何問題，請隨時與我們聯繫</p>" +
				                      "<p></p>"+
				                      "<p>此致，</p>" +
				                      "<p><img src='https://drive.google.com/uc?export=view&id=1cflkoNcDPnCQqvgwe2S-_O96prGliSBi' width='300' height='200' /> TM™ JingXing Travel</p>"+
				                      "<br>"+
									  "<p>Dear " + dto.getCustomerName() + ",</p><br>" +
									  "<p>Thank you for registering for the briefing session. Below are the details of your briefing:</p>"+
									  "<p>Itinerary ID: " + dto.getGroupTourID() + "</p>" +
									  "<p>Itinerary Name: " + findByGroupTourID.get().getItineraryName() + "</p>" +				                      
									  "<p>Number of participants: " + dto.getQuantity() + "</p>" +
									  "<p>Meeting link:</p>"+
									  "<p>If you have any questions, feel free to contact us.</p>" +
									  "<p>Best regards,</p>" +  
									  "<p><img src='https://drive.google.com/uc?export=view&id=1cflkoNcDPnCQqvgwe2S-_O96prGliSBi' width='300' height='200' /> TM™ JingXing Travel</p>";
				        
				        groupTourOrdersService.sendSimpleMessage(to, subject, text);				        
				    System.out.println(findByGroupTourID.get().getItineraryName());
					System.out.println(subject);
					System.out.println(text);
					System.out.println(dto.getGroupTourOrdersID());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return new RedirectView("http://localhost:8080/html/grouptour/OrderSuccessful.html");
		}

//	後台修改
	@PostMapping("/GroupTourOrdersUpdateId.controller")
	@ResponseBody
	public ResponseEntity<String> processUpdate(@ModelAttribute GroupTourOrdersUpdateDto dto) {

		System.out.println(dto);
		GroupTourOrdersBean bean = new GroupTourOrdersBean();
		
		Optional<GroupTourBean> findByGroupTourID = 
				groupTourRepository.findById(dto.getGroupTourID());

		if(findByGroupTourID.isPresent()) {			
			bean.setGroupTourBean(findByGroupTourID.get());
			bean.setGroupTourOrdersID(dto.getGroupTourOrdersID());
			bean.setCustomerName(dto.getCustomerName());				
			bean.setCustomerPhone(dto.getCustomerPhone());				
			bean.setCustomerEmail(dto.getCustomerEmail());				
			bean.setQuantity(dto.getQuantity());		
		}		
		groupTourOrdersService.updateById(bean);

		return new ResponseEntity<>("Y", HttpStatus.OK);
	}

//	後台刪除
	@DeleteMapping("/GroupTourOrdersDelete.controller/{groupTourOrdersID}")
	@ResponseBody
	public ResponseEntity<String> processDelete(@PathVariable int groupTourOrdersID) {
		try {
			groupTourOrdersService.deleteById(groupTourOrdersID);
			return new ResponseEntity<>("Y", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("N", HttpStatus.OK);
		}
	}
	
	
//  後台查尋訂單細項id
	@GetMapping("/GroupTourOrdersSelectId.controller/{groupTourOrdersID}")
	@ResponseBody
	public ResponseEntity<?> processSelectId(@PathVariable int groupTourOrdersID) {
		try {
			GroupTourOrdersBean beanList = 
					groupTourOrdersService.findById(groupTourOrdersID);
			System.out.println(groupTourOrdersService.findById(groupTourOrdersID));
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("查不到此訂單id", HttpStatus.OK);
		}
	}
	
//	查詢全部
	@GetMapping("/GroupTourOrdersSelectAll.controller")
	@ResponseBody
//	<?>裡也能寫List<TravelAgencyBean>
	public ResponseEntity<?> processSelectAll() {
		try {
			List<GroupTourOrdersBean> beanList = groupTourOrdersService.findAll();
			System.out.println(groupTourOrdersService.findAll());
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("N", HttpStatus.OK);
		}
	}
	
	
	
	
	
}
