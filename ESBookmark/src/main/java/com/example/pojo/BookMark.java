package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 书签实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMark {
    private Integer id; //ID
    private String title; //标题
    private Integer userId; //上传用户id
    private String url; //链接
    private String image; //网页图片
    private String description; //描述信息
    private Integer typeId;  //书签类型Id
    private Short status;  //状态 0私有 1共享
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
