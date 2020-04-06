package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.mapper.BizArticleCommentMapper;
import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizArticleCommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private BizArticleCommentMapper commentMapper;

    public int createComment(BizArticleComment comment) {
        commentMapper.insertSelective(comment);
        return comment.getId();
    }

    public List<BizArticleComment> getComments(Integer id) {
        BizArticleCommentExample example = new BizArticleCommentExample();
        example.createCriteria().andArticleIdEqualTo(id);
        return commentMapper.selectByExample(example);
    }

    public void deleteAll() {
        commentMapper.deleteByExample(null);
    }
}
