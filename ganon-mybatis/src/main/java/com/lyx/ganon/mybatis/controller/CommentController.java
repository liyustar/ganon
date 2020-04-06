package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @DeleteMapping
    public ResponseObj deleteAll() {
        commentService.deleteAll();
        return ResponseObj.success();
    }
}
