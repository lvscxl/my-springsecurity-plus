package com.codermy.myspringsecurityplus.entity;

import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Data
public class MyRoleUser {
    private static final long serialVersionUID = 8545514045582235838L;
    private Integer userId;
    private Integer roleId;
}
