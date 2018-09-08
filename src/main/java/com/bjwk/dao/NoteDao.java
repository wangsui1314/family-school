package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface NoteDao {


    int createNote(@Param("noteTitle")String noteTitle,@Param("noteContent") String content, @Param("token")String token, @Param("noteType")Integer type);

    int deleteNote(@Param("noteId") Integer noteId);

    List<HashMap<String,Object>> queryNodeList(String token);
}
