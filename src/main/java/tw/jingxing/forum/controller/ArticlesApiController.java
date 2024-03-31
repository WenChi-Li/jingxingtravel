package tw.jingxing.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dto.ArticlesDTO;
import tw.jingxing.forum.model.entity.ArticleCollectionRecords;
import tw.jingxing.forum.model.entity.ArticleLikeRecords;
import tw.jingxing.forum.model.entity.ArticleTypes;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.forum.model.entity.Categories;
import tw.jingxing.forum.model.service.ArticleCollectionRecordsService;
import tw.jingxing.forum.model.service.ArticleLikeRecordsService;
import tw.jingxing.forum.model.service.ArticleTypesService;
import tw.jingxing.forum.model.service.ArticlesService;
import tw.jingxing.forum.model.service.CategoriesService;
import tw.jingxing.forum.model.service.ForumsService;
import tw.jingxing.forum.model.service.GlobalForumService;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.service.MemberService;

@Controller
@Transactional
@SessionAttributes({ "artsortfield", "artsortmethod", "artpagesize", "artsortfieldb", "artsortmethodb",
		"artpagesizeb" })
public class ArticlesApiController {

	@Autowired
	private ForumsService forumsService;

	@Autowired
	private ArticlesService articlesService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ArticleTypesService articleTypesService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ArticleCollectionRecordsService articleCollectionRecordsService;

	@Autowired
	private ArticleLikeRecordsService articleLikeRecordsService;

	/*-------------------------------前台------------------------------------------*/
	// 查詢討論區所有文章(Pagination)
	@GetMapping("/articles/forumId.do")
	public String findByForumId(@RequestParam Integer forumId, @RequestParam(required = false) Integer PageNo,
			Model model) {

		String sortfield = (String) model.getAttribute("artsortfield");
		String sortmethod = (String) model.getAttribute("artsortmethod");
		Integer pagesize = (Integer) model.getAttribute("artpagesize");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Article_Sort_Method;
			sortfield = GlobalForumService.Articles_Sort_Field;
			pagesize = GlobalForumService.Article_Page_Size;
			model.addAttribute("artsortmethod", sortmethod);
			model.addAttribute("artsortfield", sortfield);
			model.addAttribute("artpagesize", pagesize);
		}

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

		return "/forum/articles";
	}

	// 查詢子版所有文章(Pagination)
	@GetMapping("/articles/categoryId.do")
	public String findByCategoriesWithPaginationAndSorting(@RequestParam Integer categoryId,
			@RequestParam(required = false) Integer PageNo, Model model) {

		String sortfield = (String) model.getAttribute("artsortfield");
		String sortmethod = (String) model.getAttribute("artsortmethod");
		Integer pagesize = (Integer) model.getAttribute("artpagesize");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Article_Sort_Method;
			sortfield = GlobalForumService.Articles_Sort_Field;
			pagesize = GlobalForumService.Article_Page_Size;
			model.addAttribute("artsortmethod", sortmethod);
			model.addAttribute("artsortfield", sortfield);
			model.addAttribute("artpagesize", pagesize);
		}

		if (sortfield.equals("like") || sortfield.equals("collection")) {

			Page<Object[]> rs = articlesService.findByCategoryIdSortingByLikeCollect(categoryId, PageNo, pagesize,
					sortfield, sortmethod);

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

			Page<Articles> rs = articlesService.findAllByCategoryId(categoryId, PageNo, pagesize, sortfield,
					sortmethod);

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

		return "/forum/articles";
	}

	// 改變排序屬性
	@GetMapping("/articles/change/{field}")
	public void changeSortingField(@PathVariable String field, Model model) {
		String sortfield = (String) model.getAttribute("artsortfield");
		if (sortfield == null) {
			sortfield = GlobalForumService.Articles_Sort_Field;
			model.addAttribute("artsortfield", sortfield);
		} else {
			sortfield = field;
			model.addAttribute("artsortfield", sortfield);
		}
	}

	// 改變排序升降
	@GetMapping("/articles/change")
	public void changeSorting(Model model) {
		String sortmethod = (String) model.getAttribute("artsortmethod");
		if (sortmethod == null) {
			sortmethod = GlobalForumService.Article_Sort_Method;
			model.addAttribute("artsortmethod", sortmethod);
		} else {

			if (sortmethod.equals("desc")) {
				sortmethod = "asc";
				model.addAttribute("artsortmethod", sortmethod);
			} else {
				sortmethod = "desc";
				model.addAttribute("artsortmethod", sortmethod);
			}

		}
	}

	// 改變PageSize
	@GetMapping("/articles/changeps/{PageSize}")
	public void changePageSize(@PathVariable Integer PageSize, Model model) {
		Integer pagesize = (Integer) model.getAttribute("artpagesize");
		if (pagesize == null) {
			pagesize = GlobalForumService.Article_Page_Size;
			model.addAttribute("artpagesize", pagesize);
		} else {
			pagesize = PageSize;
			model.addAttribute("artpagesize", pagesize);
		}
	}

	// 文章title模糊查詢
	@GetMapping("/articles/query")
	public String queryByArticleTitleLike(@RequestParam String querystring, @RequestParam Integer forumId,
			@RequestParam(required = false) Integer PageNo, Model model) {

		String sortfield = (String) model.getAttribute("artsortfield");
		String sortmethod = (String) model.getAttribute("artsortmethod");
		Integer pagesize = (Integer) model.getAttribute("artpagesize");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Article_Sort_Method;
			sortfield = GlobalForumService.Articles_Sort_Field;
			pagesize = GlobalForumService.Article_Page_Size;
			model.addAttribute("artsortmethod", sortmethod);
			model.addAttribute("artsortfield", sortfield);
			model.addAttribute("artpagesize", pagesize);
		}

		Page<Articles> results = articlesService.findByArticleTitleLikePagination(querystring, forumId, PageNo,
				pagesize, sortfield, sortmethod);

		model.addAttribute("Pagination", results);

		model.addAttribute("currentPageNo", results.getNumber() + 1);

		model.addAttribute("forum", forumsService.findForumById(forumId));

		model.addAttribute("querystring", querystring);

		if (results.isEmpty()) {
			model.addAttribute("querymsg", "查無文章");
		} else {
			model.addAttribute("querymsg", "共" + results.getTotalElements() + "篇文章");
		}

		return "/forum/queryarticles";
	}

	// 新增文章
	@PostMapping("/articles")
	@ResponseBody
	public String insertArticle(@RequestBody ArticlesDTO articlesDTO, Model model) {
		JSONObject responseJson = new JSONObject();

		System.out.println(articlesDTO);
		UserData userData = null;
		Categories category = categoriesService.findByCategoryId(articlesDTO.getCategoryId());
		ArticleTypes articleType = articleTypesService.findByArticleTypeId(articlesDTO.getArticleTypeId());
		Optional<UserData> op = memberService.getUserById(articlesDTO.getmId());
		if (op.isPresent()) {
			userData = op.get();
		}

		Articles article = new Articles(articlesDTO.getArticleTitle(), articlesDTO.getArticleContent(),
				articlesDTO.getArticleDate(), category, articleType, userData);
//		System.out.println(DatetimeConverter.toString(articlesDTO.getArticleDate(), "yyyy-MM-dd hh-mm-ss"));
		Articles rs = articlesService.insertArticle(article);

		int forumId = category.getForums().getForumId();

		try {
			responseJson.put("forumId", forumId);
			if (rs == null) {
				responseJson.put("msg", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("msg", "新增成功");
				responseJson.put("success", true);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return responseJson.toString();

	}

	// 查詢單篇文章
	@GetMapping("/article.do")
	public String findByArticleId(@RequestParam Integer articleId, Model model) {

		int likednumber = articleLikeRecordsService.findByLikedArticleArticleId(articleId).size();

		int collectRecordsnum = articleCollectionRecordsService.findCollectedRecordsByArticleId(articleId).size();

		Articles article = articlesService.findByArticleId(articleId);

		model.addAttribute("Article", article);
		model.addAttribute("forum", article.getCategories().getForums());
		model.addAttribute("collectnumber", collectRecordsnum);
		model.addAttribute("likednumber", likednumber);

		return "/forum/article";
	}

	// 修改文章
	@PutMapping("/articles")
	@ResponseBody
	public String updateArticle(@RequestBody ArticlesDTO articlesDTO, Model model) {
		JSONObject responseJson = new JSONObject();

		UserData userData = null;
		Categories category = categoriesService.findByCategoryId(articlesDTO.getCategoryId());
		ArticleTypes articleType = articleTypesService.findByArticleTypeId(articlesDTO.getArticleTypeId());
		Optional<UserData> op = memberService.getUserById(articlesDTO.getmId());
		if (op.isPresent()) {
			userData = op.get();
		}
		Articles article = new Articles(articlesDTO.getArticleId(), articlesDTO.getArticleTitle(),
				articlesDTO.getArticleContent(), articlesDTO.getArticleDate(), category, articleType, userData);
		Articles rs = articlesService.updateArticle(article);

		int forumId = category.getForums().getForumId();

		try {
			responseJson.put("forumId", forumId);
			if (rs == null) {
				responseJson.put("msg", "更新失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("articleId", rs.getArticleId());
				responseJson.put("msg", "更新成功");
				responseJson.put("success", true);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return responseJson.toString();

	}

	// 刪除文章
	@DeleteMapping("/deletearticle")
	@ResponseBody
	public String deleteArticle(@RequestParam Integer articleId) {
		JSONObject responseJson = new JSONObject();

		int forumId = articlesService.findByArticleId(articleId).getCategories().getForums().getForumId();

		boolean rs = articlesService.deleteById(articleId);
		try {
			responseJson.put("forumId", forumId);
			if (rs) {
				responseJson.put("msg", "刪除成功");
				responseJson.put("success", rs);

			} else {
				responseJson.put("msg", "刪除失敗");
				responseJson.put("success", rs);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return responseJson.toString();

	}

	// 檢查會員收藏狀態
	@GetMapping("/articles/collection/contain")
	@ResponseBody
	public String whetherUserDataCollectByArticleId(@RequestParam int mid, @RequestParam int articleId) {

		List<ArticleCollectionRecords> recordsByArticleId = articleCollectionRecordsService
				.findCollectedRecordsByArticleId(articleId);

		for (ArticleCollectionRecords records : recordsByArticleId) {
			if (records.getCollectUserData().getMid() == mid) {
				return "true";
			}
		}
		return "false";
	}

	// 會員收藏功能
	@PostMapping("/articles/collecteduserdata")
	@ResponseBody
	public String modifyCollectRecordsByMidAndArticleId(@RequestParam int mid, @RequestParam int articleId) {
		JSONObject responseJson = new JSONObject();

		List<ArticleCollectionRecords> collectedRecords = articleCollectionRecordsService
				.findCollectedRecordsByArticleId(articleId);

		Optional<UserData> op = memberService.getUserById(mid);
		if (op.isPresent()) {

			for (ArticleCollectionRecords records : collectedRecords) {
				if (records.getCollectUserData().getMid() == mid) {

					boolean removeresult = articleCollectionRecordsService
							.removeCollectionRecords(records.getArticleCollectionRecordId());

					if (removeresult) {
						try {
							JSONObject removeDetail = new JSONObject();
							removeDetail.put("mid", mid);
							removeDetail.put("articleId", articleId);

							responseJson.put("modify", "取消收藏文章");
							responseJson.put("detail", removeDetail);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
					return responseJson.toString();
				}
			}

			UserData userData = memberService.getUserById(mid).orElse(null);

			Articles article = articlesService.findByArticleId(articleId);

			ArticleCollectionRecords records = articleCollectionRecordsService.addCollectionRecords(userData, article);

			JSONObject recordJson = new JSONObject();

			if (records != null) {
				try {
					recordJson.put("mid", records.getCollectUserData().getMid());
					recordJson.put("articleId", records.getCollectedArticle().getArticleId());
					recordJson.put("date", records.getArticleCollectionRecordDate());
					responseJson.put("modify", "收藏文章");
					responseJson.put("record", recordJson);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				return responseJson.toString();

			}

		}
		return "不存在使用者或文章收藏失敗";
	}

	// 檢查會員按讚狀態
	@GetMapping("/articles/likedmembers/contain")
	@ResponseBody
	public String whetherUserDatalikeByArticleId(@RequestParam int mid, @RequestParam int articleId) {
		List<ArticleLikeRecords> recordsByArticleId = articleLikeRecordsService.findByLikedArticleArticleId(articleId);
		for (ArticleLikeRecords records : recordsByArticleId) {
			if (records.getLikedUserData().getMid() == mid) {
				return "true";
			}
		}
		return "false";

	}

	// 會員按讚功能
	@PostMapping("/articles/likeduserdata")
	@ResponseBody
	public String modifyLikedUserDatasByArticleId(@RequestParam int mid, @RequestParam int articleId) {
		JSONObject responseJson = new JSONObject();

		List<ArticleLikeRecords> likeRecords = articleLikeRecordsService.findByLikedArticleArticleId(articleId);

		Optional<UserData> op = memberService.getUserById(mid);
		if (op.isPresent()) {

			for (ArticleLikeRecords records : likeRecords) {
				if (records.getLikedUserData().getMid() == mid) {

					boolean removeresult = articleLikeRecordsService
							.removeLikeCollectionRecords(records.getArticleLikeRecordId());

					if (removeresult) {
						try {
							JSONObject removeDetail = new JSONObject();
							removeDetail.put("mid", mid);
							removeDetail.put("articleId", articleId);

							responseJson.put("modify", "取消按讚文章");
							responseJson.put("detail", removeDetail);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
					return responseJson.toString();
				}
			}

			UserData userData = memberService.getUserById(mid).orElse(null);

			Articles article = articlesService.findByArticleId(articleId);

			ArticleLikeRecords records = articleLikeRecordsService.addLikeRecords(userData, article);

			JSONObject recordJson = new JSONObject();

			if (records != null) {
				try {
					recordJson.put("mid", records.getLikedUserData().getMid());
					recordJson.put("articleId", records.getLikedArticle().getArticleId());
					recordJson.put("date", records.getArticleLikeRecordDate());
					responseJson.put("modify", "按讚文章");
					responseJson.put("record", recordJson);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				return responseJson.toString();

			}

		}
		return "不存在使用者或文章收藏失敗";
	}

	/*------------------------------後台-----------------------------------*/

	// 改變PageSize
	@GetMapping("/back/articles/changeps/{PageSize}")
	public void changePageSizeBack(@PathVariable Integer PageSize, Model model) {
		Integer pagesize = (Integer) model.getAttribute("artpagesizeb");
		if (pagesize == null) {
			pagesize = GlobalForumService.Back_Article_Page_Size;
			model.addAttribute("artpagesizeb", pagesize);
		} else {
			pagesize = PageSize;
			model.addAttribute("artpagesizeb", pagesize);
		}
	}

	// 改變排序屬性
	@GetMapping("/back/articles/change/{field}")
	public void changeSortingFieldBack(@PathVariable String field, Model model) {
		String sortfield = (String) model.getAttribute("artsortfieldb");
		if (sortfield == null) {
			sortfield = GlobalForumService.Back_Articles_Sort_Field;
			model.addAttribute("artsortfieldb", sortfield);
		} else {
			sortfield = field;
			model.addAttribute("artsortfieldb", sortfield);
		}
	}

	// 改變排序升降
	@GetMapping("/back/articles/change")
	public void changeSortingBack(Model model) {
		String sortmethod = (String) model.getAttribute("artsortmethodb");
		if (sortmethod == null) {
			sortmethod = GlobalForumService.Back_Article_Sort_Method;
			model.addAttribute("artsortmethodb", sortmethod);
		} else {

			if (sortmethod.equals("desc")) {
				sortmethod = "asc";
				model.addAttribute("artsortmethodb", sortmethod);
			} else {
				sortmethod = "desc";
				model.addAttribute("artsortmethodb", sortmethod);
			}

		}
	}

	// 查詢單篇文章
	@GetMapping("/back/article.do")
	public String findByArticleIdBack(@RequestParam Integer articleId, Model model) {

		int likednumber = articleLikeRecordsService.findByLikedArticleArticleId(articleId).size();

		int collectRecordsnum = articleCollectionRecordsService.findCollectedRecordsByArticleId(articleId).size();

		Articles article = articlesService.findByArticleId(articleId);

		model.addAttribute("Article", article);
		model.addAttribute("forum", article.getCategories().getForums());
		model.addAttribute("collectnumber", collectRecordsnum);
		model.addAttribute("likednumber", likednumber);

		return "/forum/back/articleback";
	}

	// 文章title模糊查詢
	@GetMapping("/back/articles/query")
	public String queryByArticleTitleLikeBack(@RequestParam String querystring, @RequestParam Integer forumId,
			@RequestParam(required = false) Integer PageNo, Model model) {

		String sortfield = (String) model.getAttribute("artsortfieldb");
		String sortmethod = (String) model.getAttribute("artsortmethodb");
		Integer pagesize = (Integer) model.getAttribute("artpagesizeb");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Back_Article_Sort_Method;
			sortfield = GlobalForumService.Back_Articles_Sort_Field;
			pagesize = GlobalForumService.Back_Article_Page_Size;
			model.addAttribute("artsortmethodb", sortmethod);
			model.addAttribute("artsortfieldb", sortfield);
			model.addAttribute("artpagesizeb", pagesize);
		}

		if (sortfield.equals("like") || sortfield.equals("collection")) {

			Page<Object[]> rs = articlesService.findByArticleTitleLikeSortingByLikeCollect(forumId, querystring, PageNo,
					pagesize, sortfield, sortmethod);

			model.addAttribute("Pagination", rs);
			model.addAttribute("currentPageNo", rs.getNumber() + 1);
			model.addAttribute("systemName", "articles");
			model.addAttribute("querywithlikeorcollection", true);
			model.addAttribute("forum", forumsService.findForumById(forumId));
			model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
		} else {

			Page<Articles> rs = articlesService.findByArticleTitleLikePagination(querystring, forumId, PageNo, pagesize,
					sortfield, sortmethod);

			model.addAttribute("Pagination", rs);
			model.addAttribute("currentPageNo", rs.getNumber() + 1);
			model.addAttribute("systemName", "articles");
			model.addAttribute("querywithlikeorcollection", false);
			model.addAttribute("forum", forumsService.findForumById(forumId));
			model.addAttribute("querymsg", "共" + rs.getTotalElements() + "篇文章");
		}

		return "forum/back/forumback";
	}
}
