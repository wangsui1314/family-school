package com.bjwk.service.publics.notice;

import com.bjwk.model.notice.NoticeEntity;

import java.util.List;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 17:49 2018/6/29
 **/
public interface NoticeService {

    /**
     * 查看公告详情
     **/
    NoticeEntity findNoticeDetails(int noticeId);

    /**
     * 增加公告阅读量
     **/
    void addBrowseVolume(int noticeId);

    /**
     * 插入公告
     **/
    boolean insertNotice(NoticeEntity noticeEntity);

    /**
     * 删除指定公告
     **/
    boolean deleteNoticeById(int noticeId);

    /**
     * 查询所有公告
     **/
    List<NoticeEntity> findAllNotice();

    /**
     * 根据id更改公告信息
     **/
    boolean updateNoticeById(NoticeEntity noticeEntity);

}