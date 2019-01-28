package com.merrill.util;

import com.merrill.dao.entity.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-21
 * Time: 11:08
 * Description: Shiro相关的工具类
 */
public class ShiroUtil {
    /**
     * 将传入的用户的密码进行多次MD5加密
     * @param user 用户信息
     * @return 返回加密之后的密码
     */
    public static String SysMd5(UserLogin user) {
        String hashAlgorithmName = "MD5";//加密方式

        Object credential = user.getPassword();//密码原值

        ByteSource salt = ByteSource.Util.bytes(user.getEmail());//以账号作为盐值

        int hashIterations = 3;//加密3次

        SimpleHash hash = new SimpleHash(hashAlgorithmName, credential, salt, hashIterations);

        return hash.toString();
    }

    /**
     * 获取已登录用户的邮箱
     * @return
     */
    public static String getLoginUserEmail() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser == null || currentUser.getPrincipal() == null) {
            return "";
        }
        UserLogin user = (UserLogin) currentUser.getPrincipal();
        return user.getEmail();
    }
}
