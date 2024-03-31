package tw.jingxing.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.jingxing.forum.model.entity.Categories;
import tw.jingxing.forum.model.entity.Forums;
import tw.jingxing.forum.model.service.CategoriesService;
import tw.jingxing.forum.model.service.ForumsService;

@Controller
public class CategoriesApiController {

	@Autowired
	CategoriesService categoriesService;

	@Autowired
	ForumsService forumsService;
	/*--------------------前台-----------------------------------------*/
	

	


	/*--------------------後台-----------------------------------------*/
	
	@GetMapping("/categories/query")
	public String queryByCategoryNameLikeAndForums(@RequestParam String queryString, @RequestParam Integer forumId,
			Model model) {
		
		if (queryString.trim() == null || queryString.trim() == "") {
			return "redirect:/back/categories?forumId=" + forumId;
			
		} else {
			List<Categories> queryrs = categoriesService.findByCategoryNameLikeAndForums(queryString, forumId);
			
			Forums forum = forumsService.findForumById(forumId);
			forum.setCategories(queryrs);
			model.addAttribute("forum", forum);
			
			if (queryrs.isEmpty()) {
				model.addAttribute("querymsg", "查無資料");
			} else {
				model.addAttribute("querymsg", "共" + queryrs.size() + "筆資料");
			}
		}
		
		return "/forum/back/categoriesback";
	}
	
	@DeleteMapping("/categories/{categoryId}")
	@ResponseBody
	public String deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {
		boolean result = categoriesService.deleteCategoriesById(categoryId);
		if (result) {
			return "Delete OK";
		}
		return "Delete Fail";
	}
	
	
	@PostMapping("/categories/update")
	public String updateCategory(@RequestParam String categoryName, @RequestParam Integer categoryId,
			@RequestParam Integer forumId) {
		
		Forums forum = forumsService.findForumById(forumId);
		Categories category = new Categories(categoryId,categoryName,forum);
		categoriesService.updateCategory(category);
		
		return "redirect:/back/categories?forumId=" + forumId;
	}
	
	
	@PostMapping("/categories")
	public String insertCategory(@RequestParam String categoryName, @RequestParam Integer forumId) {

		Forums forums = forumsService.findForumById(forumId);
		Categories categories = new Categories(categoryName, forums);
		categoriesService.insertForumsCategories(categories);

		return "redirect:/back/categories?forumId=" + forumId;
	}

}
