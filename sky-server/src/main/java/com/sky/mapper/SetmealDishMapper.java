package com.sky.mapper;

import com.sky.annotation.autoFill;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
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

    /**
     * 新增套餐和菜品关系
     * @param setmealDishes
     */
    //@autoFill(OperationType.INSERT)
    void insertBatch(List<SetmealDish> setmealDishes);
}
