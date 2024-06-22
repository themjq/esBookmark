package com.example.mapper;

import com.example.pojo.BookMarkType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface BookmarkTypeMapper {
    /**
     * 根据id查询
     */
    @Select("select * from bm_type where id=#{id}")
    BookMarkType getById(Integer id);
    /**
     * 查询全部类型
     * @return
     */
    @Select("select * from bm_type")
    List<BookMarkType> list();

    /**
     * 根据ID删除书签类型
     * @param id
     */
    @Delete("delete from bm_type where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增书签类型
     * @param bookMarkType
     */
    @Insert("insert into bm_type(type, create_time, update_time) values(#{type},#{createTime},#{updateTime})")
    void insert(BookMarkType bookMarkType);

    /**
     * 更新书签类型
     */
    @Update("update bm_type set type=#{type} where id=#{id}")
    void update(BookMarkType bookMarkType);
}
