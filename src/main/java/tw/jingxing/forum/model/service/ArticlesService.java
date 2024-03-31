package tw.jingxing.forum.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dao.ArticlesRepository;
import tw.jingxing.forum.model.entity.Articles;

@Service
@Transactional
public class ArticlesService {

	@Autowired
	private ArticlesRepository articlesRepository;

	public Articles findLatestByForumId(Integer forumId) {

		return articlesRepository.findTop1ByCategoriesForumsForumIdOrderByArticleDateDesc(forumId);

	}

	public Page<Articles> findByForumId(Integer forumId, Integer PageNo, Integer PageSize, String SortField,
			String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByCategoriesForumsForumId(forumId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByCategoriesForumsForumId(forumId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}

	public Page<Object[]> findBySortingByLikeCollect(Integer forumId, Integer PageNo, Integer PageSize,
			String SortField, String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortField.equals("like")) {
			if (SortMethod.equals("desc")) {
				return articlesRepository.findByForumIdSortByLikeNo(forumId,
						PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
			} else {
				return articlesRepository.findByForumIdSortByLikeNo(forumId,
						PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
			}
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByForumIdSortByCollectNo(forumId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByForumIdSortByCollectNo(forumId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}

	
	
	public Page<Object[]> findByCategoryIdSortingByLikeCollect(Integer categoryId, Integer PageNo, Integer PageSize,
			String SortField, String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortField.equals("like")) {
			if (SortMethod.equals("desc")) {
				return articlesRepository.findByCategoryIdSortByLikeNo(categoryId,
						PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
			} else {
				return articlesRepository.findByCategoryIdSortByLikeNo(categoryId,
						PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
			}
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByCategoryIdSortByCollectNo(categoryId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByCategoryIdSortByCollectNo(categoryId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}
	
	
	
	
	
	
	
	public Page<Articles> findAllByCategoryId(Integer categoryId, Integer PageNo, Integer PageSize, String SortField,
			String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByCategoriesCategoryId(categoryId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByCategoriesCategoryId(categoryId,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}

	public Articles findByArticleId(Integer articleId) {
		Optional<Articles> op = articlesRepository.findById(articleId);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	public Articles insertArticle(Articles articles) {
		return articlesRepository.save(articles);

	}

	public Articles updateArticle(Articles articles) {
		return articlesRepository.save(articles);

	}

	public boolean deleteById(Integer articleId) {
		articlesRepository.deleteById(articleId);
		Optional<Articles> op = articlesRepository.findById(articleId);
		if (op.isEmpty()) {
			return true;
		}
		return false;

	}

	public Page<Articles> findByArticleTitleLikePagination(String articleTitle, Integer forumId, Integer PageNo,
			Integer PageSize, String SortField, String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByCategoriesForumsForumIdAndArticleTitleContaining(forumId, articleTitle,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByCategoriesForumsForumIdAndArticleTitleContaining(forumId, articleTitle,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}
	
	
	public Page<Object[]> findByArticleTitleLikeSortingByLikeCollect(Integer forumId,String articleTitle, Integer PageNo, Integer PageSize,
			String SortField, String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortField.equals("like")) {
			if (SortMethod.equals("desc")) {
				return articlesRepository.findByArticleTitleLikeWithLikeNo(forumId,articleTitle,
						PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
			} else {
				return articlesRepository.findByArticleTitleLikeWithLikeNo(forumId,articleTitle,
						PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
			}
		}

		if (SortMethod.equals("desc")) {
			return articlesRepository.findByArticleTitleLikeWithCollectNo(forumId,articleTitle,
					PageRequest.of(offset, PageSize, Sort.by(SortField).descending()));
		} else {
			return articlesRepository.findByArticleTitleLikeWithCollectNo(forumId,articleTitle,
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}
	
	//該user作品文章中前三高按讚數的文章
		public List<Articles> findTop3ByMid(Integer Mid) {

			List<Articles> articles = new ArrayList<>();
			List<Object[]> rs = articlesRepository.findTop3LikeByMid(Mid);
			
			if (rs.size()>=3) {
				for (int i = 0; i < 3; i++) {
					articles.add((Articles) rs.get(i)[0]);
				}			
			}else if (rs.size()>0) {
				for (Object[] objects : rs) {
					articles.add((Articles)objects[0]);
				}
			}else {
				articles=null;
			}		

			return articles;
		}
		
		//該user按讚文章中前三高按讚數的文章
		public List<Articles> findTop3ByMidLike(Integer Mid) {

			List<Articles> articles = new ArrayList<>();
			List<Object[]> rs = articlesRepository.findTop3LikeByMidLike(Mid);

			if (rs.size()>=3) {
				for (int i = 0; i < 3; i++) {
					articles.add((Articles) rs.get(i)[0]);
				}			
			}else if (rs.size()>0) {
				for (Object[] objects : rs) {
					articles.add((Articles)objects[0]);
				}
			}else {
				articles=null;
			}

			return articles;
		}
		
		//該user收藏文章中前三高按讚數的文章
			public List<Articles> findTop3ByMidCollect(Integer Mid) {

				List<Articles> articles = new ArrayList<>();
				List<Object[]> rs = articlesRepository.findTop3LikeByMidCollect(Mid);

				if (rs.size()>=3) {
					for (int i = 0; i < 3; i++) {
						articles.add((Articles) rs.get(i)[0]);
					}			
				}else if (rs.size()>0) {
					for (Object[] objects : rs) {
						articles.add((Articles)objects[0]);
					}
				}else {
					articles=null;
				}

				return articles;
			}
	
	
	
	
	

}
