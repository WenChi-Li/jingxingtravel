package tw.jingxing.forum.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.jingxing.forum.model.entity.Articles;


public interface ArticlesRepository extends JpaRepository<Articles, Integer> {

	
	@EntityGraph(attributePaths = {"collectedRecords","likedUserDatas"})
	 void deleteById(Integer id) ;
	
	
	Articles findTop1ByCategoriesForumsForumIdOrderByArticleDateDesc(Integer forumId);
	
	Page<Articles> findByCategoriesForumsForumId(Integer forumId, Pageable pageable);
	
	
	@Query("SELECT a,count(l) as like from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.likedUserDatas l "
			+ "where f.forumId=?1 "
			+ "GROUP BY a"
			)
	Page<Object[]> findByForumIdSortByLikeNo(Integer forumId, Pageable pageable);
	
	@Query("SELECT a,count(n) as collection from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.collectedRecords n "
			+ "where f.forumId=?1 "
			+ "GROUP BY a"
			)
	Page<Object[]> findByForumIdSortByCollectNo(Integer forumId, Pageable pageable);
	
	@Query("SELECT a,count(l) as like from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.likedUserDatas l "
			+ "where c.categoryId=?1 "
			+ "GROUP BY a"
			)
	Page<Object[]> findByCategoryIdSortByLikeNo(Integer categoryId, Pageable pageable);
	
	@Query("SELECT a,count(n) as collection from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.collectedRecords n "
			+ "where c.categoryId=?1 "
			+ "GROUP BY a"
			)
	Page<Object[]> findByCategoryIdSortByCollectNo(Integer categoryId, Pageable pageable);
	
	
	
	
	Page<Articles> findByCategoriesCategoryId(Integer categoryId, Pageable pageable);
	
	
	
	
	Page<Articles> findByCategoriesForumsForumIdAndArticleTitleContaining(Integer forumId, String articleTitle, Pageable pageable);

	
	
	@Query("SELECT a,count(l) as like from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.likedUserDatas l "
			+ "where f.forumId=?1 "
			+ "and a.articleTitle like '%?2%' "
			+ "GROUP BY a"
			)
	Page<Object[]> findByArticleTitleLikeWithLikeNo(Integer forumId,String articleTitle, Pageable pageable);
	
	@Query("SELECT a,count(n) as collection from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.collectedRecords n "
			+ "where f.forumId=?1 "
			+ "and a.articleTitle like '%?2%' "
			+ "GROUP BY a"
			)
	Page<Object[]> findByArticleTitleLikeWithCollectNo(Integer forumId,String articleTitle, Pageable pageable);
	

@Query("SELECT alr.likedArticle,count(alr) from ArticleLikeRecords alr WHERE alr.likedArticle.articleId IN "
			+ "(SELECT a.articleId "
			+ "from Articles a "
			+ "WHERE a.userData.mid = ?1) "
			+ "GROUP BY alr.likedArticle "
			+ "ORDER BY count(alr) DESC ")
	List<Object[]> findTop3LikeByMid(Integer Mid);
	
	
	
	@Query("SELECT alr.likedArticle,count(alr) from ArticleLikeRecords alr WHERE alr.likedArticle.articleId IN "
			+ "(SELECT alr.likedArticle.articleId "
			+ "from ArticleLikeRecords alr WHERE alr.likedUserData.mid = ?1) "
			+ "GROUP BY alr.likedArticle "
			+ "ORDER BY count(alr) DESC ")
	List<Object[]> findTop3LikeByMidLike(Integer Mid);
	
	
	@Query("SELECT alr.likedArticle,count(alr) from ArticleLikeRecords alr WHERE alr.likedArticle.articleId IN "
			+ "(SELECT acr.collectedArticle.articleId "
			+ "from ArticleCollectionRecords acr WHERE acr.collectUserData.mid = ?1) "
			+ "GROUP BY alr.likedArticle "
			+ "ORDER BY count(alr) DESC ")
	List<Object[]> findTop3LikeByMidCollect(Integer Mid);
	
	
}
