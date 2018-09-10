package gyqw.jingcai.model;

import gyqw.jingcai.domain.Order;
import gyqw.jingcai.domain.OrderItem;
import gyqw.jingcai.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author fred
 * 2018/09/04 17:09
 */
public class OrderModel implements Serializable {
    private User user;
    private Order order;
    private List<OrderItem> orderItemList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "user=" + user +
                ", order=" + order +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
