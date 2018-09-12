package gyqw.jingcai.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.model.WeChatPayOrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fred
 * 2018/9/12 上午7:24
 */
@RequestMapping("/wechatPay")
@RestController
public class WechatPayController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    private WxPayService wxService;

    @Autowired
    public void setWxService(WxPayService wxService) {
        this.wxService = wxService;
    }

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param weChatPayOrderModel 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.POST)
    public BaseModel unifiedOrder(@RequestBody WeChatPayOrderModel weChatPayOrderModel) throws WxPayException {
        BaseModel baseModel = new BaseModel();

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody(weChatPayOrderModel.getBody());
        orderRequest.setOutTradeNo(weChatPayOrderModel.getOrderNo());
        // 单位：分
        orderRequest.setTotalFee(weChatPayOrderModel.getTotalAmount());
        orderRequest.setOpenid(weChatPayOrderModel.getOpenId());
        orderRequest.setSpbillCreateIp("116.234.81.220");
        orderRequest.setTradeType("JSAPI");
        orderRequest.setNotifyUrl("http://jingcai.xiaobaitu.site/api/wechatPay/receiveNotify");
        baseModel.setResult(this.wxService.unifiedOrder(orderRequest));

        return baseModel;
    }

    @RequestMapping(value = "receiveNotify")
    public String receiveNotify() {
        return "";
    }
}
