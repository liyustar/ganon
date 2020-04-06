package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.controller.request.RewardReq;
import com.lyx.ganon.mybatis.mapper.BizArticleMapper;
import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private BizArticleMapper articleMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public int createArticle(BizArticle article) {
        articleMapper.insertSelective(article);
        return article.getId();
    }

    public void deleteAll() {
        articleMapper.deleteByExample(null);
    }

    public int createComment(int id, BizArticleComment comment) {
        comment.setArticleId(id);
        return commentService.createComment(comment);
    }

    public int reward(int id, RewardReq rewardReq) {
        BizArticle article = articleMapper.selectByPrimaryKey(id);
        return userService.transMoney(rewardReq.getUserId(),
                article.getAuthorId(),
                rewardReq.getAmt(),
                "文章打赏");
    }
}
