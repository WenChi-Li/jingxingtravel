package tw.jingxing.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dao.ArticleTypesRepository;
import tw.jingxing.forum.model.entity.ArticleTypes;

@Service @Transactional
public class ArticleTypesService {

	@Autowired
	private ArticleTypesRepository articleTypesRepository;
	
	
	public List<ArticleTypes> findAllArticleTypes() {
		return articleTypesRepository.findAll();
	}
	
	public ArticleTypes findByArticleTypeId(Integer articleTypeId) {
		Optional<ArticleTypes> op = articleTypesRepository.findById(articleTypeId);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
		
	}
	
	public ArticleTypes inserArticleTypes(ArticleTypes articleTypes) {
		
		return articleTypesRepository.save(articleTypes);
		
	}
}
