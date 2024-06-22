package com.example.mapper;

import com.example.pojo.Administrator;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 管理员用户管理
 */
@Mapper
public interface AdminMapper {
    /**
     * 管理员信息查询
     * @return
     */
    public List<Administrator> list(String name, Short sex, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);
    /**
     * 新增管理员
     */
    @Insert("insert into administrator(username, name, sex,phone, avatar, id_number,status ,create_time, update_time) " +
            " values(#{username},#{name},#{sex},#{phone},#{avatar},#{idNumber},status,#{createTime},#{updateTime})")
    void insert(Administrator administrator);

    /**
     * 根据ID查询管理员
     * @param id
     * @return
     */
    @Select("select * from administrator where  id = #{id}")
    User getById(Integer id);

    /**
     * 更新管理员
     */
    void update(Administrator administrator);

    /**
     * 根据用户名和密码查询管理员
     */
    @Select("select * from administrator where username = #{username} and password = #{password}")
    Administrator getByUsernameAndPassword(Administrator administrator);
}
