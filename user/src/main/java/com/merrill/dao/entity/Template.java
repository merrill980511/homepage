package com.merrill.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:06
 * Description: 页面一个大模板的信息
 */

@Data
public class Template implements Serializable {
    //大模块的id
    private Long id;

    //大模块的标题
    private String name;

    //大模块在页面中的位置
    private int location;

    //大模块的具体内容
    private String content;

    //大模块的状态
    private int status;

    //大模块中含有的小模块
    private List<Module> modules;
}