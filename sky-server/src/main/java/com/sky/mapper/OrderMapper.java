package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author LLY
 * @InterfaceName OderMapper
 * @date 2024/4/17
 */
@Mapper
public interface OrderMapper {

    /**
     * 插入订单
     *
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     *
     * @param orders
     */
    void update(Orders orders);

    /**
     * 用于替换微信支付更新数据库状态的问题
     *
     * @param orderStatus
     * @param orderPaidStatus
     */
    @Update("update orders set status = #{orderStatus},pay_status = #{orderPaidStatus} ,checkout_time = #{check_out_time} where id = #{id}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime check_out_time, Long id);

    /**
     * 通过订单id，查询订单详情
     *
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(long id);

    /**
     * 获取当前用户所有订单
     *
     * @return
     */

    Page<OrderVO> getAll(long userId, Long status);

    /**
     * 获取待派送，派送中，待接单订单的数量
     *
     * @return
     */
    List<Orders> get3lei();


    /**
     * 订单搜索
     *
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * * 根据订单状态和下单时间查询订单
     * * @param status
     * * @param orderTime
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrdertimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据动态条件统计营业额
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 根据动态条件统计订单数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 查询指定时间区间内的销量排名top10
     * @param beginTime
     * @param endTime
     * @return
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);
}
