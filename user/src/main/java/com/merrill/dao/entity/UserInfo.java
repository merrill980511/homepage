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
 * Description: 存储用户所有的相关信息
 */

@Entity
@Table(name = "user_info")
@Data
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    private String nameEnglish;

    @Column
    @NotBlank
    private String gender;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotBlank
    private Date birth;

    @Column
    @NotBlank
    private String phone;

    @Column
    private String title;

    @Column
    private String photo;

    @Column
    private String office;

    @Column
    private String officeEnglish;

    @Column
    private String description;

    @Column
    private String descriptionEnglish;

    @Column
    private String url;
}