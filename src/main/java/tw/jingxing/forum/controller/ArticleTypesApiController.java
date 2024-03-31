package tw.jingxing.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.jingxing.forum.model.entity.ArticleTypes;
import tw.jingxing.forum.model.service.ArticleTypesService;

@Controller
public class ArticleTypesApiController {

	@Autowired
	private ArticleTypesService articleTypesService;
	
	@PostMapping("/back/articletypes")
	public String insertArticleTypes(@RequestParam String articleTypeName) {
		articleTypesService.inserArticleTypes(new ArticleTypes(articleTypeName)); 
		return "redirect:/back/forums";
		
	}
}
