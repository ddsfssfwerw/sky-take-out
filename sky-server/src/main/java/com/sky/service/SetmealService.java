package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.SetmealDish;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @author LLY
 * @InterfaceName SetmealService
 * @date 2024/4/14
 */
public interface SetmealService {
    /**
     *套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithFlavor(SetmealDTO setmealDTO);

    /**
     * 套餐批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐
     * @param setmealid
     * @return
     */
    SetmealDish getById(long setmealid);

    /**
     * 套餐起售、停售
     * @param status
     * @param id
     */
    void startorstop(Integer status, long id);
}
