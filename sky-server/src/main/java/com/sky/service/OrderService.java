package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * @author LLY
 * @InterfaceName OrderService
 * @date 2024/4/17
 */
public interface OrderService {
    /**
     * 下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    OrderVO getOrderDetail(long id);

    /**
     * 查询历史订单
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult getHistoryOrders(int page, int pageSize, Long status);

    /**
     * 取消订单
     * @param id
     */
    void cancel(int id) throws Exception;

    /**
     * agein
     * @param id
     */
    void agein(int id);

    /**
     * 各订单数量统计各订单数量统计
     * @return
     */
    OrderStatisticsVO getOrderStatistics();

    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 接单
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     *
     * @param ordersRejectionDTO
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     *
     * @param ordersCancelDTO
     */
    void adminCancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单
     *
     * @param id
     */
    void delivery(Long id);
    /**
     * 完成订单
     *
     * @param id
     */
    void complete(Long id);

    /**
     * 用户催单
     * @param id
     */
    void reminder(Long id);
}
