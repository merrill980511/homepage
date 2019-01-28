package com.merrill.service;

import com.merrill.dao.entity.UserInfo;
import com.merrill.dao.entity.UserLogin;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 17:13
 * Description: 用户相关的业务逻辑接口
 */

public interface IUserService {

    /**
     * 根据传入的邮箱判断该邮箱有没有被注册
     *
     * @param email 邮箱
     * @return 已存在（被注册）返回true，不存在（未被注册）返回false
     */
    boolean isEmailExists(String email);

    /**
     * 根据传入的邮箱、密码注册，返回成功后的相关信息
     *
     * @param user 只有邮箱和密码
     * @return 在原来邮箱密码的基础上加入了id
     */
    UserLogin register(UserLogin user);

    /**
     * 根据传入的url地址判断是否已被使用
     *
     * @param url 用户请求的url
     * @return 已存在（被使用）返回true，不存在（未被使用）返回false
     */
    boolean isUrlExists(String url);

    /**
     * 根据页面传入的账号密码登录
     *
     * @param userLogin 封装了用户的账号、密码
     * @return 返回登录结果
     */
    boolean login(UserLogin userLogin);

    /**
     * 将用户输入的相关个人信息保存到数据库
     *
     * @param userInfo 封装了用户的相关信息
     * @return 返回保存结果
     */
    boolean addUserInfo(UserInfo userInfo);

    /**
     * 根据传入的邮箱，判断该邮箱是否已经申请过url
     *
     * @param email 用户的邮箱
     * @return 若已申请，返回true；未申请，返回false
     */
    boolean isAppliedUrl(String email);

    /**
     * 将传入的url地址保存到相应的用户记录中
     *
     * @param email 用户的邮箱
     * @param url   用户申请的url
     * @return 返回保存结果
     */
    boolean addUrl(String email, String url);

    /**
     * 根据传入的邮箱获取用户的头像保存路径
     *
     * @param email 用户邮箱
     * @return 头像保存路径
     */
    String getPhotoByEmail(String email);

    /**
     * 根据传入的邮箱获取头像文件的输出流
     *
     * @param email 用户头像
     */
    void showImage(String email, HttpServletResponse response);
}
