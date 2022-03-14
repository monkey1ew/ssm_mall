package com.fqh.controller;

import com.fqh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    
}
