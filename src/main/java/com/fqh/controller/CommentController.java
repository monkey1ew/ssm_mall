package com.fqh.controller;

import com.fqh.bean.Comment;
import com.fqh.service.CommentService;
import com.fqh.service.LogisticsService;
import com.fqh.utils.DateUtils;
import com.fqh.utils.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private LogisticsService logisticsService;

    @ResponseBody
    @PostMapping("/subComment")
    public Result subComment(@RequestBody Map<String, Object> map,
                             HttpSession session) {
        Comment comment = new Comment();
        comment.setUserName((String) session.getAttribute("username"));
        comment.setGoodsName((String) map.get("goodsName"));
        comment.setContent((String) map.get("comment"));
        comment.setContentTime(DateUtils.formatDate());
        comment.setStars((String) map.get("star"));
        commentService.insertComment(comment);
//        评价成功删除物流的待评价的信息
        logisticsService.delLogistics((String) map.get("logisNumber"));
        return Result.success();
    }

//    点赞
    @ResponseBody
    @PostMapping("/comment/like")
    public Result likeComment(@RequestBody Map<String, Object> map,
                              HttpSession session) {
        String goodsName = (String) map.get("goodsName");
        String commentator = (String) map.get("commentator");
        String commentTime = (String) map.get("commentTime");
        if (session.getAttribute(commentator) == null || !(boolean)session.getAttribute(commentator)) {
//          点赞  + 1
            commentService.incrLike(goodsName, commentator, commentTime);
//            标记已点赞[bug]
            session.setAttribute(commentator, true);
            return Result.success();
        }
        Result result = Result.failed();
        result.setMessage("你已经点过赞, 不能重复点赞!");
        return result;
    }
}
