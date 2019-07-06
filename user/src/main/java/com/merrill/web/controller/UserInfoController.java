package com.merrill.web.controller;

import com.merrill.dao.entity.UserInfo;
import com.merrill.service.IUserService;
import com.merrill.util.ShiroUtil;
import com.merrill.web.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-22
 * Time: 14:46
 * Description: 用户个人信息相关的接口
 */

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private IUserService userService;
    @Autowired
    private Status status;

    /**
     * 跳转信息录入页面的方法
     *
     * @return
     */
    @GetMapping("/info/add")
    public String info() {
        return "/setInfo";
    }

    /**
     * 获取用户注册信息的提交结果，并保存到数据库
     *
     * @param userInfo 封装了用户大多数必须信息
     * @param errors   hibernate中的validate验证结果
     * @return
     */
    @PostMapping("/info/addCommit")
    @ResponseBody
    public Object userInfoCommit(@RequestBody UserInfo userInfo, Errors errors) {
        if (errors.hasErrors()) {
            status.setMessage("注册信息有误");
            return status;
        }
        userService.addUserInfo(userInfo);
        status.setMessage("true");
        return status;
    }
}
