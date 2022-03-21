package com.fqh.dao;

import com.fqh.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface CommentMapper {

    public int insertComment(@Param("comment")Comment comment);

    public List<Comment> getCommentsByGoodsName(@Param("gName") String goodsName);

    public int incrLike(@Param("gName")String goodsName,
                        @Param("userName") String commentator,
                        @Param("commentTime")String commentTime);
}
