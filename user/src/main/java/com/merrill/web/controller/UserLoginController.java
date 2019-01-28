package com.merrill.web.controller;

import com.merrill.dao.entity.UserLogin;
import com.merrill.service.IUserService;
import com.merrill.web.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-26
 * Time: 13:59
 * Description: 用户登录相关信息的控制器
 */

@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private Status status;
    @Autowired
    private IUserService userService;

    /**
     * 跳转登录页面的方法
     *
     * @return 返回登录页面的url
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录页面信息提交接口
     *
     * @param userLogin 封装了用户的账号（邮箱，但是在实体类中式username）、密码
     * @param errors    hibernate中的validate验证结果
     * @return 登录验证成功返回true，否则返回相关错误信息
     */
    @RequestMapping("/login/commit")
    @ResponseBody
    public Object loginCommit(@RequestBody UserLogin userLogin, Errors errors) {
        if (errors.hasErrors()) {
            status.setMessage("信息格式有误");
            return status;
        }
        if (userService.login(userLogin)) {
            status.setMessage("true");
        } else {
            status.setMessage("账号不存在或者密码错误");
        }
        return status;
    }
}
