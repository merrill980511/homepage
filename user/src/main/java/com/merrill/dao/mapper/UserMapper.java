package com.merrill.dao.mapper;

import com.merrill.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:26
 * Description: 用户相关的数据库映射类
 */

@Repository
public interface UserMapper {

    UserInfo getUserByEmail(String email);

    UserInfo register(@Param("email") String email, @Param("password") String password);
}
