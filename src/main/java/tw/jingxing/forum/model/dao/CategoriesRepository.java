package tw.jingxing.forum.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.jingxing.forum.model.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

	@Query("SELECT c from Categories c where c.categoryName like ?1 and c.forums.forumId = ?2")
	List<Categories> findByCategoryNameLikeAndForums(String categoryName,Integer ForumId);
	
	@EntityGraph(attributePaths = {"forums","articles"})
	void deleteById(Integer id) ;
	
}
