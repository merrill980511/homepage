package com.merrill.web.controller;

import com.merrill.dao.entity.UserLogin;
import com.merrill.service.IUserService;
import com.merrill.util.EmailUtil;
import com.merrill.web.vo.Status;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
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
 * Description:用户相关信息的控制器
 */

@Validated
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private Status status;


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/emailCommit")
    @ResponseBody
    public Object emailCommit(@RequestBody Map<String, String> map) {
        if (userService.isEmailExists(map.get("email"))) {
            status.setMessage("邮箱已被注册");
        }
        status.setMessage("true");
        return status;
    }

    @PostMapping("/verificationCodeCommit")
    @ResponseBody
    public Object verificationCodeCommit(@RequestBody Map<String, String> map, HttpSession session) {
        String randCode = EmailUtil.randCode(8);
        String email = map.get("email");
        System.out.println(email);
        session.setAttribute("CODE_IN_SESSION", randCode);
        session.setMaxInactiveInterval(5*60);
        if (!EmailUtil.sendEmail(email, randCode)) {
            status.setMessage("验证码发送失败");
        }
        status.setMessage("true");
        return status;
    }

    @PostMapping(value="/registerCommit", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object registerCommit(@RequestBody @Valid UserLogin user, HttpSession session, Errors errors) {
        String randCode = (String) session.getAttribute("CODE_IN_SESSION");
        String code = user.getCode();
        if (!StringUtils.isNotBlank(randCode)){
            status.setMessage("验证码已失效");
            return status;
        }
        if (!randCode.equalsIgnoreCase(code)) {
            status.setMessage("验证码不匹配");
            return status;
        }
        if (errors.hasErrors()) {
            status.setMessage("注册失败");
            return status;
        }
        userService.register(user);
        status.setMessage("true");
        return status;
    }
}
