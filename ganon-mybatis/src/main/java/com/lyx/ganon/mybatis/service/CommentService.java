package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.mapper.BizArticleCommentMapper;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private BizArticleCommentMapper commentMapper;

    public int createComment(BizArticleComment comment) {
        commentMapper.insertSelective(comment);
        return comment.getId();
    }

    public void deleteAll() {
        commentMapper.deleteByExample(null);
    }
}
