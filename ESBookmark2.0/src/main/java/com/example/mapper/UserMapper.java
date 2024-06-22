package com.example.mapper;

import com.example.pojo.Administrator;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户管理
 */
@Mapper
public interface UserMapper {
    /**
     * 用户信息查询
     * @return
     */
    public List<User> list(String name, Short sex, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into user(username,password, name,phone, sex, avatar, status, id_number, create_time, update_time) " +
            " values(#{username},#{password},#{name},#{phone},#{sex},#{avatar},#{status},#{idNumber},#{createTime},#{updateTime})")
    void insert(User user);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Select("select * from user where  id = #{id} limit 1")
    User getById(Integer id);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 根据用户名和密码查询用户
     * @param user
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    @Select("select * from user where username=#{username}")
    User getByUserName(User user);
}
