package com.merrill.dao.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:08
 * Description: 用来存储一个图片相关信息
 */

@Data
public class PictureModule {
    private Long id;

    private String url;

    private String content;

    private Byte location;
}