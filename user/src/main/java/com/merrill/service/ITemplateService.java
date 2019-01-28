package com.merrill.service;

import com.merrill.dao.entity.Module;
import com.merrill.dao.entity.Template;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-24
 * Time: 20:09
 * Description: 模块相关的业务逻辑接口
 */

public interface ITemplateService {
    /**
     * 保存传输过来的模块信息
     *
     * @param template 模块信息
     * @return 返回模块的id
     */
    Long saveTemplate(Template template);

    /**
     * 更新模块内容
     *
     * @param templateId 模块的id
     * @param content    模块的内容
     * @return 返回更新结果
     */
    boolean updateContent(Long templateId, String content);

    /**
     * 更新模块中小模块的内容
     *
     * @param templateId 模块的id
     * @param modules    待更新的小模块内容
     * @return 返回更新结果
     */
    boolean updateModules(Long templateId, List<Module> modules);

    /**
     * 保存小模块
     *
     * @param modules    待保存的小模块
     * @param templateId 模块的id
     * @return 返回保存结果
     */
    boolean saveModules(List<Module> modules, Long templateId);

    /**
     * 根据id获取模块对象
     *
     * @param id 模块的id
     * @return 获取到的模块对象
     */
    Template getTemplateById(Long id);

    /**
     * 根据传入的模块更新模块信息
     *
     * @param template 传入的模块
     * @return 返回更新结果
     */
    boolean updateTemplate(Template template);

    /**
     * 根据id删除模块信息
     *
     * @param templateId 模块的id
     * @return 返回删除结果
     */
    boolean deleteTemplate(Long templateId);

    /**
     * 将request中的图像保存到服务器并更新数据库
     *
     * @param request 请求对象
     * @return 保存结果信息
     */
    String saveImage(HttpServletRequest request);
}
