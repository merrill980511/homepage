package com.merrill.web.controller;

import com.merrill.dao.entity.HomePage;
import com.merrill.service.IHomepageService;
import com.merrill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-27
 * Time: 13:46
 * Description: 展示主页信息的控制器
 */
@Controller
public class PageController {
    @Autowired
    private IHomepageService homepageService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到主页显示页面
     * @return
     */
    @RequestMapping("/page/*")
    public String page(){
        return "myHomepage";
    }


    @RequestMapping("/pageNotFound")
    public String pageNotFound(){
        return "pageNotFound";
    }

    /**
     * 加载主页信息
     * @param map 封装了需要展示的url信息
     * @return 返回一个页面信息
     */
    @PostMapping("/showHomepage")
    @ResponseBody
    public HomePage myHomepage(@RequestBody Map<String, String> map){
        String url = map.get("url");
        System.out.println(url);
        return homepageService.getHomepageByUrl(url);
    }

    /**
     * 显示图片方法
     * @param request
     * @param response
     */
    @RequestMapping("/showImage")
    @ResponseBody
    public void showImage(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        userService.showImage(email, response);
    }
}
