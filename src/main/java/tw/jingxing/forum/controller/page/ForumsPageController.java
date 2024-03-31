package tw.jingxing.forum.controller.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.entity.ArticleTypes;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.forum.model.entity.Categories;
import tw.jingxing.forum.model.entity.Forums;
import tw.jingxing.forum.model.service.ArticleCollectionRecordsService;
import tw.jingxing.forum.model.service.ArticleTypesService;
import tw.jingxing.forum.model.service.ArticlesService;
import tw.jingxing.forum.model.service.CategoriesService;
import tw.jingxing.forum.model.service.ForumsService;
import tw.jingxing.forum.model.service.GlobalForumService;

@Transactional
@Controller
@SessionAttributes({ "sortfieldb", "sortmethodb", "pagesizeb","artsortfieldb", "artsortmethodb", "artpagesizeb" })
public class ForumsPageController {

	@Autowired
	private ForumsService forumsService;
	
	@Autowired
	private ArticleTypesService articleTypesService;
	
	@Autowired
	private ArticlesService articlesService;
	
	@Autowired
	private ArticleCollectionRecordsService articleCollectionRecordsService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	/*----------------------------前台-----------------------------------------------*/
	
	@GetMapping("/forums")
	public String toAllForums() {
		return "forward:forums.do";
		
	}
	
	@GetMapping("/forums/{forumsid}")
	public String toForum(@PathVariable(name = "forumsid")Integer forumsid,Model model) {
		Forums forum = forumsService.findForumById(forumsid);
		model.addAttribute("forum", forum);
		return "forum/forum";
	}
	
	/*-------------------------後台---------------------------------------------------*/
	
	@GetMapping("/back/forums")
	public String toBackForums(@RequestParam(required = false)Integer PageNo,Model model) {
		
		String sortfield = (String) model.getAttribute("sortfieldb");
		String sortmethod = (String) model.getAttribute("sortmethodb");
		Integer pagesize = (Integer) model.getAttribute("pagesizeb");
		
		if (sortmethod == null||sortfield==null||pagesize==null) {
			sortmethod=GlobalForumService.Back_Forum_Sort_Method;
			sortfield=GlobalForumService.Back_Forums_Sort_Field;
			pagesize=GlobalForumService.Back_Forum_Page_Size;
			model.addAttribute("sortmethodb", sortmethod);
			model.addAttribute("sortfieldb", sortfield);
			model.addAttribute("pagesizeb", pagesize);
		}
		
		if (sortfield.equals("pop")) {
			Page<Object[]> results = forumsService.findAllForumsWithPop(PageNo, pagesize, sortfield, sortmethod);

			Map<Integer, Articles> map = new HashMap<>();

			results.forEach(t -> map.put(((Forums) t[0]).getForumId(),
					articlesService.findLatestByForumId(((Forums) t[0]).getForumId())));

			
			
			model.addAttribute("allforums", results);
			model.addAttribute("latestArticle", map);
			model.addAttribute("currentPageNo", results.getNumber() + 1);

			model.addAttribute("querywithpop", true);

			if (results.getTotalElements() == 0) {
				model.addAttribute("querymsg", "查無資料");
			} else {
				model.addAttribute("querymsg", "共" + results.getTotalElements() + "個討論區");
			}
		} else {
			Page<Forums> results = forumsService.findAllForums(PageNo, pagesize, sortfield, sortmethod);
			Map<Integer, Articles> map = new HashMap<>();

			Map<Integer, Integer> popularmap = new HashMap<>();

			results.forEach(t -> map.put(t.getForumId(), articlesService.findLatestByForumId(t.getForumId())));

			results.forEach(t -> popularmap.put(t.getForumId(), t.getAllPopular()));

			model.addAttribute("querywithpop", false);
			model.addAttribute("allforums", results);
			model.addAttribute("latestArticle", map);
			model.addAttribute("allPopular", popularmap);
			model.addAttribute("currentPageNo", results.getNumber() + 1);

			if (results.getTotalElements() == 0) {
				model.addAttribute("querymsg", "查無資料");
			} else {
				model.addAttribute("querymsg", "共" + results.getTotalElements() + "個討論區");
			}
		}
		
		List<ArticleTypes> ArticleTypes = articleTypesService.findAllArticleTypes();
		model.addAttribute("ArticleTypes", ArticleTypes);

		model.addAttribute("systemName", "back/forums");
		
		return "/forum/back/forumsback";
	}
	
	//導到討論區更新頁面
	@GetMapping("/back/updateforums/{forumsId}")
	public String toUpdateForums(@PathVariable Integer forumsId,Model model) {
		Forums forums = forumsService.findForumById(forumsId);
		model.addAttribute("forums", forums);
		return "forum/back/updateforums";
	}
	
	//導到討論區新增頁面
	@GetMapping("/addforums")
	public String toAddForums() {
		return "forum/back/addforums";
	}
	
	
	//導到討論區文章管理系統
	@GetMapping("/back/forums/{forumId}/articles")
	public String toForumback(@PathVariable Integer forumId,@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) Integer PageNo,
			Model model) {
		
		String sortfield = (String) model.getAttribute("artsortfieldb");
		String sortmethod = (String) model.getAttribute("artsortmethodb");
		Integer pagesize = (Integer) model.getAttribute("artpagesizeb");
		
		if (sortmethod == null||sortfield==null||pagesize==null) {
			sortmethod=GlobalForumService.Back_Article_Sort_Method;
			sortfield=GlobalForumService.Back_Articles_Sort_Field;
			pagesize=GlobalForumService.Back_Article_Page_Size;
			model.addAttribute("artsortmethodb", sortmethod);
			model.addAttribute("artsortfieldb", sortfield);
			model.addAttribute("artpagesizeb", pagesize);
		}
		
		if (categoryId==null) {
			
			if (sortfield.equals("like") || sortfield.equals("collection")) {

				Page<Object[]> rs = articlesService.findBySortingByLikeCollect(forumId, PageNo, pagesize, sortfield,
						sortmethod);
				
				List<Integer> likes = new ArrayList<>();

				List<Integer> collections = new ArrayList<>();

				for (Object[] objs : rs) {
					Articles a = (Articles) objs[0];
					likes.add(a.getLikedNum());
					collections.add(a.getCollectionNum());
				}

				model.addAttribute("likeNo", likes);
				model.addAttribute("collectionNo", collections);

				model.addAttribute("Pagination", rs);
				model.addAttribute("currentPageNo", rs.getNumber() + 1);
				model.addAttribute("systemName", "articles");
				model.addAttribute("querywithlikeorcollection", true);
				model.addAttribute("forum", forumsService.findForumById(forumId));
				model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
			} else {

				Page<Articles> rs = articlesService.findByForumId(forumId, PageNo, pagesize, sortfield, sortmethod);

				List<Integer> likes = new ArrayList<>();

				List<Integer> collections = new ArrayList<>();

				for (Articles a : rs) {
					likes.add(a.getLikedNum());
					collections.add(a.getCollectionNum());
				}

				model.addAttribute("likeNo", likes);
				model.addAttribute("collectionNo", collections);
				
				model.addAttribute("Pagination", rs);
				model.addAttribute("currentPageNo", rs.getNumber() + 1);
				model.addAttribute("systemName", "articles");
				model.addAttribute("querywithlikeorcollection", false);
				model.addAttribute("forum", forumsService.findForumById(forumId));
				model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
			}
			
			return "forum/back/forumback";
		}
		
		if (sortfield.equals("like") || sortfield.equals("collection")) {

			Page<Object[]> rs = articlesService.findByCategoryIdSortingByLikeCollect(categoryId, PageNo, pagesize, sortfield,
					sortmethod);

			List<Integer> likes = new ArrayList<>();

			List<Integer> collections = new ArrayList<>();

			for (Object[] objs : rs) {
				Articles a = (Articles) objs[0];
				likes.add(a.getLikedNum());
				collections.add(a.getCollectionNum());
			}

			model.addAttribute("likeNo", likes);
			model.addAttribute("collectionNo", collections);
			
			
			model.addAttribute("Pagination", rs);
			model.addAttribute("currentPageNo", rs.getNumber() + 1);
			model.addAttribute("systemName", "articles");
			model.addAttribute("querywithlikeorcollection", true);
			model.addAttribute("forum", categoriesService.findByCategoryId(categoryId).getForums());
			model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
		} else {

			Page<Articles> rs = articlesService.findAllByCategoryId(categoryId, PageNo, pagesize, sortfield, sortmethod);

			
			
			List<Integer> likes = new ArrayList<>();

			List<Integer> collections = new ArrayList<>();

			for (Articles a : rs) {
				likes.add(a.getLikedNum());
				collections.add(a.getCollectionNum());
			}

			model.addAttribute("likeNo", likes);
			model.addAttribute("collectionNo", collections);
			
			model.addAttribute("Pagination", rs);
			model.addAttribute("currentPageNo", rs.getNumber() + 1);
			model.addAttribute("systemName", "articles");
			model.addAttribute("querywithlikeorcollection", false);
			model.addAttribute("forum", categoriesService.findByCategoryId(categoryId).getForums());
			model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
		}

		return "forum/back/forumback";
	}
	
	
	
	
}
