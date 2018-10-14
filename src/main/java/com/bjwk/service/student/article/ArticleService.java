package com.bjwk.service.student.article;

import java.util.List;

import com.bjwk.model.Article;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

/** 
* @Description: 美文阅读实现类接口
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月29日 下午5:19:47 
* @version 1.0  
*/
public interface ArticleService {

	/**
	 * @Desc : 查询美文列表
	 * @param categoryType 美文查询字段
	 * @return :
	 **/
	 DataWrapper<PageInfo<Article>> findArticle(String categoryType, int numberPerPage, int currentPage);

	 DataWrapper<Article> getArticleDetails(int articleId);
	
	 DataWrapper<Boolean> addArticle(Article article);
	
	 DataWrapper<Boolean> updateArticle(Article article);
	
	 DataWrapper<Boolean> deleteArticle(int articleId);

}
