package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

public interface NoteDao {


    int createNote(@Param("noteTitle")String noteTitle,@Param("noteContent") String content, @Param("token")String token, @Param("noteType")Integer type);
}
