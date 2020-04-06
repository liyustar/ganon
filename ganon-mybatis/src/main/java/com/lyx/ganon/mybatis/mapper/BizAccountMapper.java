package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.model.BizAccount;
import com.lyx.ganon.mybatis.model.BizAccountExample;
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

public interface BizAccountMapper {
    @SelectProvider(type=BizAccountSqlProvider.class, method="countByExample")
    long countByExample(BizAccountExample example);

    @DeleteProvider(type=BizAccountSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizAccountExample example);

    @Delete({
        "delete from ganon_mybatis..biz_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ganon_mybatis..biz_account (id, acc_code, ",
        "user_id, amt, remark, ",
        "created)",
        "values (#{id,jdbcType=INTEGER}, #{accCode,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{amt,jdbcType=DOUBLE}, #{remark,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=TIMESTAMP})"
    })
    int insert(BizAccount record);

    @InsertProvider(type=BizAccountSqlProvider.class, method="insertSelective")
    int insertSelective(BizAccount record);

    @SelectProvider(type=BizAccountSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_code", property="accCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BizAccount> selectByExample(BizAccountExample example);

    @Select({
        "select",
        "id, acc_code, user_id, amt, remark, created",
        "from ganon_mybatis..biz_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_code", property="accCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    BizAccount selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizAccount record, @Param("example") BizAccountExample example);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizAccount record, @Param("example") BizAccountExample example);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizAccount record);

    @Update({
        "update ganon_mybatis..biz_account",
        "set acc_code = #{accCode,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "amt = #{amt,jdbcType=DOUBLE},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizAccount record);
}