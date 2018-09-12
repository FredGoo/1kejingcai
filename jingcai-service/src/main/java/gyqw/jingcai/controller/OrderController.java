package gyqw.jingcai.controller;

import gyqw.jingcai.domain.Order;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.filter.OrderFilter;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.model.OrderModel;
import gyqw.jingcai.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author fred
 * 2018/09/04 11:54
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseModel createOrder(@RequestBody OrderModel orderModel, HttpSession httpSession) {
        BaseModel baseModel = new BaseModel();

        String userId = (String) httpSession.getAttribute("userId");
        if (!StringUtils.isEmpty(userId)) {
            User user = orderModel.getUser();
            user.setnId(Integer.valueOf(userId));
            orderModel.setUser(user);
            baseModel.setResult(this.orderService.createOrder(orderModel));
            return baseModel;
        } else {
            baseModel.setErrorCode("400");
            return baseModel;
        }
    }

    @RequestMapping(value = "/list")
    public BaseModel list(@RequestBody OrderFilter orderFilter) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.orderService.list(orderFilter));
        return baseModel;
    }

    @RequestMapping(value = "/listCount")
    public BaseModel listCount(@RequestBody OrderFilter orderFilter) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.orderService.listCount(orderFilter));
        return baseModel;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseModel update(@RequestBody Order order) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.orderService.update(order));
        return baseModel;
    }

    @RequestMapping(value = "/userOrder", method = RequestMethod.GET)
    public BaseModel userOrder(HttpSession httpSession) {
        BaseModel baseModel = new BaseModel();

        OrderFilter orderFilter = new OrderFilter();
        orderFilter.setPage(1);
        orderFilter.setRow(999);
        orderFilter.setUserId(Integer.valueOf(httpSession.getAttribute("userId").toString()));

        baseModel.setResult(this.orderService.list(orderFilter));
        return baseModel;
    }
}
