package com.yzhstudy.webapppractice.mapper;

import com.yzhstudy.webapppractice.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username,password) values (#{username},#{password})")
    void addUser(User user);
    @Select("select * from user where username = #{username}")
    // 通过用户名查找用户是否存在
    User getUser(String username);
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username,String password);
    @Delete("delete from user where username = #{username}")
    void deleteUser(String username);
    @Update("update user set password = #{password} where username = #{username}")
    void updateUser(String username,String password);
}
