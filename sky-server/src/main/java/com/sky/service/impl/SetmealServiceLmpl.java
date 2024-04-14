package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.autoFill;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LLY
 * @className SetmealServiceLmpl
 * @date 2024/4/14
 */
@Service
public class SetmealServiceLmpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增套餐
     * @param setmealDTO
     */
    @autoFill(OperationType.INSERT)
    @Override
    public void saveWithFlavor(SetmealDTO setmealDTO) {

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //向菜品表中插入1条数据
        setmealMapper.insert(setmeal);
        long setmealid = setmeal.getId();//插入后获取套餐id
        List<SetmealDish> setmealDishes= setmealDTO.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0) {
            //设置套餐id
            setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmealid));
            //批量插入
            setmealDishMapper.insertBatch(setmealDishes);
            //setmealDishes.forEach(setmealDish -> setmealDishMapper.insert(setmealDish));
        }
    }

    /**
     * 套餐批量删除
     * @param ids
     */
    //TODO 完善
    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id->{
            Setmeal setmeal = setmealMapper.getById(id);
            if (setmeal.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        setmealMapper.deleteByIds(ids);
        setmealDishMapper.deleteByIds(ids);

    }

    /**
     * 根据id查询套餐
     * @param setmealid
     * @return
     */
    @Override
    public SetmealDish getById(long setmealid) {
        return null;
    }

    /**
     * 套餐起售、停售
     * @param status
     * @param id
     */
    @Override
    public void startorstop(Integer status, long id) {
        Setmeal setmeal = new Setmeal();
        setmeal.setId(id);
        setmeal.setStatus(status);
        setmealMapper.update(setmeal);


    }
}
