package com.bjwk.service.impl.student;

import java.util.List;

import com.bjwk.dao.CategoryDao;
import com.bjwk.dao.CourseLibraryDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.Category;
import com.bjwk.utils.RedisClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.ArticleDao;
import com.bjwk.model.Article;
import com.bjwk.service.student.article.ArticleService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

/**
 * @author Desolation
 * @version 1.0
 * @Description: 美文阅读实现类
 * @email:1071680460@qq.com
 * @date 创建时间：2018年4月29日 下午5:20:58
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private static final Log _logger = LogFactory.getLog(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private RegLoginDao regLoginDao;

    @Autowired
    private CourseLibraryDao courseLibraryDao;


    /**
     * 查询美文阅读实现方法
     *
     * @param token          用户token
     * @param gradeId        年级Id
     * @param categoryTypeId 类型Id
     */
    @Override
    public DataWrapper<PageInfo<Article>> findArticle(String token, String gradeId, String categoryTypeId, int numberPerPage, int currentPage) {
        _logger.info("查询年级id为：" + gradeId + "，类型id为：" + categoryTypeId + "的所有美文");
        DataWrapper<PageInfo<Article>> dataWrapper = new DataWrapper<PageInfo<Article>>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);

        if (numberPerPage <= 0 || currentPage <= 0) {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("传递的数据有误");
            return dataWrapper;
        }
        PageHelper.startPage(currentPage, numberPerPage);
        List<Article> articleList = articleDao.findArticle(gradeId, categoryTypeId);
        if (!CollectionUtils.isEmpty(articleList)) {
            for (Article article : articleList) {
                Integer num = courseLibraryDao.queryIsCollection(userId, article.getArticleId(), 2);
                if (num > 0) {
                    article.setArticleCollection(Boolean.TRUE);
                } else {
                    article.setArticleCollection(Boolean.FALSE);
                }
            }
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("查询成功");
            //dataWrapper.setData(articleList);
            PageInfo<Article> page = new PageInfo<Article>(articleList);
            dataWrapper.setData(page);
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("暂未有该数据");
        }
        return dataWrapper;
    }


    /**
     * 获取美文详情实现方法
     */
    @Override
    public DataWrapper<Article> getArticleDetails(int articleId) {
        _logger.info("获取美文详情");
        DataWrapper<Article> dataWrapper = new DataWrapper<Article>();

        Article articleDetails = articleDao.findArticleById(articleId);
        if (articleDetails != null) {
            articleDao.addBrowseVolume(articleId);
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("获取美文成功");
            dataWrapper.setData(articleDetails);
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("没有该美文");
        }
        return dataWrapper;
    }

    /**
     * 添加美文
     *
     * @param article 美文实体类
     */
    @Override
    @Transactional
    public DataWrapper<Boolean> addArticle(Article article) {
        _logger.info("添加美文");
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        _logger.info("添加的美文数据为：" + article.toString());
        boolean addResult = articleDao.insertArticle(article);
        if (addResult) {
            dataWrapper.setMsg("添加文章成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setData(addResult);
        } else {
            dataWrapper.setMsg("添加文章失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(addResult);
        }
        return dataWrapper;
    }


    /**
     * 修改美文
     *
     * @param article 美文Id
     */
    @Override
    @Transactional
    public DataWrapper<Boolean> updateArticle(Article article) {
        _logger.info("修改美文");
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        _logger.info("修改的美文的内容为：" + article.toString());
        boolean updateResult = articleDao.updateArticleById(article);
        if (updateResult) {
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("修改文章成功");
            dataWrapper.setData(updateResult);
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("修改美文失败");
            dataWrapper.setData(updateResult);
        }
        return dataWrapper;
    }

    /**
     * 删除指定文章
     *
     * @param articleId 文章Id
     * @return
     */
    @Override
    @Transactional
    public DataWrapper<Boolean> deleteArticle(int articleId) {
        _logger.info("删除指定美文");
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        _logger.info("删除的美文的Id为：" + articleId);
        boolean deleteResult = articleDao.deleteArticleById(articleId);
        if (deleteResult) {
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("删除文章成功");
            dataWrapper.setData(deleteResult);
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("删除文章失败");
            dataWrapper.setData(deleteResult);
        }
        return dataWrapper;
    }

    /**
     * 查询美文类别
     **/
    @Override
    public DataWrapper<List<Category>> findCategory() {
        List<Category> categorieList = categoryDao.findCategory();
        DataWrapper<List<Category>> dataWrapper = new DataWrapper<List<Category>>();

        if (CollectionUtils.isEmpty(categorieList)) {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(categorieList);
            dataWrapper.setMsg("美文类型为空");
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("美文阅读类型查找成功");
            dataWrapper.setData(categorieList);
        }
        return dataWrapper;
    }

    /**
     * 收藏美文
     *
     * @param token     登录token
     * @param articleId 美文ID
     **/
    @Override
    @Transactional
    public DataWrapper<Boolean> collectionArticle(String token, String articleId) {
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);

        /*
         * 2 代表美文
         **/
        Integer num = courseLibraryDao.queryIsCollection(userId, Integer.parseInt(articleId), 2);
        if (num > 0) {
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("当前美文已经收藏");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        } else {
            int countLine = courseLibraryDao.insertCollection(userId, Integer.parseInt(articleId), 2);
            if (countLine > 0) {
                dataWrapper.setData(Boolean.TRUE);
                dataWrapper.setMsg("收藏美文成功");
                dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            } else {
                dataWrapper.setData(Boolean.FALSE);
                dataWrapper.setMsg("收藏美文失败");
                dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            }
        }

        return dataWrapper;
    }
}
