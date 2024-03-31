package tw.jingxing.forum.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.jingxing.forum.model.entity.ArticleLikeRecords;

public interface ArticleLikeRecordsRepository extends JpaRepository<ArticleLikeRecords, Integer> {

	
		//查詢此文章的所有被按讚紀錄
		List<ArticleLikeRecords> findByLikedArticleArticleId(int articleId);
		
		
		//查詢此會員的所有按讚紀錄
		List<ArticleLikeRecords> findByLikedUserDataMid(int mid);
	
	
}
