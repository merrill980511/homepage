package com.merrill.web.vo;

import com.merrill.dao.entity.Module;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-24
 * Time: 21:06
 * Description: 用来存储模板的id和各个小模板，方便转换成json
 */

@Data
@Component
public class Modules {
    /**
     * 大模块的id
     */
    private Long templateId;

    /**
     * 小模块信息
     */
    private List<Module> modules;
}
