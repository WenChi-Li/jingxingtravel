package tw.jingxing.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dao.CategoriesRepository;
import tw.jingxing.forum.model.entity.Categories;

@Service
@Transactional
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	public Categories insertForumsCategories(Categories categories) {
		return categoriesRepository.save(categories);
	}

	/**
	 * 以傳入的子版ID刪除該子版.
	 * <p>
	 * 刪除成功回傳true，若刪除失敗則回傳false.
	 * </p>
	 * 
	 * @return boolean
	 */
	public boolean deleteCategoriesById(Integer id) {
		categoriesRepository.deleteById(id);
		Optional<Categories> op = categoriesRepository.findById(id);
		return op.isEmpty();

	}
	
	public Categories findByCategoryId(Integer categoryId) {
		Optional<Categories> optional = categoriesRepository.findById(categoryId);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
		
	}

	public List<Categories> findByCategoryNameLikeAndForums(String queString, Integer ForumId) {

		return categoriesRepository.findByCategoryNameLikeAndForums("%" + queString + "%", ForumId);

	}

	public Categories updateCategory(Categories categories) {
		
		return categoriesRepository.save(categories);

	}

}
