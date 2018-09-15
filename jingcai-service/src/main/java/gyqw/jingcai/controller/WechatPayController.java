package gyqw.jingcai.controller;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import gyqw.jingcai.config.WxPayProperties;
import gyqw.jingcai.domain.Order;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.OrderService;
import gyqw.jingcai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fred
 * 2018/9/12 上午7:24
 */
@RequestMapping("/wechatPay")
@RestController
public class WechatPayController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    private WxPayService wxService;
    private OrderService orderService;
    private UserService userService;
    private WxPayProperties properties;

    @Autowired
    public void setProperties(WxPayProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setWxService(WxPayService wxService) {
        this.wxService = wxService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     * 注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     *
     * @param reqMap 下单信息
     */
    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.POST)
    public BaseModel unifiedOrder(HttpSession httpSession, @RequestBody Map<String, String> reqMap) {
        BaseModel baseModel = new BaseModel();

        try {
            // 获取用户信息
            String userId = (String) httpSession.getAttribute("userId");
            User user = this.userService.findUserById(Integer.valueOf(userId));
            if (user == null) {
                logger.info("unifiedOrder user is null");
                baseModel.setStatus(false);
                return baseModel;
            }

            // 获取订单信息
            Order order = this.orderService.findOrderByOrderNo(reqMap.get("orderNo"));
            if (order == null) {
                logger.info("unifiedOrder order is null");
                baseModel.setStatus(false);
                return baseModel;
            }

            // 生成微信支付订单
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody("焱食堂-餐费");
            orderRequest.setOutTradeNo(order.getcOrderNo());
            // 单位：分
            orderRequest.setTotalFee(order.getnTotalAmount());
            orderRequest.setOpenid(user.getcOpenId());
            orderRequest.setSpbillCreateIp(reqMap.get("ip"));
            orderRequest.setTradeType("JSAPI");
            orderRequest.setNotifyUrl("http://jingcai.xiaobaitu.site/api/wechatPay/receiveNotify");
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = this.wxService.unifiedOrder(orderRequest);

            // 计算js sign
            Date now = new Date();
            String timeStamp = ("" + now.getTime()).substring(0, 10);
            String paySign = "appId=" + this.properties.getAppId() + "&nonceStr=" + wxPayUnifiedOrderResult.getNonceStr()
                    + "&package=prepay_id=" + wxPayUnifiedOrderResult.getPrepayId() + "&signType=MD5"
                    + "&timeStamp=" + timeStamp + "&key=" + this.properties.getMchKey();
            String sign = getMD5Str(paySign);

            // 封装返回
            Map<String, Object> map = new HashMap<>();
            map.put("appId", this.properties.getAppId());
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", wxPayUnifiedOrderResult.getNonceStr());
            map.put("package", "prepay_id=" + wxPayUnifiedOrderResult.getPrepayId());
            map.put("signType", "MD5");
            map.put("paySign", sign);
            baseModel.setResult(map);
        } catch (Exception e) {
            logger.error("unifiedOrder error", e);
            baseModel.setStatus(false);
            baseModel.setResult(e.getMessage());
        }

        return baseModel;
    }

    @RequestMapping(value = "/receiveNotify")
    public String receiveNotify(@RequestBody Map map) {
        logger.info(map.toString());
        return "";
    }

    private String getMD5Str(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            logger.error("MD5加密出现错误", e);
            return "";
        }
    }
}
