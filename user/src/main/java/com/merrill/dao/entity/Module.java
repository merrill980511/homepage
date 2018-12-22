package com.merrill.dao.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:12
 * Description: 用来存储一个图片相关信息
 */

public class Module {
    private Long id;

    private String name;

    private Byte location;

    private Long templateId;

    private String content;

    private List<PictureModule> pictureModules;

    private List<LinkModule> linkModules;
}