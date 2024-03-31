package tw.jingxing.grouptour.controller;

import java.io.IOException;
import java.util.List;

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
import tw.jingxing.grouptour.model.dto.GroupTourCreateDto;
import tw.jingxing.grouptour.model.dto.GroupTourUpdateDto;
import tw.jingxing.grouptour.model.service.GroupTourService;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@Transactional
public class GroupTourController {

	@Autowired
	private GroupTourService groupTourService;

//	後台新增
	@PostMapping("/GroupTourInsert.controller")
	@ResponseBody
	public RedirectView processInsert(@ModelAttribute GroupTourCreateDto dto) {
//		try {
			System.out.println(dto);
			GroupTourBean bean = new GroupTourBean();
			bean.setItineraryName(dto.getItineraryName());
			bean.setItineraryImage(dto.getItineraryImageBase64());
			bean.setItineraryCode(dto.getItineraryCode());
			bean.setFeatureImage1(dto.getFeatureImage1Base64());
			bean.setFeatureImage2(dto.getFeatureImage2Base64());
			bean.setFeature1(dto.getFeature1());
			bean.setFeature2(dto.getFeature2());
			bean.setItineraryContent(dto.getItineraryContent());
			bean.setSeatCapacity(dto.getSeatCapacity());
			bean.setDepartureDate(dto.getDepartureDate());
			bean.setDepartureTransport(dto.getDepartureTransport());
			bean.setReturnDate(dto.getReturnDate());
			bean.setReturnTransport(dto.getReturnTransport());
			bean.setPrice(dto.getPrice());
			bean.setNote(dto.getNote());

			System.out.println(dto.getItineraryImageBase64());
			try {
				groupTourService.Insert(bean);
				System.out.println(bean);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			return new ResponseEntity<>("Y", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("N", HttpStatus.OK);
//		}
		return new RedirectView("http://localhost:8080/html/grouptour/ShowGroupTour.html");
	}

//	後台修改
	@PostMapping("/GroupTourUpdateId.controller")
	@ResponseBody
	public RedirectView processUpdate(@ModelAttribute GroupTourUpdateDto dto) {

		System.out.println(dto);
		GroupTourBean bean = new GroupTourBean();
		bean.setGroupTourID(dto.getGroupTourID());
		bean.setItineraryName(dto.getItineraryName());
		bean.setItineraryImage(dto.getItineraryImageBase64());
		bean.setItineraryCode(dto.getItineraryCode());
		bean.setFeatureImage1(dto.getFeatureImage1Base64());
		bean.setFeatureImage2(dto.getFeatureImage2Base64());
		bean.setFeature1(dto.getFeature1());
		bean.setFeature2(dto.getFeature2());
		bean.setItineraryContent(dto.getItineraryContent());
		bean.setSeatCapacity(dto.getSeatCapacity());
		bean.setDepartureDate(dto.getDepartureDate());
		bean.setDepartureTransport(dto.getDepartureTransport());
		bean.setReturnDate(dto.getReturnDate());
		bean.setReturnTransport(dto.getReturnTransport());
		bean.setPrice(dto.getPrice());
		bean.setNote(dto.getNote());

		groupTourService.updateById(bean);

		return new RedirectView("http://localhost:8080/html/grouptour/ShowGroupTour.html");
	}
	
	
	
//	後台刪除
	@DeleteMapping("/GroupTourDelete.controller/{groupTourID}")
	@ResponseBody
	public ResponseEntity<String> processDelete(@PathVariable int groupTourID) {
		try {
			groupTourService.deleteById(groupTourID);
			return new ResponseEntity<>("Y", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("N", HttpStatus.OK);
		}
	}
	
	
//  後台查詢行程id
	@GetMapping("/GroupTourSelectId.controller/{groupTourID}")
	@ResponseBody
	public ResponseEntity<?> processSelectId(@PathVariable int groupTourID) {
		try {
			GroupTourBean beanList = groupTourService.findById(groupTourID);
			System.out.println(groupTourService.findById(groupTourID));
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("查不到此行程id", HttpStatus.OK);
		}
	}
	
	
	
//  後台查詢行程名字
	@GetMapping("/GroupTourSelectName.controller/{itineraryName}")
	@ResponseBody
	public ResponseEntity<?> processSelectName(@PathVariable GroupTourBean itineraryName) {
		try {
			GroupTourBean beanList = groupTourService.findByName(itineraryName);
			System.out.println(groupTourService.findByName(itineraryName));
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("查不到此行程名稱", HttpStatus.OK);
		}
	}

//  後台查詢行程代碼
	@GetMapping("/GroupTourSelectCode.controller/{itineraryCode}")
	@ResponseBody
	public ResponseEntity<?> processSelectCode(@PathVariable GroupTourBean itineraryCode) {
		try {
			GroupTourBean beanList = groupTourService.findByCode(itineraryCode);
			System.out.println(groupTourService.findByCode(itineraryCode));
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("查不到此行程代碼", HttpStatus.OK);
		}
	}

//	查詢全部
	@GetMapping("/GroupTourSelectAll.controller")
	@ResponseBody
//	<?>裡也能寫List<TravelAgencyBean>
	public ResponseEntity<?> processSelectAll() {
		try {
			List<GroupTourBean> beanList = groupTourService.findAll();
			
			
			System.out.println(groupTourService.findAll());
			return new ResponseEntity<>(beanList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("N", HttpStatus.OK);
		}
	}
}
