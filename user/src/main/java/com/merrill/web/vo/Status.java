package com.merrill.web.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-21
 * Time: 19:24
 * Description: 用来存储相关的信息，方便转换成json
 */

@Data
@Component
public class Status {
    /**
     * json的key固定为"message"，value根据后端数据处理结果返回
     */
    private String message;
}
