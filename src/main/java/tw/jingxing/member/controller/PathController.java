package tw.jingxing.member.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.entity.ArticleLikeRecords;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.forum.model.service.ArticlesService;
import tw.jingxing.member.model.bean.UserData;
import tw.jingxing.member.service.MemberService;

@Controller
@Transactional
public class PathController {

	@Autowired
	private ArticlesService articlesService;
	
	@Autowired
	private MemberService memberService;
	
	// 導到"首頁"
	@GetMapping("/index")
	public String goIndex() {
		return "index";
	}
	
	// 導到"註冊"
	@GetMapping("/register")
	public String goRegister() {
		return "member/clients/register";
	}
	
	// 導到"登入"
	@GetMapping("/login")
	public String goLogin() {
		return "member/clients/login";
	}
	
	// 導到"忘記密碼"
	@GetMapping("/forgetPassword")
	public String goForgetPassword() {
		return "member/clients/forgetPassword";
	}
	
	// 導到"會員抽獎"
	@GetMapping("/memberLottery")
	public String goMemberLottery() {
		return "member/clients/memberLottery";
	}
	
	// 導到"文章作者"
	
	
	// 導到"文章作者"
	@GetMapping("/forgetPwdServiceCenter")
	public String goForgetPwdServiceCenter() {
		return "member/clients/forgetPwdServiceCenter";
	}
	
	// 導到"會員中心"
	@GetMapping("/memberCenter")
	public String goMemberCenter() {
		return "member/clients/memberCenter";
	}
	
	// 導到"客服中心"
	@GetMapping("/customerServiceUser")
	public String goCustomerServiceUser() {
		return "member/clients/customerServiceUser";
	}
	
	// 導到"成功登出"
	@GetMapping("/logoutSuccess")
	public String gologoutSuccess() {
		return "member/logoutSuccess";
	}
	
	// 導到"二次驗證"
	@GetMapping("/validationPlace")
	public String goValidationPlace() {
		return "member/clients/validationPlace";
	}
	
	// 導到"Admin後台"
	@GetMapping("/backend")
	public String goBackend() {
		return "member/admin/backend";
	}
	
	// 導到"Admin查詢會員"
	@GetMapping("/memberQuery")
	public String goMemberQuery() {
		return "member/admin/memberQuery";
	}
	
	// 導到"Admin後台編輯會員"
		@GetMapping("/adminEdit")
		public String goAdminEdit() {
			return "member/admin/adminEdit";
		}
		
	// 導到"Admin客服中心"
	@GetMapping("/adminCustomerService")
	public String goAdminCustomerService() {
		return "member/admin/customerService";
	}
	
	// 導到"Admin平台管理系統"
	@GetMapping("/adminPlatformCenter")
	public String goAdminPlatformCenter() {
		return "member/admin/platformCenter";
	}
	
	// 導到"Admin獎品編輯"
	@GetMapping("/adminPrizeEdit")
	public String goAdminPrizeEdit() {
		return "member/admin/prizeEdit";
	}
	
	// 導到"Admin獎池"
	@GetMapping("/adminPrizePoolEdit")
	public String goAdminPrizePoolEdit() {
		return "member/admin/prizePoolEdit";
	}
	
	// 導到"查看對話紀錄"
	@GetMapping("/adminViewRecord")
	public String goAdminViewRecord() {
		return "member/admin/viewRecord";
	}
	
	// 導到"Admin編輯獎池"
	@GetMapping("/adminEditPrize")
	public String goAdminEditPrize() {
		return "member/admin/adminEditPrize";
	}


	// 導到"文章作者"
		@GetMapping("/authorData/{Mid}")
		public String goAuthorData(@PathVariable Integer Mid,Model model) {
			
			Optional<UserData> op = memberService.getUserById(Mid);
			if (op.isEmpty()) {
				return "redirect:/login";
			}
			
			UserData userData = op.get();
			List<Articles> articles = userData.getArticles();
			model.addAttribute("userData", userData);
			
			//計算人氣(讚加收藏數)
			int likeAndCollection = 0;
			for (Articles a : articles) {
				likeAndCollection+=a.getCollectionNum()+a.getLikedNum();
			}
			model.addAttribute("popular", likeAndCollection);
			
			for (ArticleLikeRecords articleLikeRecords : userData.getLikeArticles()) {
				articleLikeRecords.getLikedArticle();
			}
			
			List<Articles> userArticles = articlesService.findTop3ByMid(Mid);
			List<Articles> likeArticles = articlesService.findTop3ByMidLike(Mid);
			List<Articles> collectArticles = articlesService.findTop3ByMidCollect(Mid);
			
			
			model.addAttribute("userArticles", userArticles);
			model.addAttribute("likeArticles", likeArticles);
			model.addAttribute("collectArticles", collectArticles);
			
			
			userData.setUserPic(userData.getUserPic());
			
			if (userData.getUserBackGround()!=null) {
			    userData.setUserBackGround(userData.getUserBackGround());
			   }
			
			return "member/clients/authorData";
		}
		
		
		// 導到"文章作者"
		@GetMapping("/authorEdit/{Mid}")
		public String goAuthorEdit(@PathVariable Integer Mid,Model model) {
			Optional<UserData> op = memberService.getUserById(Mid);
			if (op.isEmpty()) {
				return "redirect:/login";
			}
			
			UserData userData = op.get();
			List<Articles> articles = userData.getArticles();
			model.addAttribute("userData", userData);
			
			//計算人氣(讚加收藏數)
			int likeAndCollection = 0;
			for (Articles a : articles) {
				likeAndCollection+=a.getCollectionNum()+a.getLikedNum();
			}
			model.addAttribute("popular", likeAndCollection);
			
			for (ArticleLikeRecords articleLikeRecords : userData.getLikeArticles()) {
				articleLikeRecords.getLikedArticle();
			}
			
			List<Articles> userArticles = articlesService.findTop3ByMid(Mid);
			List<Articles> likeArticles = articlesService.findTop3ByMidLike(Mid);
			List<Articles> collectArticles = articlesService.findTop3ByMidCollect(Mid);
			
			
			model.addAttribute("userArticles", userArticles);
			model.addAttribute("likeArticles", likeArticles);
			model.addAttribute("collectArticles", collectArticles);
			
			
			userData.setUserPic(userData.getUserPic());
			
			if (userData.getUserBackGround()!=null) {
			    userData.setUserBackGround(userData.getUserBackGround());
			   }
			
			return "member/clients/authorEdit";
		}
	

}
