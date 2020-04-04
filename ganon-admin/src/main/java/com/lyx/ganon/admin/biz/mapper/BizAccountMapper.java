package com.lyx.ganon.admin.biz.mapper;

import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.biz.model.BizAccountExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BizAccountMapper {

    @Select({
            "select",
            "id, acc_code, amt, created, version",
            "from biz_account",
            "where acc_code = #{acc_code} for update"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="acc_code", property="accCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
            @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    })
    BizAccount selectOneForUpdate(String acc_code);

    @SelectProvider(type=BizAccountSqlProvider.class, method="countByExample")
    long countByExample(BizAccountExample example);

    @DeleteProvider(type=BizAccountSqlProvider.class, method="deleteByExample")
    int deleteByExample(BizAccountExample example);

    @Delete({
        "delete from biz_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into biz_account (id, acc_code, ",
        "amt, created, version)",
        "values (#{id,jdbcType=INTEGER}, #{accCode,jdbcType=VARCHAR}, ",
        "#{amt,jdbcType=DOUBLE}, #{created,jdbcType=TIMESTAMP}, #{version,jdbcType=INTEGER})"
    })
    int insert(BizAccount record);

    @InsertProvider(type=BizAccountSqlProvider.class, method="insertSelective")
    int insertSelective(BizAccount record);

    @SelectProvider(type=BizAccountSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_code", property="accCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    })
    List<BizAccount> selectByExample(BizAccountExample example);

    @Select({
        "select",
        "id, acc_code, amt, created, version",
        "from biz_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="acc_code", property="accCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="amt", property="amt", jdbcType=JdbcType.DOUBLE),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    })
    BizAccount selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BizAccount record, @Param("example") BizAccountExample example);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BizAccount record, @Param("example") BizAccountExample example);

    @UpdateProvider(type=BizAccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BizAccount record);

    @Update({
        "update biz_account",
        "set acc_code = #{accCode,jdbcType=VARCHAR},",
          "amt = #{amt,jdbcType=DOUBLE},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "version = #{version,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BizAccount record);
}