package com.example.mapper;

import com.example.pojo.BookMark;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 书签管理
 */
@Mapper
public interface BookmarkMapper {
    /**
     * 书签信息查询
     * @return
     */
    public List<BookMark> list(Integer userId,String title, Short status, Short typeId, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增书签
     */
    @Insert("insert into bookmark (user_id,title,type_id, image,url, status,description, create_time, update_time) " +
            " values(#{userId},#{title},#{typeId},#{image},#{url},#{status},#{description},#{createTime},#{updateTime})")
    void insert(BookMark bookMark);

    /**
     * 根据ID查询书签
     * @param id
     * @return
     */
    @Select("select * from bookmark where  id = #{id}")
    BookMark getById(Integer id);

    /**
     * 更新书签
     */
    void update(BookMark bookMark);

    /**
     *根据书签类型查询对应书签并返回
     */
    @Select("select * from bookmark where type_id=#{id}")
    List<BookMark> getListById(Integer id);
}
