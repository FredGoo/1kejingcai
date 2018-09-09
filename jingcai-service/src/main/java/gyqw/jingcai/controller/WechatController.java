package gyqw.jingcai.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/wechat")
@RestController
public class WechatController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    private WxMpService wxMpService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginTest")
    public String loginTest() {
        return "login test";
    }

    @RequestMapping(value = "/checkSignature")
    public String checkSignature(@RequestParam(name = "signature", required = false) String signature,
                                 @RequestParam(name = "timestamp", required = false) String timestamp,
                                 @RequestParam(name = "nonce", required = false) String nonce,
                                 @RequestParam(name = "echostr", required = false) String echoStr) {
        logger.info("接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echoStr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echoStr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echoStr;
        }

        return "非法请求";
    }
}
