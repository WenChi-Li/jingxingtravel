package tw.jingxing.forum.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.jingxing.forum.model.entity.ArticleCollectionRecords;

public interface ArticleCollectionRecordsRepository extends JpaRepository<ArticleCollectionRecords, Integer> {

	
	
	//查詢此文章的所有被收藏紀錄
	List<ArticleCollectionRecords> findByCollectedArticleArticleId(int articleId);
	
	
	//查詢此會員的所有收藏紀錄
	List<ArticleCollectionRecords> findByCollectUserDataMid(int mid);
	
	
}
