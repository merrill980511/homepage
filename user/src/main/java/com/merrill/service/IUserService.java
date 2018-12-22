package com.merrill.service;

import com.merrill.dao.entity.UserLogin;

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
     * @param email 邮箱
     * @return 已存在（被注册）返回true，不存在（未被注册）返回false
     */
    boolean isEmailExists(String email);

    /**
     * 根据传入的邮箱、密码注册，返回成功后的相关信息
     * @param user 只有邮箱和密码
     * @return 在原来邮箱密码的基础上加入了id
     */
    UserLogin register(UserLogin user);
}
