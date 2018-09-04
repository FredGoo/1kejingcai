package gyqw.jingcai.controller;

import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.model.OrderModel;
import gyqw.jingcai.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fred
 * @date 2018/09/04 11:54
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
    public BaseModel createOrder(@RequestBody OrderModel orderModel) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.orderService.createOrder(orderModel));
        return baseModel;
    }
}
