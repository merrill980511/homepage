package com.merrill.service.impl;

import com.merrill.dao.entity.UserInfo;
import com.merrill.dao.entity.UserLogin;
import com.merrill.dao.mapper.UserMapper;
import com.merrill.service.IUserService;
import com.merrill.util.FileUtil;
import com.merrill.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 17:14
 * Description: 用户相关的业务逻辑的实现类
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        UserLogin login = userMapper.getUserLoginByEmail(email);
        if (login != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserLogin register(UserLogin user) {
        String password = ShiroUtil.SysMd5(user);
        userMapper.addUserLogin(user.getEmail(), password);
        user.setId(userMapper.getLastInsertId());
        Long id = userMapper.getLastInsertId();
        userMapper.addUserIdByEmail(id, user.getEmail());
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUrlExists(String url) {
        if (userMapper.getUserInfoByUrl(url) == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean login(UserLogin userLogin) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(userLogin.getEmail(), userLogin.getPassword());
        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        String email = ShiroUtil.getLoginUserEmail();
        userInfo.setEmail(email);
        if (userMapper.addUserInfo(email, userInfo.getName(), userInfo.getNameEnglish(),
                userInfo.getGender(), userInfo.getTitle(), null, userInfo.getBirth(),
                userInfo.getPhone(), userInfo.getOffice(), null,
                userInfo.getDepartment()) > 0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAppliedUrl(String email) {
        String url = userMapper.getUrlByEmail(email);
        if (StringUtils.isNotBlank(url)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addUrl(String email, String url) {
        if (userMapper.updateUrlByEmail(email, url) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getPhotoByEmail(String email) {
        return userMapper.getPhotoByEmail(email);
    }

    @Override
    public void showImage(String email, HttpServletResponse response) {
        String photo = getPhotoByEmail(email);
        BufferedImage image;
        try {
            if (StringUtils.isNotBlank(photo)){
                image = ImageIO.read(new File(FileUtil.getPath() + photo));
            } else {
                image = ImageIO.read(new File(FileUtil.getPath() + "default.png"));
            }
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
