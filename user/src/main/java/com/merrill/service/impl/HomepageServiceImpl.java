package com.merrill.service.impl;

import com.merrill.dao.entity.*;
import com.merrill.dao.mapper.TemplateMapper;
import com.merrill.dao.mapper.UserMapper;
import com.merrill.service.IHomepageService;
import com.merrill.util.FileUtil;
import com.merrill.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-26
 * Time: 9:19
 * Description: 个人主页相关的业务逻辑实现类
 */
@Service
@Transactional
public class HomepageServiceImpl implements IHomepageService {
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public HomePage getHomepage() {
        return getHomepageByUserEmail(ShiroUtil.getLoginUserEmail());
    }

    @Override
    public HomePage getHomepageByUrl(String url) {
        UserInfo info = userMapper.getUserInfoByUrl(url);
        return getHomepageByUserEmail(info.getEmail());
    }

    @Override
    public HomePage getHomepageByUserEmail(String email) {
        UserInfo user = userMapper.getUserInfoByEmail(email);
        HomePage homePage = new HomePage();
        homePage.setUserInfo(user);
        List<Template> list = templateMapper.getTemplateByUserIdAndStatus(user.getId(), 0);
        list.sort(new Comparator<Template>() {
            @Override
            public int compare(Template o1, Template o2) {
                return o1.getLocation() - o2.getLocation();
            }
        });
        for (Template template : list) {
            List<Module> modules = template.getModules();
            for (Module module : modules) {
                if (StringUtils.isNotBlank(module.getName())){
                    module.setIsLink(true);
                } else {
                    module.setIsLink(false);
                }
            }
            modules.sort(new Comparator<Module>() {
                @Override
                public int compare(Module o1, Module o2) {
                    return o1.getLocation() - o2.getLocation();
                }
            });
        }
        homePage.setTemplates(list);
        if (StringUtils.isNotBlank(homePage.getUserInfo().getUrl())){
            homePage.getUserInfo().setUrl(FileUtil.getPath() + "default.png");
        }
        return homePage;
    }
}
