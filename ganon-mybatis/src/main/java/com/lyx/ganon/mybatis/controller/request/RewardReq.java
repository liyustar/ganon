package com.lyx.ganon.mybatis.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RewardReq {

    private Integer articleId;
    private Integer userId;
    private Double amt;
}
