package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //ID
    private String username; //用户名
    private String password; //密码
    private String name; //昵称
    private String phone; //手机号
    private Short sex; //性别 , 1 男, 2 女
    private String avatar; //头像url
    private Short status; //状态 0 启用 1 禁用
    private String idNumber; //身份证
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
