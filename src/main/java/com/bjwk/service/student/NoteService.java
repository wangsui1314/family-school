package com.bjwk.service.student;

import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: family-school
 * @description: 个人笔记
 * @author: liqitian.
 * @create: 2018-09-07 13:54
 **/
@Controller
@RequestMapping("api/note")
@Slf4j
public interface NoteService {

    DataWrapper<Void> createNote( String noteTitle,String content, String token, Integer type);
}
