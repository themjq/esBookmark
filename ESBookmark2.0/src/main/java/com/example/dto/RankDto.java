package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankDto {
    private Integer id; //ID
    private String title; //标题
    private String username; //上传用户名
    private String url; //链接
    private String image; //网页图片
    private String type;  //书签类型
}
