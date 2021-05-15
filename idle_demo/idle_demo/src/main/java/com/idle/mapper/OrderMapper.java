package com.idle.mapper;

import com.idle.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Order> selectPage(@Param("current") int current, @Param("size") int size, @Param("name") String name, @Param("userId") Integer userId);

    Long selectPageCount(@Param("name") String name, @Param("userId") Integer userId);

    Integer insert(@Param("order") Order order);

    Integer updateById(@Param("order") Order order);

    Integer deleteById(@Param("id") Integer id);
}
