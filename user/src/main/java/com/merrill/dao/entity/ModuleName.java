package com.merrill.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:12
 * Description: 用来存储提供给用户的默认模块名
 */

@Data
public class ModuleName implements Serializable {
    private Long id;

    private String name;
}