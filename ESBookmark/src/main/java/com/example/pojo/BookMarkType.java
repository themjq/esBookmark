package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 书签类型实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkType {
    private Integer id; //ID
    private String type; //书签类型
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
