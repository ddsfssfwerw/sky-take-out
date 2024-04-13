package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LLY
 * @InterfaceName DishFlavorMapper
 * @date 2024/4/13
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);


    /**
     * 根据菜品id删除口味数
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 批量删除菜品口味
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据id查询菜品和关联的口味数据
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long id);
}
