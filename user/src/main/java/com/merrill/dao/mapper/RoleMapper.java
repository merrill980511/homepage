package com.merrill.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-25
 * Time: 18:50
 * Description: 角色相关映射类
 */

@Repository
public interface RoleMapper {

    List<String> getAllRoleSn();

    List<String> getRoleSnByUserId(Long id);
}
