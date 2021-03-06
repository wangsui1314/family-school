package com.bjwk.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Desolation
 * @version 1.0
 * @Description: 美文阅读实体类
 * @email:1071680460@qq.com
 * @date 创建时间：2018年4月29日 下午5:29:35
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private int articleId;

    private String articleTitle;

    private String titleImg;

    private String articleContent;

    private String articleAuthor;

    private String articleFrom;

    private Date createTime;

    private Date updateTime;

    private int articleDownload;

    private int browseVolume;

    private String gradeId;

    private String categoryTypeId;

    private boolean articleCollection;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleFrom() {
        return articleFrom;
    }

    public void setArticleFrom(String articleFrom) {
        this.articleFrom = articleFrom;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public int getArticleDownload() {
        return articleDownload;
    }

    public void setArticleDownload(int articleDownload) {
        this.articleDownload = articleDownload;
    }

    public int getBrowseVolume() {
        return browseVolume;
    }

    public void setBrowseVolume(int browseVolume) {
        this.browseVolume = browseVolume;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public boolean isArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(boolean articleCollection) {
        this.articleCollection = articleCollection;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleFrom='" + articleFrom + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", articleDownload=" + articleDownload +
                ", browseVolume=" + browseVolume +
                ", gradeId='" + gradeId + '\'' +
                ", categoryTypeId='" + categoryTypeId + '\'' +
                ", articleCollection=" + articleCollection +
                '}';
    }
}
