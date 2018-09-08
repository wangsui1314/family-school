package com.bjwk.controller.student;

import com.bjwk.service.student.NoteService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.TokenValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: family-school
 * @description: 个人笔记
 * @author: liqitian.
 * @create: 2018-09-07 13:54
 **/
@Controller
@RequestMapping("api/note")
@Slf4j
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * @Description:创建笔记
     * @Param: [content（内容）, token（令牌）, type（0草稿，1个人完成的笔记，2好友分享的笔记）]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @RequestMapping("/createNote")
    @ResponseBody
    @TokenValidate
    public DataWrapper<Void> createNote(
            @RequestParam(value = "noteTitle", defaultValue = "这是默认标题") String noteTitle,
            @RequestParam(value = "noteContent") String content,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "noteType", defaultValue = "0") Integer type
    ) {
        return noteService.createNote(noteTitle, content, token, type);
    }

    /**
     * @Description:删除笔记
     * @Param: [noteId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @RequestMapping("/deleteNote")
    @ResponseBody
    @TokenValidate
    public DataWrapper<Void> deleteNote(
            @RequestParam(value = "noteTitle") Integer noteId
    ) {
        return noteService.deleteNote(noteId);
    }

    /**
     * @Description:查询笔记
     * @Param: [noteId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/7
     */
    @RequestMapping("/queryNodeList")
    @ResponseBody
    @TokenValidate
    public DataWrapper<Object> queryNodeList(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
            @RequestParam(value = "numberPerPage",defaultValue = "10")int numberPerPage
    ) {
        return noteService.queryNodeList(token,currentPage,numberPerPage);
    }
}
