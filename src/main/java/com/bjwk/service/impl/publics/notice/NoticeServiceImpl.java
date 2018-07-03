package com.bjwk.service.impl.publics.notice;

import com.bjwk.dao.NoticeDao;
import com.bjwk.model.notice.NoticeEntity;
import com.bjwk.service.publics.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 17:50 2018/6/29
 **/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    /**
     * 查看公告详情
     **/
    public NoticeEntity findNoticeDetails(int noticeId) {
        return noticeDao.findNoticeById(noticeId);
    }

    /**
     * 插入公告
     **/
    public void addBrowseVolume(int noticeId) {
        noticeDao.addBrowseVolume(noticeId);
    }

    /**
     * 插入公告
     **/
    public boolean insertNotice(NoticeEntity noticeEntity) {

        return noticeDao.insertNotice(noticeEntity);
    }

    /**
     * 删除指定公告
     **/
    public boolean deleteNoticeById(int noticeId) {

        return noticeDao.deleteNoticeById(noticeId);
    }

    /**
     * 查询所有公告
     **/
    public List<NoticeEntity> findAllNotice() {
        return noticeDao.findAllNotice();
    }

    /**
     * 根据id更改公告信息
     **/
    public boolean updateNoticeById(NoticeEntity noticeEntity) {
        return noticeDao.updateNoticeById(noticeEntity);
    }
}
