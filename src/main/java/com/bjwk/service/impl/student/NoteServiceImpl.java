package com.bjwk.service.impl.student;

import com.bjwk.dao.NoteDao;
import com.bjwk.service.student.NoteService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @program: family-school
 * @description: 个人笔记逻辑处理
 * @author: liqitian.
 * @create: 2018-09-07 14:31
 **/
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {


    @Autowired
    private NoteDao noteDao;

    /**
     * @Description:创建笔记
     * @Param: [content（内容）, token（令牌）, type（0草稿，1个人完成的笔记，2好友分享的笔记）]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @Override
    public DataWrapper<Void> createNote(String noteTitle, String content, String token, Integer type) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        noteDao.createNote(noteTitle, content, token, type);
        dataWrapper.setMsg("创建成功");
        dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        return dataWrapper;
    }

    /**
     * @Description:
     * @Param: [noteId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @Override
    public DataWrapper<Void> deleteNote(Integer noteId) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        noteDao.deleteNote(noteId);
        dataWrapper.setMsg("删除成功");
        dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        return dataWrapper;
    }

    /**
     * @Description:查询笔记
     * @Param: [noteId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @Override
    public DataWrapper<Object> queryNodeList(String token, int currentPage, int numberPerPage) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        PageHelper.startPage(currentPage, numberPerPage);
        List<HashMap<String, Object>> maplist = noteDao.queryNodeList(token);
        PageInfo<HashMap<String, Object>> page = new PageInfo<HashMap<String, Object>>(maplist);
        dataWrapper.setData(page);
        return dataWrapper;
    }
}
