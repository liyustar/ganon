package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.controller.request.RewardReq;
import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseObj<Integer> createArticle(@RequestBody BizArticle article) {
        return ResponseObj.success(articleService.createArticle(article));
    }

    @PostMapping("/{id}/comments")
    public ResponseObj<Integer> createComment(@PathVariable Integer id, @RequestBody BizArticleComment comment) {
        return ResponseObj.success(articleService.createComment(id, comment));
    }

    @PostMapping("/{id}/reward")
    public ResponseObj<Integer> reward(@PathVariable Integer id, @RequestBody RewardReq rewardReq) {
        return ResponseObj.success(articleService.reward(id, rewardReq));
    }

    @DeleteMapping
    public ResponseObj deleteAll() {
        articleService.deleteAll();
        return ResponseObj.success();
    }
}
