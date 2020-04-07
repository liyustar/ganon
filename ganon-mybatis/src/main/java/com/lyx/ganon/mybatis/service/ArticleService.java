package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.constant.CashType;
import com.lyx.ganon.mybatis.controller.request.RewardReq;
import com.lyx.ganon.mybatis.mapper.BizArticleMapper;
import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizCashLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private BizArticleMapper articleMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CashLogService cashLogService;

    public int createArticle(BizArticle article) {
        articleMapper.insertSelective(article);
        return article.getId();
    }

    public List<BizArticle> getArticles() {
        return articleMapper.selectByExample(null);
    }

    public void deleteAll() {
        articleMapper.deleteByExample(null);
    }

    public int createComment(int id, BizArticleComment comment) {
        comment.setArticleId(id);
        return commentService.createComment(comment);
    }

    public List<BizArticleComment> getComments(Integer id) {
        return commentService.getComments(id);
    }

    public int createReward(int id, RewardReq rewardReq) {
        BizArticle article = articleMapper.selectByPrimaryKey(id);
        return userService.transMoney(rewardReq.getUserId(),
                article.getAuthorId(),
                rewardReq.getAmt(),
                CashType.ARTICLE_REWARD,
                article.getId(),
                "文章打赏");
    }

    public List<BizCashLog> getRewards(Integer id) {
        return cashLogService.getCashLogs(id, CashType.ARTICLE_REWARD);
    }
}
