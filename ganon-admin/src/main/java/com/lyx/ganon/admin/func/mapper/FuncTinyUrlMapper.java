package com.lyx.ganon.admin.func.mapper;

import com.lyx.ganon.admin.func.model.FuncTinyUrl;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FuncTinyUrlMapper {
    @Delete({
        "delete from func_tiny_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into func_tiny_url (id, path, ",
        "src, created)",
        "values (#{id,jdbcType=INTEGER}, #{path,jdbcType=VARCHAR}, ",
        "#{src,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP})"
    })
    int insert(FuncTinyUrl record);

    @Select({
        "select",
        "id, path, src, created",
        "from func_tiny_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    FuncTinyUrl selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, path, src, created",
        "from func_tiny_url"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FuncTinyUrl> selectAll();

    @Update({
        "update func_tiny_url",
        "set path = #{path,jdbcType=VARCHAR},",
          "src = #{src,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FuncTinyUrl record);
}