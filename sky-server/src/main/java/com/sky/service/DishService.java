package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * @author LLY
 * @InterfaceName DishService
 * @date 2024/4/13
 */
public interface DishService {

    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

}
