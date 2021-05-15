package com.idle.mapper;

import com.idle.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    List<User> selectPage(@Param("current") int current,
                          @Param("size") int size,
                          @Param("username") String username,
                          @Param("name") String name,
                          @Param("tel") String tel);

    Long selectPageCount(@Param("username") String username,
                         @Param("name") String name,
                         @Param("tel") String tel);

    User selectById(@Param("id") Integer id);

    Integer insert(@Param("user") User user);

    Integer updateById(@Param("user") User user);

    Integer deleteById(@Param("id") Integer id);

    User selectByUserNameAndPassword(@Param("user") User user);
}
