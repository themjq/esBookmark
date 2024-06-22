package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 书签传输类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDto {
    private String id; //ID
    private String title; //标题
    private String username; //上传用户名
    private String url; //链接
    private String image; //网页图片
    private String description; //描述信息
    private String type;  //书签类型
    private Short status;  //状态 0私有 1共享
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
