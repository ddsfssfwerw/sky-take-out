package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LLY
 * @InterfaceName OrderDetailMapper
 * @date 2024/4/17
 */
@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细
     * @param shoppingCartList
     */
    void insertBatch(List<OrderDetail> shoppingCartList);

    /**
     * 根据订单id，查询订单
     * @param id
     * @return
     */
    @Select("select * from order_detail where order_id = #{id}")
    List<OrderDetail> getByOrderId(long id);
}
