package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.model.BizArticle;
import com.lyx.ganon.mybatis.model.BizArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BizArticleMapper {
    @SelectProvider(type=BizArticleSqlProvider.class, method="countByExample")
    long countByExample(BizArticleExample example);

    @DeleteProvider(type=BizArticleSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizArticleExample example);

    @Delete({
        "delete from biz_article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into biz_article (title, author_id, ",
        "created, content)",
        "values (#{title,jdbcType=VARCHAR}, #{authorId,jdbcType=INTEGER}, ",
        "#{created,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(BizArticle record);

    @InsertProvider(type=BizArticleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(BizArticle record);

    @SelectProvider(type=BizArticleSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BizArticle> selectByExampleWithBLOBs(BizArticleExample example);

    @SelectProvider(type=BizArticleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BizArticle> selectByExample(BizArticleExample example);

    @Select({
        "select",
        "id, title, author_id, created, content",
        "from biz_article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    BizArticle selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizArticleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizArticle record, @Param("example") BizArticleExample example);

    @UpdateProvider(type=BizArticleSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") BizArticle record, @Param("example") BizArticleExample example);

    @UpdateProvider(type=BizArticleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizArticle record, @Param("example") BizArticleExample example);

    @UpdateProvider(type=BizArticleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizArticle record);

    @Update({
        "update biz_article",
        "set title = #{title,jdbcType=VARCHAR},",
          "author_id = #{authorId,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(BizArticle record);

    @Update({
        "update biz_article",
        "set title = #{title,jdbcType=VARCHAR},",
          "author_id = #{authorId,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizArticle record);
}