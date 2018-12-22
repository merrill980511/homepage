package com.merrill.dao.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:10
 * Description: 用来存储一个链接相关信息
 */

@Data
public class LinkModule {
    private Long id;

    private String content;

    private String link;

    private Byte location;
}