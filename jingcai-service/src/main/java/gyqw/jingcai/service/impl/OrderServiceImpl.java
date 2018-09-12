package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.OrderItemsMapper;
import gyqw.jingcai.dao.OrdersMapper;
import gyqw.jingcai.domain.Order;
import gyqw.jingcai.domain.OrderItem;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.filter.OrderFilter;
import gyqw.jingcai.model.OrderModel;
import gyqw.jingcai.model.OrderStatusEnum;
import gyqw.jingcai.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fred
 * 2018/09/04 15:25
 */
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrdersMapper ordersMapper;
    private OrderItemsMapper orderItemsMapper;

    @Resource
    public void setOrdersMapper(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Resource
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

    @Override
    public int update(Order order) {
        try {
            order.setdUpdate(new Date());
            return this.ordersMapper.updateByPrimaryKeySelective(order);
        } catch (Exception e) {
            logger.error("update error", e);
            return 0;
        }
    }

    @Override
    public List<OrderModel> list(OrderFilter orderFilter) {
        List<OrderModel> orderModelList = new ArrayList<>();

        try {
            // 获得主订单
            Condition orderCondition = new Condition(Order.class);
            Example.Criteria criteria = orderCondition.createCriteria();
            getCriteriaByFilter(criteria, orderFilter);
            orderCondition.setOrderByClause("N_ID desc limit " + ((orderFilter.getPage() - 1) * orderFilter.getRow())
                    + ", " + orderFilter.getRow());
            List<Order> orderList = this.ordersMapper.selectByCondition(orderCondition);
            List<String> orderNoList = new ArrayList<>();
            for (Order order : orderList) {
                orderNoList.add(order.getcOrderNo());
            }

            // 获得订单详情
            Condition orderItemCondition = new Condition(OrderItem.class);
            orderCondition.createCriteria().andIn("cOrderNo", orderNoList);
            List<OrderItem> orderItemList = this.orderItemsMapper.selectByCondition(orderItemCondition);
            Map<String, List<OrderItem>> orderItemListMap = new HashMap<>();
            for (OrderItem orderItem : orderItemList) {
                if (orderItemListMap.get(orderItem.getcOrderNo()) == null) {
                    List<OrderItem> orderItemList1 = new ArrayList<>();
                    orderItemList1.add(orderItem);
                    orderItemListMap.put(orderItem.getcOrderNo(), orderItemList1);
                } else {
                    orderItemListMap.get(orderItem.getcOrderNo()).add(orderItem);
                }
            }

            // 封装orderModel
            for (Order order : orderList) {
                OrderModel orderModel = new OrderModel();
                orderModel.setOrder(order);
                orderModel.setOrderItemList(orderItemListMap.get(order.getcOrderNo()));
                orderModelList.add(orderModel);
            }

            return orderModelList;
        } catch (Exception e) {
            logger.error("list error", e);
            return orderModelList;
        }
    }

    @Override
    public int listCount(OrderFilter orderFilter) {
        try {
            Condition condition = new Condition(Order.class);
            Example.Criteria criteria = condition.createCriteria();
            getCriteriaByFilter(criteria, orderFilter);
            return this.ordersMapper.selectCountByCondition(condition);
        } catch (Exception e) {
            logger.error("listCount error", e);
            return 0;
        }
    }

    private void getCriteriaByFilter(Example.Criteria criteria, OrderFilter orderFilter) {
        // 姓名
        if (!StringUtils.isEmpty(orderFilter.getName())) {
            criteria.andEqualTo("cCustName", orderFilter.getName());
        }
        // 手机号
        if (!StringUtils.isEmpty(orderFilter.getMobile())) {
            criteria.andEqualTo("cMobile", orderFilter.getMobile());
        }
        // 类型
        if (!StringUtils.isEmpty(orderFilter.getType())) {
            criteria.andEqualTo("nType", orderFilter.getType());
        }
        // 状态
        if (!StringUtils.isEmpty(orderFilter.getStatus())) {
            criteria.andEqualTo("nStatus", orderFilter.getStatus());
        }
    }
}
