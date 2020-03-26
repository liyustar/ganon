package com.lyx.ganon.admin.mapper;

import com.lyx.ganon.admin.model.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SysUserMapper {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sys_user (id, name, ",
        "password, password_md5, ",
        "password_sha)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{passwordMd5,jdbcType=VARCHAR}, ",
        "#{passwordSha,jdbcType=VARCHAR})"
    })
    int insert(SysUser record);

    @Select({
        "select",
        "id, name, password, password_md5, password_sha",
        "from sys_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="password_md5", property="passwordMd5", jdbcType=JdbcType.VARCHAR),
        @Result(column="password_sha", property="passwordSha", jdbcType=JdbcType.VARCHAR)
    })
    SysUser selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, password, password_md5, password_sha",
        "from sys_user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="password_md5", property="passwordMd5", jdbcType=JdbcType.VARCHAR),
        @Result(column="password_sha", property="passwordSha", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUser> selectAll();

    @Update({
        "update sys_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "password_md5 = #{passwordMd5,jdbcType=VARCHAR},",
          "password_sha = #{passwordSha,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUser record);
}