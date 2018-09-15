package gyqw.jingcai.service;

import gyqw.jingcai.domain.Order;
import gyqw.jingcai.filter.OrderFilter;
import gyqw.jingcai.model.OrderModel;

import java.util.List;

/**
 * 订单接口
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderModel 订单模型
     * @return 成功/失败
     */
    String createOrder(OrderModel orderModel);

    /**
     * 更新订单信息
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 根据过滤器获取订单列表
     *
     * @param orderFilter 订单过滤器
     * @return 订单列表
     */
    List<OrderModel> list(OrderFilter orderFilter);

    /**
     * 根据过滤器获得订单数量
     *
     * @param orderFilter 订单过滤器
     * @return 订单列表
     */
    int listCount(OrderFilter orderFilter);

    /**
     * 根据订单号获取订单信息
     *
     * @param orderNo 订单号
     * @return 订单信息
     */
    Order findOrderByOrderNo(String orderNo);
}
