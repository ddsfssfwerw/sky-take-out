package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.*;
import com.sky.entity.Dish;
import com.sky.entity.Employee;
import com.sky.entity.SetmealDish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.service.SetmealService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LLY
 * @className SetmealController
 * @date 2024/4/14
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id查询套餐
     * @param setmealid
     * @return
     */
    //TODO 完善
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询套餐")
    public Result<SetmealDish> getById(@PathVariable long setmealid) {
        log.info("根据id查询套餐：{}", setmealid);
        SetmealDish setmealDish = setmealService.getById(setmealid);
        return Result.success(setmealDish);
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO  setmealPageQueryDTO){
        log.info("菜品分页查询:{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增菜品：{}",setmealDTO);
        setmealService.saveWithFlavor(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("套餐批量删除")
    public Result  delete(@RequestParam List<Long> ids){
        log.info("套餐批量删除：{}",ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 套餐起售、停售
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售、停售")
    public Result startorstop(@PathVariable Integer status,long id){
        log.info("套餐起售、停售:{},id:{}",status,id);
        setmealService.startorstop(status,id);
        return Result.success();

    }


}
