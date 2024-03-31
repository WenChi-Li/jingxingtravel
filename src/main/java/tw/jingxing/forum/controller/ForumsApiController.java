package tw.jingxing.forum.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dto.ForumsDTO;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.forum.model.entity.Forums;
import tw.jingxing.forum.model.service.ArticlesService;
import tw.jingxing.forum.model.service.ForumsService;
import tw.jingxing.forum.model.service.GlobalForumService;

@Controller
@Transactional
@SessionAttributes({ "sortfield", "sortmethod", "pagesize", "sortfieldb", "sortmethodb", "pagesizeb" })
public class ForumsApiController {

	@Autowired
	private ForumsService forumsService;

	@Autowired
	private ArticlesService articlesService;

	/*---------------------前台---------------------------------------------------------*/

	// 查詢所有討論區
	@GetMapping("/forums.do")
	public String queryAllForums(@RequestParam(required = false) Integer PageNo, Model model) {

		String sortfield = (String) model.getAttribute("sortfield");
		String sortmethod = (String) model.getAttribute("sortmethod");
		Integer pagesize = (Integer) model.getAttribute("pagesize");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Forum_Sort_Method;
			sortfield = GlobalForumService.Forums_Sort_Field;
			pagesize = GlobalForumService.Forum_Page_Size;
			model.addAttribute("sortmethod", sortmethod);
			model.addAttribute("sortfield", sortfield);
			model.addAttribute("pagesize", pagesize);
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

		model.addAttribute("systemName", "forums");
		
//		model.addAttribute("allquery", forumsService.findAll());
		

		return "forum/forums";

	}

	// 改變排序屬性
	@GetMapping("/forums/change/{field}")
	public void changeSortingField(@PathVariable String field, Model model) {
		String sortfield = (String) model.getAttribute("sortfield");
		if (sortfield == null) {
			sortfield = GlobalForumService.Forums_Sort_Field;
			model.addAttribute("sortmethod", sortfield);
		} else {
			sortfield = field;
			model.addAttribute("sortfield", sortfield);
		}
	}

	// 改變PageSize
	@GetMapping("/forums/changeps/{PageSize}")
	public void changePageSize(@PathVariable Integer PageSize, Model model) {
		Integer pagesize = (Integer) model.getAttribute("pagesize");
		if (pagesize == null) {
			pagesize = GlobalForumService.Forum_Page_Size;
			model.addAttribute("pagesize", pagesize);
		} else {
			pagesize = PageSize;
			model.addAttribute("pagesize", pagesize);
		}
	}

	// 改變排序升降
	@GetMapping("/forums/change")
	public void changeSorting(Model model) {
		String sortmethod = (String) model.getAttribute("sortmethod");
		if (sortmethod == null) {
			sortmethod = GlobalForumService.Forum_Sort_Method;
			model.addAttribute("sortmethod", sortmethod);
		} else {

			if (sortmethod.equals("desc")) {
				sortmethod = "asc";
				model.addAttribute("sortmethod", sortmethod);
			} else {
				sortmethod = "desc";
				model.addAttribute("sortmethod", sortmethod);
			}

		}
	}

	// 查詢單筆討論區
	@GetMapping("/forums/{forumsid}.do")
	public Forums queryByForumId(@PathVariable(name = "forumsid") Integer forumsid) {
		return forumsService.findForumById(forumsid);
	}

	// 模糊查詢討論區名字
	@GetMapping("/forums/query")
	public String queryByForumNameLike(@RequestParam(name = "querystring") String querystring,
			@RequestParam(required = false) Integer PageNo, Model model) {
		String sortfield = (String) model.getAttribute("sortfield");
		String sortmethod = (String) model.getAttribute("sortmethod");
		Integer pagesize = (Integer) model.getAttribute("pagesize");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Forum_Sort_Method;
			sortfield = GlobalForumService.Forums_Sort_Field;
			pagesize = GlobalForumService.Forum_Page_Size;
			model.addAttribute("sortmethod", sortmethod);
			model.addAttribute("sortfield", sortfield);
			model.addAttribute("pagesize", pagesize);
		}

		if (querystring.trim() == null || querystring.trim() == "") {
			return "redirect:/back/forums";

		} else {
			Page<Forums> results = forumsService.findByForumNameContaining(querystring, PageNo, pagesize, sortfield,
					sortmethod);
			model.addAttribute("allforums", results);

			model.addAttribute("currentPage", results.getNumber() + 1);
			if (results.isEmpty()) {
				model.addAttribute("querymsg", "查無資料");
			} else {
				model.addAttribute("querymsg", "共" + results.getTotalElements() + "筆資料");
			}
		}

		return "forum/forums";
	}

	/*---------------------------------後台-------------------------------------------------*/

	// 模糊查詢討論區名字
	@GetMapping("/back/forums/query")
	public String queryByForumNameLikeback(@RequestParam(name = "querystring") String querystring,
			@RequestParam(required = false) Integer PageNo, Model model) {

		String sortfield = (String) model.getAttribute("sortfieldb");
		String sortmethod = (String) model.getAttribute("sortmethodb");
		Integer pagesize = (Integer) model.getAttribute("pagesizeb");

		if (sortmethod == null || sortfield == null || pagesize == null) {
			sortmethod = GlobalForumService.Back_Forum_Sort_Method;
			sortfield = GlobalForumService.Back_Forums_Sort_Field;
			pagesize = GlobalForumService.Back_Forum_Page_Size;
			model.addAttribute("sortmethodb", sortmethod);
			model.addAttribute("sortfieldb", sortfield);
			model.addAttribute("pagesizeb", pagesize);
		}

		if (querystring.trim() == null || querystring.trim() == "") {
			return "redirect:/back/forums";
		} else {
			Page<Forums> results = forumsService.findByForumNameContaining(querystring, PageNo, pagesize, sortfield,
					sortmethod);
			model.addAttribute("allforums", results);
			model.addAttribute("currentPage", results.getNumber() + 1);
			if (results.isEmpty()) {
				model.addAttribute("querymsg", "查無資料");
			} else {
				model.addAttribute("querymsg", "共" + results.getTotalElements() + "筆資料");
			}
		}
		return "forum/back/forumsback";
	}

	// 更新討論區
	@PostMapping("/forums/{forumsid}.do")
	public String updateForum(@ModelAttribute Forums forums, @PathVariable Integer forumsid) {
		forumsService.updateForum(forums);
		return "redirect:/back/forums";
	}

	// 新增討論區
	@PostMapping("/forums.do")
	public String insertForum(@ModelAttribute ForumsDTO forumsDto) {

		System.out.println(forumsDto);

		Forums forums = new Forums(forumsDto.getForumName(), forumsDto.getForumIntroduction(),
				forumsDto.getForumImage());
		forumsService.insertForum(forums);
		return "redirect:/back/forums";
	}

	// 刪除討論區
	@DeleteMapping("/forums/{forumsid}")
	@ResponseBody
	public String deleteForum(@PathVariable(name = "forumsid") Integer forumsid) {
		boolean result = forumsService.deleteForumById(forumsid);
		if (result) {
			return "Delete OK";
		}
		return "Delete Fail";
	}

	// 改變排序屬性
	@GetMapping("/back/forums/change/{field}")
	public void changeSortingFieldBack(@PathVariable String field, Model model) {
		String sortfield = (String) model.getAttribute("sortfieldb");
		if (sortfield == null) {
			sortfield = GlobalForumService.Back_Forums_Sort_Field;
			model.addAttribute("sortmethodb", sortfield);
		} else {
			sortfield = field;
			model.addAttribute("sortfieldb", sortfield);
		}
	}

	// 改變PageSize
	@GetMapping("/back/forums/changeps/{PageSize}")
	public void changePageSizeBack(@PathVariable Integer PageSize, Model model) {
		Integer pagesize = (Integer) model.getAttribute("pagesizeb");
		if (pagesize == null) {
			pagesize = GlobalForumService.Back_Forum_Page_Size;
			model.addAttribute("pagesizeb", pagesize);
		} else {
			pagesize = PageSize;
			model.addAttribute("pagesizeb", pagesize);
		}
	}

	// 改變排序升降
	@GetMapping("/back/forums/change")
	public void changeSortingBack(Model model) {
		String sortmethod = (String) model.getAttribute("sortmethodb");
		if (sortmethod == null) {
			sortmethod = GlobalForumService.Back_Forum_Sort_Method;
			model.addAttribute("sortmethodb", sortmethod);
		} else {
			if (sortmethod.equals("desc")) {
				sortmethod = "asc";
				model.addAttribute("sortmethodb", sortmethod);
			} else {
				sortmethod = "desc";
				model.addAttribute("sortmethodb", sortmethod);
			}

		}
	}

}
