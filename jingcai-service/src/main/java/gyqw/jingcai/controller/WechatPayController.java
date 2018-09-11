package gyqw.jingcai.controller;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import gyqw.jingcai.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fred
 * 2018/9/12 上午7:24
 */
@RequestMapping("/wechatPay")
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
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.POST)
    public BaseModel unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.wxService.unifiedOrder(request));
        return baseModel;
    }
}
