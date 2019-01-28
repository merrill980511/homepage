package com.merrill.dao.mapper;

import com.merrill.dao.entity.UserInfo;
import com.merrill.dao.entity.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:26
 * Description: 用户相关的数据库映射类
 */

@Repository
public interface UserMapper {

    /**
     * 根据传入的邮箱查询相关用户
     *
     * @param email 待查询的邮箱
     * @return 返回查询到的用户信息，不存在返回null
     */
    UserLogin getUserLoginByEmail(String email);

    /**
     * 将传入的邮箱、密码添加到user_login表中
     *
     * @param email    用户邮箱
     * @param password 用户密码（多次md5加密之后）
     * @return 返回数据库受影响的行数
     */
    int addUserLogin(@Param("email") String email, @Param("password") String password);

    /**
     * 得到最后一次添加到数据库的用户的id
     *
     * @return Long类型的用户id
     */
    Long getLastInsertId();

    /**
     * 将传入的用户id和邮箱添加到user_info表中
     *
     * @param id    用户id
     * @param email 用户邮箱
     * @return 返回数据库受影响的行数
     */
    int addUserIdByEmail(@Param("id") Long id, @Param("email") String email);

    /**
     * 根据用户传入的url得到该用户的请求信息
     *
     * @param url 用户请求的url
     * @return 返回得到的用户信息，不存在返回null
     */
    UserInfo getUserInfoByUrl(String url);

    /**
     * 根据邮箱查询其url
     *
     * @param email 用户邮箱
     * @return 返回查询到的url
     */
    String getUrlByEmail(String email);

    /**
     * 根据用户邮箱添加相应的yrl
     *
     * @param email 用户邮箱
     * @param url   用户申请的url
     * @return 返回添加结果
     */
    int updateUrlByEmail(@Param("email") String email, @Param("url") String url);

    /**
     * 将用户的详细信息保存到数据库
     *
     * @param email
     * @param name
     * @param nameEnglish
     * @param gender
     * @param title
     * @param titleEnglish
     * @param birth
     * @param phone
     * @param office
     * @param officeEnglish
     * @param department
     * @return
     */
    int addUserInfo(@Param("email") String email,
                    @Param("name") String name, @Param("nameEnglish") String nameEnglish,
                    @Param("gender") String gender,
                    @Param("title") String title, @Param("titleEnglish") String titleEnglish,
                    @Param("birth") Date birth, @Param("phone") String phone,
                    @Param("office") String office, @Param("officeEnglish") String officeEnglish,
                    @Param("department") String department);

    /**
     * 根据用户邮箱获取用户详细信息
     *
     * @param loginUserEmail 用户的邮箱
     * @return 用户详细信息
     */
    UserInfo getUserInfoByEmail(String loginUserEmail);

    /**
     * 根据用户的id更新图片的相对地址
     *
     * @param userId   用户id
     * @param fileName 待更新的图片名
     * @return 返回受影响的行数
     */
    int updateImageByUserId(@Param("userId") Long userId, @Param("fileName") String fileName);

    /**
     * 根据邮箱获取照片的url地址
     *
     * @param email 邮箱
     * @return 返回照片的相对路径
     */
    String getPhotoByEmail(String email);
}
