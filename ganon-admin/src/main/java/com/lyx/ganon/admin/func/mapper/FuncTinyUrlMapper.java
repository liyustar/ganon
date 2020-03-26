package com.lyx.ganon.admin.func.mapper;

import com.lyx.ganon.admin.func.model.FuncTinyUrl;
import com.lyx.ganon.admin.func.model.FuncTinyUrlExample;
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

public interface FuncTinyUrlMapper {
    @SelectProvider(type=FuncTinyUrlSqlProvider.class, method="countByExample")
    long countByExample(FuncTinyUrlExample example);

    @DeleteProvider(type=FuncTinyUrlSqlProvider.class, method="deleteByExample")
    int deleteByExample(FuncTinyUrlExample example);

    @Delete({
        "delete from func_tiny_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into func_tiny_url (id, path, ",
        "src, created, ",
        "md5)",
        "values (#{id,jdbcType=INTEGER}, #{path,jdbcType=VARCHAR}, ",
        "#{src,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP}, ",
        "#{md5,jdbcType=CHAR})"
    })
    int insert(FuncTinyUrl record);

    @InsertProvider(type=FuncTinyUrlSqlProvider.class, method="insertSelective")
    int insertSelective(FuncTinyUrl record);

    @SelectProvider(type=FuncTinyUrlSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="md5", property="md5", jdbcType=JdbcType.CHAR)
    })
    List<FuncTinyUrl> selectByExample(FuncTinyUrlExample example);

    @Select({
        "select",
        "id, path, src, created, md5",
        "from func_tiny_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="md5", property="md5", jdbcType=JdbcType.CHAR)
    })
    FuncTinyUrl selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FuncTinyUrlSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FuncTinyUrl record, @Param("example") FuncTinyUrlExample example);

    @UpdateProvider(type=FuncTinyUrlSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FuncTinyUrl record, @Param("example") FuncTinyUrlExample example);

    @UpdateProvider(type=FuncTinyUrlSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FuncTinyUrl record);

    @Update({
        "update func_tiny_url",
        "set path = #{path,jdbcType=VARCHAR},",
          "src = #{src,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "md5 = #{md5,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FuncTinyUrl record);
}