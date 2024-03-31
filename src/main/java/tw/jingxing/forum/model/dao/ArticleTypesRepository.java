package tw.jingxing.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.jingxing.forum.model.entity.ArticleTypes;

public interface ArticleTypesRepository extends JpaRepository<ArticleTypes, Integer> {

}
