package com.idle.mapper;

import com.idle.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    List<Product> selectPage(@Param("current") int current,
                             @Param("size") int size,
                             @Param("userId") Integer userId,
                             @Param("name") String name,
                             @Param("highPrice") String highPrice,
                             @Param("minPrice") String minPrice,
                             @Param("number") Integer number,
                             @Param("label") String label,
                             @Param("status") String status);

    Long selectPageCount(@Param("userId") Integer userId,
                         @Param("name") String name,
                         @Param("highPrice") String highPrice,
                         @Param("minPrice") String minPrice,
                         @Param("number") Integer number,
                         @Param("label") String label,
                         @Param("status") String status);

    Product selectById(@Param("id") Integer id);

    Integer insert(@Param("product") Product product);

    Integer updateById(@Param("product") Product product);

    Integer deleteById(@Param("id") Integer id);
}
