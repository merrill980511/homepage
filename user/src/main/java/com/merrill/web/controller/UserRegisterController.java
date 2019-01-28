package com.merrill.web.controller;

import com.merrill.dao.entity.UserLogin;
import com.merrill.service.IUserService;
import com.merrill.util.EmailUtil;
import com.merrill.web.vo.Status;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 16:40
 * Description:用户注册相关信息的控制器
 */

@Validated
@Controller
@RequestMapping("/user")
public class UserRegisterController {
    @Autowired
    private IUserService userService;
    @Autowired
    private Status status;

    /**
     * 用户注册接口，跳转到指定的用户注册页面
     *
     * @return 注册页面
     */
    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    /**
     * 用户邮箱提交接口，判断该邮箱有没有被注册
     *
     * @param map 封装了待验证的邮箱
     * @return true表示未被注册，其余信息表示已被注册
     */
    @PostMapping("/register/emailCommit")
    @ResponseBody
    public Object emailCommit(@RequestBody Map<String, String> map) {
        if (userService.isEmailExists(map.get("email"))) {
            status.setMessage("邮箱已被注册");
        } else {
            status.setMessage("true");
        }
        return status;
    }

    /**
     * 验证码发送接口实现类，给指定的邮箱发送8位验证码
     *
     * @param map     封装了用户邮箱
     * @param session 将验证码存储到session中
     * @return 发送成功返回true，否则返回错误信息
     */
    @PostMapping("/register/verificationCodeCommit")
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
     * 提交用户注册信息
     *
     * @param user    将用户信息封装到user参数中
     * @param session 从该session中获取之前生成的验证码
     * @param errors  hibernate中的validate验证结果
     * @return 添加成功返回true
     */
    @PostMapping(value = "/register/commit", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object registerCommit(@RequestBody @Valid UserLogin user, HttpSession session, Errors errors) {
        String randCode = (String) session.getAttribute("CODE_IN_SESSION");
        String code = user.getCode();
        if (!StringUtils.isNotBlank(randCode)) {
            status.setMessage("验证码已失效");
            return status;
        }
        if (!randCode.equalsIgnoreCase(code)) {
            status.setMessage("验证码不匹配");
            return status;
        }
        if (errors.hasErrors()) {
            status.setMessage("注册信息有误");
            return status;
        }
        if (userService.register(user) != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getEmail(),
                            user.getPassword());
            user.setPassword("");
            try {
                subject.login(token);
                status.setMessage("true");
            } catch (Exception e) {
                status.setMessage("操作失败，请手动登录");
            }
        } else {
            status.setMessage("注册失败");
        }
        return status;
    }
}
