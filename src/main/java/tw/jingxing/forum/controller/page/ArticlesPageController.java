package tw.jingxing.forum.controller.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.entity.ArticleTypes;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.forum.model.entity.Forums;
import tw.jingxing.forum.model.service.ArticleTypesService;
import tw.jingxing.forum.model.service.ArticlesService;
import tw.jingxing.forum.model.service.CategoriesService;
import tw.jingxing.forum.model.service.ForumsService;

@Controller
@Transactional
public class ArticlesPageController {

	@Autowired
	private ArticleTypesService articleTypesService;

	@Autowired
	private ArticlesService articlesService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ForumsService forumsService;

	@GetMapping("/articles")
	public String toArticles(@RequestParam Integer forumId, @RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer PageNo) {

		if (categoryId == null) {
			return "forward:/articles/forumId.do";
		}
		return "forward:/articles/categoryId.do";
	}

	@GetMapping("/articles/addarticle")
	public String toAddArticle(@RequestParam Integer forumId, Model model) {
		Forums forum = forumsService.findForumById(forumId);
		List<ArticleTypes> articleTypes = articleTypesService.findAllArticleTypes();
		model.addAttribute("articleTypes", articleTypes);
		model.addAttribute("forum", forum);

		return "/forum/addarticles";
	}

	@GetMapping("/article")
	public String toArticle() {
		return "forward:/article.do";
	}

	@GetMapping("/updatearticle")
	public String toUpdateArticle(@RequestParam Integer articleId, Model model) {

		Articles article = articlesService.findByArticleId(articleId);
		model.addAttribute("Article", article);

		Forums forum = forumsService.findByArticleId(articleId);
		model.addAttribute("forum", forum);

		List<ArticleTypes> articleTypes = articleTypesService.findAllArticleTypes();
		model.addAttribute("articleTypes", articleTypes);

		return "/forum/updatearticles";
	}

	/*--------------------後台-------------------------*/

	
	@GetMapping("/back/updatearticle")
	public String toUpdateArticleBack(@RequestParam Integer articleId, Model model) {

		Articles article = articlesService.findByArticleId(articleId);
		model.addAttribute("Article", article);

		Forums forum = forumsService.findByArticleId(articleId);
		model.addAttribute("forum", forum);

		List<ArticleTypes> articleTypes = articleTypesService.findAllArticleTypes();
		model.addAttribute("articleTypes", articleTypes);

		return "/forum/back/updatearticlesback";
	}

	
	@GetMapping("/back/article")
	public String toArticleBack() {
		return "forward:/back/article.do";
	}
}
