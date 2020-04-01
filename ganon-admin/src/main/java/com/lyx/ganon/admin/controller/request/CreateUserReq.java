package com.lyx.ganon.admin.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Description CreateUserReq
 * @Author liyuxing
 * @Date 2019-12-13
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateUserReq {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
