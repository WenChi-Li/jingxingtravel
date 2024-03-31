package tw.jingxing.forum.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.jingxing.forum.model.entity.Forums;


public interface ForumsRepository extends JpaRepository<Forums, Integer> {

	
	
	Page<Forums> findByForumNameContaining(String forumName,Pageable pageable);
	
	@EntityGraph(attributePaths = {"categories"})
	@Query("SELECT f from Forums f JOIN f.categories c JOIN c.articles a where a.articleId = ?1")
	Forums findByArticleId(Integer articleId);
	
	
	
	@Query("SELECT f,count(l) as pop from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.likedUserDatas l "
			+ "GROUP BY f ")
	Page<Object[]> findAllBySortingWithPopular(Pageable pageable); 
	
	@Query("SELECT f,count(cr) as cs from "
			+ "Forums f JOIN f.categories c "
			+ "JOIN c.articles a "
			+ "JOIN a.collectedRecords cr "
			+ "GROUP BY f ")
	Page<Object[]> findAllBySortingWithCollectNo(Pageable pageable); 
	
	
	
	
}
