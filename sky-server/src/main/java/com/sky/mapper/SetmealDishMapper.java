package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LLY
 * @interface  SetmealDishMapper
 * @date 2024/4/13
 */
@Mapper
public interface SetmealDishMapper
{

    /**
     * 根据菜品id查询关联的套餐id
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

}
