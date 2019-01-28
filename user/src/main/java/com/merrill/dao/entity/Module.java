package com.merrill.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:12
 * Description: 用来存储一个模块相关信息
 */

@Data
public class Module implements Serializable {
    //模块的id
    private Long id;

    //模块的名称
    private String name;

    //模块的具体内容
    private String content;

    //是否是连接
    private Boolean isLink;

    //模块在大模块中的位置
    private int location;

    //模块的状态
    private int status;
}