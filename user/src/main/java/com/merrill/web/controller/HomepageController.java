package com.merrill.web.controller;

import com.merrill.dao.entity.HomePage;
import com.merrill.service.IHomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-25
 * Time: 10:35
 * Description: 显示整张页面信息的控制器
 */

@Controller
@RequestMapping("/user")
public class HomepageController {
    @Autowired
    private IHomepageService homepageService;

    /**
     * 跳转到显示个人主页页面
     *
     * @return
     */
    @RequestMapping("/homepage/showHomepage")
    @ResponseBody
    public HomePage showHomepage() {
        HomePage homePage = homepageService.getHomepage();
        return homePage;
    }

    /**
     * 跳转到个人主页编辑页面
     *
     * @return
     */
    @RequestMapping("/homepage/homepageGenerate")
    public String homepageGenerate() {
        return "homepageGenerate";
    }
}
