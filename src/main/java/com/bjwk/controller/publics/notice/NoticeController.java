package com.bjwk.controller.publics.notice;

import com.bjwk.model.notice.NoticeEntity;
import com.bjwk.service.publics.notice.NoticeService;
import com.bjwk.utils.CallStatusEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bjwk.utils.DataWrapper;

import java.util.List;


/**
 * @author Desolation
 * @version 1.0
 * @Description: 公告Controller
 * @email:1071680460@qq.com
 * @date 创建时间：2018年5月1日 上午12:21:11
 */
@Controller
@RequestMapping("api/public/notice")
public class NoticeController {

    private static Logger _logger = Logger.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询公告
     **/
    @RequestMapping("_pageNotice")
    @ResponseBody
    public DataWrapper<List> pageNotice() {
        _logger.info(this.getClass().getSimpleName()+" 查询所有公告(最近10条)");
        List<NoticeEntity> list = noticeService.findAllNotice();
        DataWrapper<List> dataWrapper = new DataWrapper<List>();
        if (list == null) {
            dataWrapper.setMsg("当前没有公告");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        } else {
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("查询公告成功");
            dataWrapper.setData(list);
        }
        return dataWrapper;
    }

    /**
     * 添加公告
     **/
    @RequestMapping("_saveNotice")
    @ResponseBody
    public DataWrapper<Boolean> addNotice(@ModelAttribute(value = "noticeEntity") NoticeEntity noticeEntity) {
        _logger.info("添加公告" + noticeEntity);
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        if (noticeEntity.getNoticeId() >= 1) {
            boolean b = noticeService.updateNoticeById(noticeEntity);
            if (b) {
                dataWrapper.setMsg("更改公告成功");
                dataWrapper.setData(Boolean.TRUE);
                dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
                return dataWrapper;
            }
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("更改公告失败");
            return dataWrapper;
        }
        boolean result = noticeService.insertNotice(noticeEntity);
        if (result) {
            dataWrapper.setMsg("添加公告成功");
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            return dataWrapper;
        }
        dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        dataWrapper.setData(Boolean.FALSE);
        dataWrapper.setMsg("添加公告失败");
        return dataWrapper;
    }

    /**
     * 公告详情
     **/
    @RequestMapping("_noticeDetails")
    @ResponseBody
    public DataWrapper<NoticeEntity> findNoticeDetails(@RequestParam(required = false, defaultValue = "0") int noticeId) {
        _logger.info("获取公告详情" + noticeId);
        DataWrapper<NoticeEntity> dataWrapper = new DataWrapper<NoticeEntity>();
        if (noticeId < 1) {
            dataWrapper.setMsg("公告id有误");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(null);
        } else {
            NoticeEntity entity = noticeService.findNoticeDetails(noticeId);
            _logger.info(entity);
            if (entity != null) {
                noticeService.addBrowseVolume(noticeId);
                dataWrapper.setData(entity);
                dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
                dataWrapper.setMsg("查询公告详情正确");
            } else {
                dataWrapper.setMsg("没有找到该公告");
                dataWrapper.setCallStatus(CallStatusEnum.FAILED);
                dataWrapper.setData(null);
            }
        }
        return dataWrapper;
    }

    /**
     * 删除指定公告
     **/
    @RequestMapping(value = "_deleteNotice")
    @ResponseBody
    public DataWrapper<Boolean> deleteNotice(@RequestParam int noticeId) {
        _logger.info("删除指定公告" + noticeId);
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        if(noticeId < 1){
            dataWrapper.setMsg("公告id传递有误");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(Boolean.FALSE);
            return dataWrapper;
        }
        boolean result = noticeService.deleteNoticeById(noticeId);
        if (result) {
            dataWrapper.setMsg("删除指定公告成功");
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            return dataWrapper;
        }
        dataWrapper.setMsg("删除公告失败");
        dataWrapper.setData(Boolean.FALSE);
        dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        return dataWrapper;
    }
}
