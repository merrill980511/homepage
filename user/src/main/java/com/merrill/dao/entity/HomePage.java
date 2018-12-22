package com.merrill.dao.entity;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:15
 * Description:用于存储整张页面的信息
 */

@Data
public class HomePage {
    /**
     * 存储页面上部分的用户信息
     */
    private UserInfo userInfo;

    /**
     * 存储页面下部分的其他相关信息
     */
    private List<Template> templates;
}
