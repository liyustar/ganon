package com.lyx.ganon.mybatis.model;

import com.lyx.ganon.mybatis.constant.CashType;
import java.util.Date;

public class BizCashLog {
    private Integer id;

    private String accFrom;

    private String accTo;

    private Integer bizId;

    private Double amt;

    private String remark;

    private Date created;

    private CashType bizType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccFrom() {
        return accFrom;
    }

    public void setAccFrom(String accFrom) {
        this.accFrom = accFrom;
    }

    public String getAccTo() {
        return accTo;
    }

    public void setAccTo(String accTo) {
        this.accTo = accTo;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public CashType getBizType() {
        return bizType;
    }

    public void setBizType(CashType bizType) {
        this.bizType = bizType;
    }
}