package com.fqh.service.impl;

import com.fqh.bean.Comment;
import com.fqh.dao.CommentMapper;
import com.fqh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentsByGoodsName(String goodsName) {
        return commentMapper.getCommentsByGoodsName(goodsName);
    }

    @Override
    public int incrLike(String goodsName, String commentator, String commentTime) {
        return commentMapper.incrLike(goodsName, commentator, commentTime);
    }
}
