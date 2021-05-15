package com.idle.mapper;

import com.idle.entity.Shopping;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ShoppingMapper {
    
    List<Shopping> selectPage(@Param("current") int current,
                              @Param("size") int size,
                              @Param("type") Integer type,
                              @Param("userId") Integer userId);

    Long selectPageCount(@Param("type") Integer type, @Param("userId") Integer userId);

    Shopping selectById(@Param("id") Integer id);

    Integer insert(@Param("shopping") Shopping shopping);

    Integer updateById(@Param("shopping") Shopping shopping);

    Integer deleteById(@Param("id") Integer id);
}
