package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.Lint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LLY
 * @className ShoppingCartController
 * @date 2024/4/17
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端-购物车相关接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;


    /**
     * 加入购物车
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加到购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加到购物车:{}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();

    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> list(){

        log.info("查看购物车……");
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);

    }

    /**
     * 删除一个商品
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("删除一个商品")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除一个商品:{}",shoppingCartDTO);
        shoppingCartService.sub(shoppingCartDTO);

        return Result.success();
    }

    /**
     * 清空
     * @param
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result deleteAll(){
        log.info("清空购物车");
        shoppingCartService.deletaAll();
        return Result.success();
    }

}
