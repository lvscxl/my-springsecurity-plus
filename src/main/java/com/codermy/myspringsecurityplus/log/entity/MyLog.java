package com.codermy.myspringsecurityplus.log.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author codermy
 * @createTime 2020/8/4
 */
@Data
public class MyLog implements Serializable {

    private Long id;

    /** 操作用户 */
    private String userName;

    /** 描述 */
    private String description;

    /** 方法名 */
    private String method;

    /** 请求ip */
    private String ip;

    // /** 地址 */
    // private String ipAddress;
    /** 参数*/
    private String params;

    /** 浏览器  */
    private String browser;

    /** 请求耗时 */
    private Long time;

    /** 创建日期 */
    private Date createTime = new Date();

    public MyLog( Long time) {
        this.time = time;
    }

}