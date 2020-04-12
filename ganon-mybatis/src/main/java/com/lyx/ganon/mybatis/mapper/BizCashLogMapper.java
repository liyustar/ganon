package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.constant.CashType;
import com.lyx.ganon.mybatis.model.BizCashLog;
import com.lyx.ganon.mybatis.model.BizCashLogExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BizCashLogMapper {
    @SelectProvider(type=BizCashLogSqlProvider.class, method="countByExample")
    long countByExample(BizCashLogExample example);

    @DeleteProvider(type=BizCashLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizCashLogExample example);

    @Delete({
        "delete from biz_cash_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into biz_cash_log (acc_from, acc_to, ",
        "biz_id, amt, remark, ",
        "created, biz_type)",
        "values (#{accFrom,jdbcType=VARCHAR}, #{accTo,jdbcType=VARCHAR}, ",
        "#{bizId,jdbcType=INTEGER}, #{amt,jdbcType=DOUBLE}, #{remark,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=TIMESTAMP}, #{bizType,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(BizCashLog record);

    @InsertProvider(type=BizCashLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(BizCashLog record);

    @SelectProvider(type=BizCashLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_from", property="accFrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="acc_to", property="accTo", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_id", property="bizId", jdbcType=JdbcType.INTEGER),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.TINYINT)
    })
    List<BizCashLog> selectByExample(BizCashLogExample example);

    @Select({
        "select",
        "id, acc_from, acc_to, biz_id, amt, remark, created, biz_type",
        "from biz_cash_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_from", property="accFrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="acc_to", property="accTo", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_id", property="bizId", jdbcType=JdbcType.INTEGER),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.TINYINT)
    })
    BizCashLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizCashLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizCashLog record, @Param("example") BizCashLogExample example);

    @UpdateProvider(type=BizCashLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizCashLog record, @Param("example") BizCashLogExample example);

    @UpdateProvider(type=BizCashLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizCashLog record);

    @Update({
        "update biz_cash_log",
        "set acc_from = #{accFrom,jdbcType=VARCHAR},",
          "acc_to = #{accTo,jdbcType=VARCHAR},",
          "biz_id = #{bizId,jdbcType=INTEGER},",
          "amt = #{amt,jdbcType=DOUBLE},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "biz_type = #{bizType,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizCashLog record);

    @Select({
            "select",
            "biz_type",
            "from biz_cash_log",
            "where id = #{id,jdbcType=INTEGER}"
    })
    CashType selectCashTypeById(Integer id);
}