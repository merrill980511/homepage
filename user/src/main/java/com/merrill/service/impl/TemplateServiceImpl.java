package com.merrill.service.impl;

import com.merrill.dao.entity.Module;
import com.merrill.dao.entity.Template;
import com.merrill.dao.entity.UserLogin;
import com.merrill.dao.mapper.TemplateMapper;
import com.merrill.dao.mapper.UserMapper;
import com.merrill.service.ITemplateService;
import com.merrill.util.FileUtil;
import com.merrill.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-24
 * Time: 20:09
 * Description: 模块相关的业务逻辑实现类
 */

@Service
@Transactional
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Long saveTemplate(Template template) {
        UserLogin user = userMapper.getUserLoginByEmail(ShiroUtil.getLoginUserEmail());
        if (templateMapper.saveTemplate(template.getName(), template.getContent(),
                template.getLocation(), 0, user.getId()) <= 0) {
            return null;
        }
        List<Module> modules = template.getModules();
        Long id = templateMapper.getLastInsertId();
        if (saveModules(modules, id)){
            return id;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateContent(Long templateId, String content, String name) {
        if (templateMapper.updateContent(templateId, content, name) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateModules(Long templateId, List<Module> modules) {
        if (templateMapper.deleteModuleByTemplateId(templateId) < 0) {
            return false;
        }
        return saveModules(modules, templateId);
    }

    @Override
    public boolean saveModules(List<Module> modules, Long templateId) {
        for (Module module : modules) {
            if (templateMapper.saveModule(module.getName(), module.getContent(),
                    module.getLocation(), 0, templateId) <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Template getTemplateById(Long id) {
        Template template = templateMapper.getTemplateById(id);
        List<Module> list = template.getModules();
        for (Module module : list) {
            if (StringUtils.isNotBlank(module.getName())){
                module.setIsLink(true);
            } else {
                module.setIsLink(false);
            }
        }
        list.sort(new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
                return o1.getLocation() - o2.getLocation();
            }
        });
        return template;
    }

    @Override
    public boolean updateTemplate(Template template) {
        return this.updateContent(template.getId(), template.getContent(), template.getName()) &&
                this.updateModules(template.getId(), template.getModules());
    }

    @Override
    public boolean deleteTemplate(Long templateId) {
        Template t = templateMapper.getTemplateById(templateId);
        int location = t.getLocation();
        UserLogin user = userMapper.getUserLoginByEmail(ShiroUtil.getLoginUserEmail());
        List<Template> list = templateMapper.getTemplateByUserIdAndStatus(user.getId(), 0);
        for (Template template : list) {
            if (template.getLocation() > location){
                templateMapper.updateTemplateLocation(template.getId(), template.getId() - 1);
            }
        }
        templateMapper.deleteTemplateById(templateId);
        return true;
    }

    @Override
    public String saveImage(HttpServletRequest request) {
        try{
            String fileName = FileUtil.upload(request);
            if (StringUtils.isNotBlank(fileName)){
                String email = ShiroUtil.getLoginUserEmail();
                UserLogin user = userMapper.getUserLoginByEmail(email);
                String file = userMapper.getPhotoByEmail(email);
                userMapper.updateImageByUserId(user.getId(), fileName);
                FileUtil.deleteFile(file);
                return "true";
            } else {
                return "添加图片失败";
            }
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
