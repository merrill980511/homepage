package com.merrill.dao.shiro;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2018-12-25
 * Time: 23:39
 * Description: 角色表实体，记录角色名以及角色表达式
 */

@Entity
@Table(name = "role")
@Data
public class Role {
    //角色的id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //角色名称
    @Column
    private String name;

    //角色表达式： empMgr
    @Column
    private String info;

    //角色创建日期
    @Column
    private Date createDate;

    //角色创建者
    @Column
    private Long createBy;
}
