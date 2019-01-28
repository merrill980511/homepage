package com.merrill.service;

import com.merrill.dao.entity.HomePage;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-26
 * Time: 9:18
 * Description: 个人主页相关的业务逻辑接口
 */
public interface IHomepageService {
    /**
     * 获取主页信息
     * @return 返回主页信息
     */
    HomePage getHomepage();

    /**
     * 根据url获取用户信息
     * @param url url地址
     * @return 返回主页信息
     */
    HomePage getHomepageByUrl(String url);

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱
     * @return 返回主页信息
     */
    HomePage getHomepageByUserEmail(String email);
}
