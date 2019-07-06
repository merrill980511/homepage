package com.merrill.web.controller;

import com.merrill.service.IUserService;
import com.merrill.util.ShiroUtil;
import com.merrill.web.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-06-07
 * Time: 15:43
 * Description:
 */

@Controller
@RequestMapping("/user")
public class UserUrlController {
    @Autowired
    private IUserService userService;
    @Autowired
    private Status status;

    /**
     * 跳转接口请求页面的方法
     *
     * @return
     */
    @GetMapping("/url/add")
    public String url() {
        return "/urlApplication";
    }

    /**
     * 根据用户申请的url判断该路径是否可用
     *
     * @param map 封装了url信息
     * @return 该url可用返回true，否则返回错误信息
     */
    @PostMapping("/url/addCommit")
    @ResponseBody
    public Object urlCommit(@RequestBody Map<String, String> map) {
        String url = map.get("url");
        String email = ShiroUtil.getLoginUserEmail();
        if (userService.isUrlExists(url)) {
            status.setMessage("该url已被使用，建议您换一个");
        } else {
            status.setMessage("true");
            userService.editUrl(email, url);
        }
        return status;
    }

    /**
     * 跳转修改url地址页面
     *
     * @return 修改uel地址的页面
     */
    @PostMapping("/url/edit")
    public String urlEdit() {
        return "";
    }

    /**
     * 检查申请的url是否被他人使用
     *
     * @return 若可用，返回true
     */
    @PostMapping("/url/check")
    @ResponseBody
    public Object check(@RequestBody Map<String, String> map) {
        String url = map.get("url");
        if (userService.isUrlExists(url)) {
            status.setMessage("该url已被使用，建议您换一个");
        } else {
            status.setMessage("true");
        }
        return status;
    }
}
