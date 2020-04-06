package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizArticleCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BizArticleCommentMapper {
    @SelectProvider(type=BizArticleCommentSqlProvider.class, method="countByExample")
    long countByExample(BizArticleCommentExample example);

    @DeleteProvider(type=BizArticleCommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizArticleCommentExample example);

    @Delete({
        "delete from biz_article_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into biz_article_comment (id, article_id, ",
        "creator_id, created, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
        "#{creatorId,jdbcType=INTEGER}, #{created,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(BizArticleComment record);

    @InsertProvider(type=BizArticleCommentSqlProvider.class, method="insertSelective")
    int insertSelective(BizArticleComment record);

    @SelectProvider(type=BizArticleCommentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BizArticleComment> selectByExampleWithBLOBs(BizArticleCommentExample example);

    @SelectProvider(type=BizArticleCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BizArticleComment> selectByExample(BizArticleCommentExample example);

    @Select({
        "select",
        "id, article_id, creator_id, created, content",
        "from biz_article_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    BizArticleComment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizArticleCommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizArticleComment record, @Param("example") BizArticleCommentExample example);

    @UpdateProvider(type=BizArticleCommentSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") BizArticleComment record, @Param("example") BizArticleCommentExample example);

    @UpdateProvider(type=BizArticleCommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizArticleComment record, @Param("example") BizArticleCommentExample example);

    @UpdateProvider(type=BizArticleCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizArticleComment record);

    @Update({
        "update biz_article_comment",
        "set article_id = #{articleId,jdbcType=INTEGER},",
          "creator_id = #{creatorId,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(BizArticleComment record);

    @Update({
        "update biz_article_comment",
        "set article_id = #{articleId,jdbcType=INTEGER},",
          "creator_id = #{creatorId,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizArticleComment record);
}