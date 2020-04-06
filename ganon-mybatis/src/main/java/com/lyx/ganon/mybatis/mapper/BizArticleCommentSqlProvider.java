package com.lyx.ganon.mybatis.mapper;

import com.lyx.ganon.mybatis.model.BizArticleComment;
import com.lyx.ganon.mybatis.model.BizArticleCommentExample.Criteria;
import com.lyx.ganon.mybatis.model.BizArticleCommentExample.Criterion;
import com.lyx.ganon.mybatis.model.BizArticleCommentExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class BizArticleCommentSqlProvider {
    public String countByExample(BizArticleCommentExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("biz_article_comment");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(BizArticleCommentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("biz_article_comment");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(BizArticleComment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("biz_article_comment");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getArticleId() != null) {
            sql.VALUES("article_id", "#{articleId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.VALUES("creator_id", "#{creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreated() != null) {
            sql.VALUES("created", "#{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(BizArticleCommentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("article_id");
        sql.SELECT("creator_id");
        sql.SELECT("created");
        sql.SELECT("content");
        sql.FROM("biz_article_comment");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(BizArticleCommentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("article_id");
        sql.SELECT("creator_id");
        sql.SELECT("created");
        sql.FROM("biz_article_comment");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        BizArticleComment record = (BizArticleComment) parameter.get("record");
        BizArticleCommentExample example = (BizArticleCommentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("biz_article_comment");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getArticleId() != null) {
            sql.SET("article_id = #{record.articleId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.SET("creator_id = #{record.creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreated() != null) {
            sql.SET("created = #{record.created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("biz_article_comment");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("article_id = #{record.articleId,jdbcType=INTEGER}");
        sql.SET("creator_id = #{record.creatorId,jdbcType=INTEGER}");
        sql.SET("created = #{record.created,jdbcType=TIMESTAMP}");
        sql.SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        
        BizArticleCommentExample example = (BizArticleCommentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("biz_article_comment");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("article_id = #{record.articleId,jdbcType=INTEGER}");
        sql.SET("creator_id = #{record.creatorId,jdbcType=INTEGER}");
        sql.SET("created = #{record.created,jdbcType=TIMESTAMP}");
        
        BizArticleCommentExample example = (BizArticleCommentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(BizArticleComment record) {
        SQL sql = new SQL();
        sql.UPDATE("biz_article_comment");
        
        if (record.getArticleId() != null) {
            sql.SET("article_id = #{articleId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.SET("creator_id = #{creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreated() != null) {
            sql.SET("created = #{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, BizArticleCommentExample example, boolean includeExamplePhrase) {
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