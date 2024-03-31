package tw.jingxing.forum.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.jingxing.forum.model.dao.ArticleCollectionRecordsRepository;
import tw.jingxing.forum.model.entity.ArticleCollectionRecords;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.member.model.bean.UserData;

@Service
public class ArticleCollectionRecordsService {

	@Autowired
	private ArticleCollectionRecordsRepository articleCollectionRecordsRepository;
	
	
	public List<ArticleCollectionRecords> findCollectedRecordsByArticleId(int articleId) {
		return articleCollectionRecordsRepository.findByCollectedArticleArticleId(articleId);
	}
	
	public List<ArticleCollectionRecords> findCollectedRecordsByMid(int mid) {
		return articleCollectionRecordsRepository.findByCollectUserDataMid(mid);
	}
	
	public boolean removeCollectionRecords(int articleCollectionRecordId) {
		articleCollectionRecordsRepository.deleteById(articleCollectionRecordId);
		Optional<ArticleCollectionRecords> op = articleCollectionRecordsRepository.findById(articleCollectionRecordId);
		if (op.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public ArticleCollectionRecords addCollectionRecords(UserData userData,Articles articles) {
		
		ArticleCollectionRecords record = new ArticleCollectionRecords(LocalDateTime.now(),articles,userData);
		
		return articleCollectionRecordsRepository.save(record);
		
		
		
		
	}
}
