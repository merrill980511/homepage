package com.merrill.service.impl;

import com.merrill.dao.entity.UserInfo;
import com.merrill.dao.entity.UserLogin;
import com.merrill.dao.mapper.UserMapper;
import com.merrill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 17:14
 * Description: 用户相关的业务逻辑的实现类
 */

@Transactional
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isEmailExists(String email) {
        UserInfo info = userMapper.getUserByEmail(email);
        if (info != null){
            return true;
        }
        return false;
    }

    @Override
    public UserLogin register(UserLogin user) {
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        userMapper.register(user.getEmail(), password);
        return null;
    }
}
