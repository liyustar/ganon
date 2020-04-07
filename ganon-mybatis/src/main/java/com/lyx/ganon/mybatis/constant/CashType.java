package com.lyx.ganon.mybatis.constant;

/**
 * @author liyuxing
 */
public enum CashType implements BaseCodeEnum {
    /**
     * UNKNOWN
     */
    UNKNOWN(0),

    /**
     * 入金，存钱
     */
    DEPOSIT(1),

    /**
     * 文章打赏
     */
    ARTICLE_REWARD(2);

    private int code;

    CashType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
