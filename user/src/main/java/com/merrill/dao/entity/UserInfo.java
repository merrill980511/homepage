package com.merrill.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: 梅峰鑫
 * Date: 2018-12-21
 * Time: 15:01
 * Description: 存储用户具体的相关信息
 */

@Entity
@Table(name = "user_info")
@Data
public class UserInfo implements Serializable {
    //用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;

    //用户中文姓名
    @Column
    @NotBlank
    private String name;

    //用户英文姓名/
    @Column()
    private String nameEnglish;

    //用户性别/
    @Column
    @NotBlank
    private String gender;

    //用户邮箱
    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    //用户出生日期
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotBlank
    private Date birth;

    //用户联系方式
    @Column
    @NotBlank
    private String phone;

    //用户职称
    @Column
    private String title;

    //用户英文职称
    @Column
    private String titleEnglish;

    //用户头像的路径
    @Column
    private String photo;

    //办公地点
    @Column
    private String office;

    //院系
    @Column
    private String department;

    //英文地点
    @Column
    private String officeEnglish;

    //个人简介
    @Column
    private String description;

    //英文个人简介
    @Column
    private String descriptionEnglish;

    //生成的个人主页的url
    @Column
    private String url;
}