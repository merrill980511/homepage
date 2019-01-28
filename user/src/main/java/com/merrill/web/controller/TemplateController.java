package com.merrill.web.controller;

import com.merrill.dao.entity.Template;
import com.merrill.service.ITemplateService;
import com.merrill.web.vo.Status;
import com.merrill.web.vo.StatusAndId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-24
 * Time: 19:46
 * Description: 模板信息相关的控制器
 */
@Controller
@RequestMapping("/user")
public class TemplateController {
    @Autowired
    private Status status;
    @Autowired
    private StatusAndId statusAndId;
    @Autowired
    private ITemplateService templateService;

    /**
     * 添加模块信息
     *
     * @param template 整个模块信息
     * @return 返回保存状态和新增的template的id
     */
    @RequestMapping("/template/addTemplate")
    @ResponseBody
    public Object addTemplate(@RequestBody Template template) {
        Long id = templateService.saveTemplate(template);
        if (id != null) {
            statusAndId.setId(id);
            statusAndId.setMessage("true");
        } else {
            statusAndId.setMessage("添加内容失败，请重试。");
        }
        return statusAndId;
    }

    /**
     * 根据模块的id获取整个模块信息
     *
     * @param map 封装了模块的id
     * @return 返回获取到的整个模块
     */
    @RequestMapping("/template/getTemplate")
    @ResponseBody
    public Template getTemplate(@RequestBody Map<String, String> map) {
        Long id = Long.parseLong(map.get("id"));
        return templateService.getTemplateById(id);
    }

    /**
     * 更新前端传过来的模块
     *
     * @param template 封装新模块的所有信息
     * @return 更新信息
     */
    @RequestMapping("/template/updateTemplate")
    @ResponseBody
    public Object updateTemplate(@RequestBody Template template) {
        if (templateService.updateTemplate(template)) {
            status.setMessage("true");
        } else {
            status.setMessage("更新模块失败，请稍后重试");
        }
        return status;
    }

    /**
     * 上传头像接口
     *
     * @param request request请求
     * @return 返回保存状态或者是错误码
     */
    @RequestMapping("/template/saveImage")
    @ResponseBody
    public Object saveImage(HttpServletRequest request
//                            @RequestParam(value = "x") String x,
//                            @RequestParam(value = "y") String y,
//                            @RequestParam(value = "height") String height,
//                            @RequestParam(value = "width") String width
    ) {
        status.setMessage(templateService.saveImage(request));
        return status;
    }

    /**
     * 根据传过来的模块id删除指定模块
     *
     * @param map 封装了模块的id
     * @return 返回删除结果
     */
    @RequestMapping("/template/deleteTemplate")
    @ResponseBody
    public Object deleteTemplate(@RequestBody Map<String, String> map) {
        Long id = Long.parseLong(map.get("id"));
        if (templateService.deleteTemplate(id)) {
            status.setMessage("true");
        } else {
            status.setMessage("删除模块失败，请稍后重试");
        }
        return status;
    }
}
