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
     * 跳转接口请求页面的方法
     *
     * @return
     */
    @GetMapping("/url")
    public String url() {
        return "/urlApplication";
    }

    /**
     * 根据用户申请的url判断该路径是否可用
     *
     * @param map 封装了url信息
     * @return 该url可用返回true，否则返回错误信息
     */
    @PostMapping("/url/commit")
    @ResponseBody
    public Object urlCommit(@RequestBody Map<String, String> map) {
        String url = map.get("url");

        String email = ShiroUtil.getLoginUserEmail();
        if (userService.isAppliedUrl(email)) {
            status.setMessage("您已经申请过了，不可以再申请了");
            return status;
        }

        if (userService.isUrlExists(url)) {
            status.setMessage("该url已被使用，建议您换一个");
        } else {
            status.setMessage("true");
            userService.addUrl(email, url);
        }
        return status;
    }


    /**
     * 跳转信息录入页面的方法
     *
     * @return
     */
    @GetMapping("/info")
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
    @PostMapping("/info/commit")
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
