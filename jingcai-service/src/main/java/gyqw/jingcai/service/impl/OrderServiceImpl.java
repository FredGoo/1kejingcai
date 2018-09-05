package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.OrderItemsMapper;
import gyqw.jingcai.dao.OrdersMapper;
import gyqw.jingcai.domain.Order;
import gyqw.jingcai.domain.OrderItem;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.model.OrderModel;
import gyqw.jingcai.model.OrderStatusEnum;
import gyqw.jingcai.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author fred
 * @date 2018/09/04 15:25
 */
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrdersMapper ordersMapper;
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    public void setOrdersMapper(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Autowired
    public void setOrderItemsMapper(OrderItemsMapper orderItemsMapper) {
        this.orderItemsMapper = orderItemsMapper;
    }

    private String genOrderNo() {
        Date curDate = new Date();
        String dateStr4yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(curDate);
        String dateStr4SSS = new SimpleDateFormat("SSS").format(curDate);
        int random = (int) ((Math.random() * 9 + 1) * 1000);
        return dateStr4yyyyMMddHHmmss + random + dateStr4SSS;
    }

    @Override
    public boolean createOrder(OrderModel orderModel) {
        try {
            Date now = new Date();
            String orderNo = genOrderNo();

            Order order = orderModel.getOrder();
            List<OrderItem> orderItemList = orderModel.getOrderItemList();
            User user = orderModel.getUser();

            // 添加订单
            order.setcOrderNo(orderNo);
            order.setnStatus(OrderStatusEnum.SAVED.ordinal());
            order.setnUserId(user.getnId());
            order.setcCustName(user.getcName());
            order.setcMobile(user.getcMobile());
            order.settCustAddress(user.getcAddress());
            order.setdCreate(now);
            int orderRes = this.ordersMapper.insertSelective(order);

            // 添加商品
            Iterator<OrderItem> itemIterator = orderItemList.iterator();
            while (itemIterator.hasNext()) {
                OrderItem orderItem = itemIterator.next();

                orderItem.setcOrderNo(orderNo);
                orderItem.setdCreate(now);
            }
            int orderItemRes = this.orderItemsMapper.insertList(orderItemList);

            return orderRes > 0 && orderItemRes > 0;
        } catch (Exception e) {
            logger.error("createOrder error", e);
            return false;
        }
    }
}
