package tw.jingxing.forum.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.jingxing.forum.model.dao.ArticleLikeRecordsRepository;
import tw.jingxing.forum.model.entity.ArticleLikeRecords;
import tw.jingxing.forum.model.entity.Articles;
import tw.jingxing.member.model.bean.UserData;

@Service
public class ArticleLikeRecordsService {

	@Autowired
	private ArticleLikeRecordsRepository articleLikeRecordsRepository;
	
	
	public List<ArticleLikeRecords> findByLikedArticleArticleId(int articleId) {
		return articleLikeRecordsRepository.findByLikedArticleArticleId(articleId);
	}
	
	public List<ArticleLikeRecords> findByLikedUserDataMid(int mid) {
		return articleLikeRecordsRepository.findByLikedUserDataMid(mid);
	}
	
	public boolean removeLikeCollectionRecords(int articleLikeRecordId) {
		articleLikeRecordsRepository.deleteById(articleLikeRecordId);
		Optional<ArticleLikeRecords> op = articleLikeRecordsRepository.findById(articleLikeRecordId);
		if (op.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public ArticleLikeRecords addLikeRecords(UserData userData,Articles articles) {
		
		ArticleLikeRecords record = new ArticleLikeRecords(LocalDateTime.now(),articles,userData);
		
		return articleLikeRecordsRepository.save(record);
		
		
		
		
	}
}
