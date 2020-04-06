package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.model.BizUser;
import com.lyx.ganon.mybatis.model.BizUserExample;
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

public interface BizUserMapper {
    @SelectProvider(type=BizUserSqlProvider.class, method="countByExample")
    long countByExample(BizUserExample example);

    @DeleteProvider(type=BizUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizUserExample example);

    @Delete({
        "delete from biz_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into biz_user (id, name, ",
        "password, created)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP})"
    })
    int insert(BizUser record);

    @InsertProvider(type=BizUserSqlProvider.class, method="insertSelective")
    int insertSelective(BizUser record);

    @SelectProvider(type=BizUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BizUser> selectByExample(BizUserExample example);

    @Select({
        "select",
        "id, name, password, created",
        "from biz_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    BizUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizUser record, @Param("example") BizUserExample example);

    @UpdateProvider(type=BizUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizUser record, @Param("example") BizUserExample example);

    @UpdateProvider(type=BizUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizUser record);

    @Update({
        "update biz_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizUser record);
}