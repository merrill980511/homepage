package com.merrill.dao.entity;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:06
 * Description: 页面一个大模板的信息
 */

@Data
public class Template {
    private Long id;

    private String name;

    private Byte location;

    private Long userId;

    private String content;

    private List<Module> modules;
}