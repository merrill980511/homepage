package com.merrill.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 15:42
 * Description: 用来存储用户登录的相关信息
 */

@Table(name = "user_login")
@Data
public class UserLogin implements Serializable {
    @Id
    private Long id;

    @Column
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式有误")
    private String email;

    @Column
    @NotBlank(message = "密码不能为空")
    private String password;

    @Column
    private int status;

    private String code;
}
