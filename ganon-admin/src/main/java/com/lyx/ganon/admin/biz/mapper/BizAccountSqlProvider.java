package com.lyx.ganon.admin.biz.mapper;

import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.biz.model.BizAccountExample.Criteria;
import com.lyx.ganon.admin.biz.model.BizAccountExample.Criterion;
import com.lyx.ganon.admin.biz.model.BizAccountExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class BizAccountSqlProvider {
    public String countByExample(BizAccountExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("biz_account");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(BizAccountExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("biz_account");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(BizAccount record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("biz_account");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAccCode() != null) {
            sql.VALUES("acc_code", "#{accCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAmt() != null) {
            sql.VALUES("amt", "#{amt,jdbcType=DOUBLE}");
        }
        
        if (record.getCreated() != null) {
            sql.VALUES("created", "#{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("version", "#{version,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(BizAccountExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("acc_code");
        sql.SELECT("amt");
        sql.SELECT("created");
        sql.SELECT("version");
        sql.FROM("biz_account");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        BizAccount record = (BizAccount) parameter.get("record");
        BizAccountExample example = (BizAccountExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("biz_account");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAccCode() != null) {
            sql.SET("acc_code = #{record.accCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAmt() != null) {
            sql.SET("amt = #{record.amt,jdbcType=DOUBLE}");
        }
        
        if (record.getCreated() != null) {
            sql.SET("created = #{record.created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{record.version,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("biz_account");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("acc_code = #{record.accCode,jdbcType=VARCHAR}");
        sql.SET("amt = #{record.amt,jdbcType=DOUBLE}");
        sql.SET("created = #{record.created,jdbcType=TIMESTAMP}");
        sql.SET("version = #{record.version,jdbcType=INTEGER}");
        
        BizAccountExample example = (BizAccountExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(BizAccount record) {
        SQL sql = new SQL();
        sql.UPDATE("biz_account");
        
        if (record.getAccCode() != null) {
            sql.SET("acc_code = #{accCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAmt() != null) {
            sql.SET("amt = #{amt,jdbcType=DOUBLE}");
        }
        
        if (record.getCreated() != null) {
            sql.SET("created = #{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, BizAccountExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}