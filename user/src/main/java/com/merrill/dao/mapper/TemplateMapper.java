package com.merrill.dao.mapper;

import com.merrill.dao.entity.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-01-24
 * Time: 19:10
 * Description:模块相关的数据库映射类
 */

@Repository
public interface TemplateMapper {
    /**
     * 将模块中的信息保存到数据库
     *
     * @param name
     * @param content
     * @param location
     * @param status
     * @param id
     * @return 受影响行数
     */
    int saveTemplate(@Param("name") String name, @Param("content") String content,
                     @Param("location") int location, @Param("status") int status,
                     @Param("userId") Long id);

    /**
     * 获取最后一次添加模块的模块id
     *
     * @return 最后一次添加模块的模块id
     */
    Long getLastInsertId();

    /**
     * 保存小模块信息
     *
     * @param name
     * @param content
     * @param location
     * @param status
     * @param id
     * @return 受影响行数
     */
    int saveModule(@Param("name") String name, @Param("content") String content,
                   @Param("location") int location, @Param("status") int status,
                   @Param("templateId") Long id);

    /**
     * 更新大模块的内容
     *
     * @param templateId 模块id
     * @param content    待更新的内容
     * @param name       待更新的标题
     * @return 受影响行数
     */
    int updateContent(@Param("templateId") Long templateId, @Param("content") String content,
                      @Param("name") String name);

    /**
     * 根据模块的id删除与其关联的所有小模块
     *
     * @param templateId 模块id
     * @return 受影响行数
     */
    int deleteModuleByTemplateId(Long templateId);

    /**
     * 根据模块的id获取模块信息
     *
     * @param id 模块id
     * @return 模块的所有信息
     */
    Template getTemplateById(Long id);

    /**
     * 更新该用户的所有模块信息
     *
     * @param userId 用户id
     * @param status 待更新的所有模块的状态
     * @return 受影响行数
     */
    int updateTemplateStatus(@Param("userId") Long userId, @Param("status") int status);

    /**
     * 更新模块中所有小模块的状态
     *
     * @param templateId 模块id
     * @param status     待更新的小模块状态
     * @return 受影响行数
     */
    int updateModuleStatus(@Param("templateId") Long templateId, @Param("status") int status);

    /**
     * 获取用户的所有模块列表
     *
     * @param userId 用户id
     * @return 模块列表
     */
    List<Long> listTemplateId(Long userId);

    /**
     * 根据用户的id和模块状态获取模块列表
     *
     * @param userId 用户id
     * @param status 模块状态
     * @return 模块列表
     */
    List<Template> getTemplateByUserIdAndStatus(@Param("userId") Long userId, @Param("status") int status);

    /**
     * 更新模块的位置信息
     *
     * @param templateId 模块的id
     * @param location   待更新的位置
     * @return 受影响行数
     */
    int updateTemplateLocation(@Param("templateId") Long templateId, @Param("location") long location);

    /**
     * 根据模块的id删除模块
     *
     * @param templateId 模块id
     * @return 受影响行数
     */
    int deleteTemplateById(Long templateId);
}
