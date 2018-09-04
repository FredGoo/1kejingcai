package gyqw.jingcai.service;

import gyqw.jingcai.model.OrderModel;

/**
 * 订单接口
 */
public interface OrderService {

    boolean createOrder(OrderModel orderModel);
}
