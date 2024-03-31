package tw.jingxing.forum.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.jingxing.forum.model.entity.Forums;
import tw.jingxing.forum.model.service.CategoriesService;
import tw.jingxing.forum.model.service.ForumsService;

@Controller
public class CategoriesPageController {

	@Autowired
	CategoriesService categoriesService;
	
	@Autowired
	ForumsService forumsService;
	
	@GetMapping("/back/categories")
	public String toForum(@RequestParam Integer forumId,Model model) {
		
		Forums forum = forumsService.findForumById(forumId);
		
		model.addAttribute("forum", forum);
		
		if (forum.getCategories().isEmpty()) {
			model.addAttribute("querymsg", "查無資料");
		} else {
			model.addAttribute("querymsg", "共"+forum.getCategories().size()+"筆資料");			
		}
		
		return "/forum/back/categoriesback";
	}
	
}
