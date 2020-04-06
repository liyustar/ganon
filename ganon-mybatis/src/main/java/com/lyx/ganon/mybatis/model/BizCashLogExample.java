package com.lyx.ganon.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizCashLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizCashLogExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccFromIsNull() {
            addCriterion("acc_from is null");
            return (Criteria) this;
        }

        public Criteria andAccFromIsNotNull() {
            addCriterion("acc_from is not null");
            return (Criteria) this;
        }

        public Criteria andAccFromEqualTo(String value) {
            addCriterion("acc_from =", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromNotEqualTo(String value) {
            addCriterion("acc_from <>", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromGreaterThan(String value) {
            addCriterion("acc_from >", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromGreaterThanOrEqualTo(String value) {
            addCriterion("acc_from >=", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromLessThan(String value) {
            addCriterion("acc_from <", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromLessThanOrEqualTo(String value) {
            addCriterion("acc_from <=", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromLike(String value) {
            addCriterion("acc_from like", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromNotLike(String value) {
            addCriterion("acc_from not like", value, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromIn(List<String> values) {
            addCriterion("acc_from in", values, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromNotIn(List<String> values) {
            addCriterion("acc_from not in", values, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromBetween(String value1, String value2) {
            addCriterion("acc_from between", value1, value2, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccFromNotBetween(String value1, String value2) {
            addCriterion("acc_from not between", value1, value2, "accFrom");
            return (Criteria) this;
        }

        public Criteria andAccToIsNull() {
            addCriterion("acc_to is null");
            return (Criteria) this;
        }

        public Criteria andAccToIsNotNull() {
            addCriterion("acc_to is not null");
            return (Criteria) this;
        }

        public Criteria andAccToEqualTo(String value) {
            addCriterion("acc_to =", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToNotEqualTo(String value) {
            addCriterion("acc_to <>", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToGreaterThan(String value) {
            addCriterion("acc_to >", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToGreaterThanOrEqualTo(String value) {
            addCriterion("acc_to >=", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToLessThan(String value) {
            addCriterion("acc_to <", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToLessThanOrEqualTo(String value) {
            addCriterion("acc_to <=", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToLike(String value) {
            addCriterion("acc_to like", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToNotLike(String value) {
            addCriterion("acc_to not like", value, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToIn(List<String> values) {
            addCriterion("acc_to in", values, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToNotIn(List<String> values) {
            addCriterion("acc_to not in", values, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToBetween(String value1, String value2) {
            addCriterion("acc_to between", value1, value2, "accTo");
            return (Criteria) this;
        }

        public Criteria andAccToNotBetween(String value1, String value2) {
            addCriterion("acc_to not between", value1, value2, "accTo");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andAmtIsNull() {
            addCriterion("amt is null");
            return (Criteria) this;
        }

        public Criteria andAmtIsNotNull() {
            addCriterion("amt is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEqualTo(Double value) {
            addCriterion("amt =", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotEqualTo(Double value) {
            addCriterion("amt <>", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThan(Double value) {
            addCriterion("amt >", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("amt >=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThan(Double value) {
            addCriterion("amt <", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThanOrEqualTo(Double value) {
            addCriterion("amt <=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtIn(List<Double> values) {
            addCriterion("amt in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotIn(List<Double> values) {
            addCriterion("amt not in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtBetween(Double value1, Double value2) {
            addCriterion("amt between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotBetween(Double value1, Double value2) {
            addCriterion("amt not between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}