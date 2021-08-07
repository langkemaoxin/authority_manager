package com.imooc.project.dto;

import com.imooc.project.entity.Account;
import lombok.Data;

/**
 * 这类存在的目的就是，用作于跳转用的
 */
@Data
public class LoginDto {

    /**
     * 重定向或者跳跳转的路径
     */
    private String path;

    /**
     * 错误提示信息
     */
    private String error;

    /**
     * 当前登录人信息
     */
    private Account account;
}
