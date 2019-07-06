package com.merrill.web.controller;

import com.merrill.dao.entity.UserLogin;
import com.merrill.service.IUserService;
import com.merrill.util.EmailUtil;
import com.merrill.util.ShiroUtil;
import com.merrill.web.vo.Status;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-26
 * Time: 13:59
 * Description: 用户登录、登出、注销相关信息的控制器
 */

@Controller
@RequestMapping("/user")
public class UserController {
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

    @RequestMapping("/logout")
    public String logout() {
        userService.logout();
        return "login";
    }

    /**
     * 验证码发送接口实现类，给指定的邮箱发送8位验证码
     *
     * @param map     封装了用户邮箱
     * @param session 将验证码存储到session中
     * @return 发送成功返回true，否则返回错误信息
     */
    @PostMapping("/verificationCode")
    @ResponseBody
    public Object verificationCodeCommit(@RequestBody Map<String, String> map, HttpSession session) {
        String randCode = EmailUtil.randCode(8);
        String email = map.get("email");
        System.out.println(email);
        session.setAttribute("CODE_IN_SESSION", randCode);
        session.setMaxInactiveInterval(5 * 60);
        if (EmailUtil.sendEmail(email, randCode)) {
            status.setMessage("true");
        } else {
            status.setMessage("验证码发送失败");
        }
        return status;
    }

    /**
     * 注销用户信息
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/logoff")
    @ResponseBody
    public Object logoff(@RequestBody Map<String, String> map, HttpSession session) {
        String randCode = (String) session.getAttribute("CODE_IN_SESSION");
        String code = map.get("code");
        if (!StringUtils.isNotBlank(randCode)) {
            status.setMessage("验证码已失效");
            return status;
        }
        if (!randCode.equalsIgnoreCase(code)) {
            status.setMessage("验证码不匹配");
            return status;
        } else {
            userService.logout();
//            userService.logoff();
            status.setMessage("true");
            return status;
        }
    }
}
