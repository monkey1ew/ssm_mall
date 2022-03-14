package com.fqh.dao;

import com.fqh.bean.Comment;
import org.apache.ibatis.annotations.Param;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface CommentMapper {

    public int insertComment(@Param("comment")Comment comment);

}
