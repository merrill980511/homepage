package com.merrill.dao.shiro;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-25
 * Time: 23:40
 * Description: 权限表对应的实体，记录权限名和权限表达式
 */

@Entity
@Table(name = "permission")
@Data
public class Permission {
    //权限id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //权限名称
    @Column
    private String name;

    //资源表达式xx:xx  比如：employee:list
    @Column
    private String permission;

    //权限说明
    @Column
    private String info;

    //权限创建日期
    @Column
    private Date createDate;

    //权限创建者
    @Column
    private Long createBy;
}
