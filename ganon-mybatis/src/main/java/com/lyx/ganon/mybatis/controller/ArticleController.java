package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.controller.request.RewardReq;
import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizCashLog;
import com.lyx.ganon.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseObj<Integer> createArticle(@RequestBody BizArticle article) {
        return ResponseObj.success(articleService.createArticle(article));
    }

    @GetMapping
    public ResponseObj<List<BizArticle>> getArticles() {
        return ResponseObj.success(articleService.getArticles());
    }

    @PostMapping("/{id}/comments")
    public ResponseObj<Integer> createComment(@PathVariable Integer id, @RequestBody BizArticleComment comment) {
        return ResponseObj.success(articleService.createComment(id, comment));
    }

    @GetMapping("/{id}/comments")
    public ResponseObj<List<BizArticleComment>> getComments(@PathVariable Integer id) {
        return ResponseObj.success(articleService.getComments(id));
    }

    @PostMapping("/{id}/rewards")
    public ResponseObj<Integer> createReward(@PathVariable Integer id, @RequestBody RewardReq rewardReq) {
        return ResponseObj.success(articleService.createReward(id, rewardReq));
    }

    @GetMapping("/{id}/rewards")
    public ResponseObj<List<BizCashLog>> getRewards(@PathVariable Integer id) {
        return ResponseObj.success(articleService.getRewards(id));
    }

    @DeleteMapping
    public ResponseObj deleteAll() {
        articleService.deleteAll();
        return ResponseObj.success();
    }
}
