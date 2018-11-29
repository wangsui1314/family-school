package com.bjwk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bjwk.model.Article;

/**
 * @author Desolation
 * @version 1.0
 * @Description: TODO
 * @email:1071680460@qq.com
 * @date 创建时间：2018年4月29日 下午5:29:14
 */
public interface ArticleDao {

    List<Article> findArticle(@Param("gradeId") String gradeId, @Param("categoryTypeId") String categoryTypeId);

    Article findArticleById(int articleId);

    void addBrowseVolume(int articleId);

    boolean insertArticle(Article article);

    boolean updateArticleById(Article article);

    boolean deleteArticleById(int articleId);

    int getCount(@Param("gradeId") String gradeId, @Param("categoryTypeId") String categoryTypeId);
}
