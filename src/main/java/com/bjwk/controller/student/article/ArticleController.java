package com.bjwk.controller.student.article;

import com.bjwk.model.Category;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.Article;
import com.bjwk.service.student.article.ArticleService;
import com.bjwk.utils.DataWrapper;

import java.util.List;

/**
 * @author Desolation
 * @version 1.0
 * @Description: 美文阅读Controller层
 * @email:1071680460@qq.com
 * @date 创建时间：2018年4月29日 下午5:15:32
 */
@Controller
@RequestMapping("api/student/article")
public class ArticleController {

    private static final Log _logger = LogFactory.getLog(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 查询指定条件的所有美文
     *
     * @param gradeId 年级id
     * @param typeId  类型id
     * @return
     */
    @RequestMapping(value = "_findArticle")
    @ResponseBody
    public DataWrapper<PageInfo<Article>> findArticle(
            String gradeId, String typeId,String token,
            @RequestParam(value = "numberPerPage", defaultValue = "1") Integer numberPerPage,
            @RequestParam(value = "currentPage", defaultValue = "10") Integer currentPage)
    {
        _logger.info("查询指定条件的所有美文");
        _logger.info("查询用户token为："+token+",年级id为：" + gradeId + "，类型id为：" + typeId + "的所有美文");
        return articleService.findArticle(token,gradeId,typeId, numberPerPage, currentPage);
    }

    /**
     * 获取美文详情
     *
     * @param articleId 美文id
     * @return
     */
    @RequestMapping(value = "_getArticleDetails")
    @ResponseBody
    public DataWrapper<Article> getArticleDetails(String articleId) {
        _logger.info("获取美文详情，接收的美文Id为：" + articleId);

        return articleService.getArticleDetails(Integer.parseInt(articleId));
    }

    /**
     * 添加美文
     *
     * @param article 美文实体类
     * @return
     */
    @RequestMapping(value = "_addArticle")
    @ResponseBody
    public DataWrapper<Boolean> addArticle(@ModelAttribute(value = "article") Article article) {
        _logger.info("添加文章，数据为：" + article.toString());
        return articleService.addArticle(article);
    }

    /**
     * 修改美文
     *
     * @param article 美文实体类
     * @return
     */
    @RequestMapping(value = "_updateArticle")
    @ResponseBody
    public DataWrapper<Boolean> updateArticle(@ModelAttribute(value = "article") Article article) {
        _logger.info("修改美文，数据为：" + article.toString());
        return articleService.updateArticle(article);
    }

    /**
     * 删除美文
     *
     * @param articleId 美文Id
     * @return
     */
    @RequestMapping(value = "_deleteArticle")
    @ResponseBody
    public DataWrapper<Boolean> deleteArticle(String articleId) {
        _logger.info("删除美文 ，删除的Id为：" + articleId);
        return articleService.deleteArticle(Integer.parseInt(articleId));
    }

    /**
     * 查找美文类别
     *
     * @return
     */
    @RequestMapping(value = "category/_search")
    @ResponseBody
    public DataWrapper<List<Category>> findCategory() {
        _logger.info("查找美文类别" );
        return articleService.findCategory();
    }

    /**
     * 收藏美文
     **/
    @RequestMapping(value = "collection")
    @ResponseBody
    public DataWrapper<Boolean> collectionArticle(String token, String articleId){
        _logger.info("用户收藏美文");
        _logger.info("接收的美文：token:"+token + "美文Id:"+articleId);
        return this.articleService.collectionArticle(token,articleId);
    }
}
