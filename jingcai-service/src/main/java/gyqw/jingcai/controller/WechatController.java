package gyqw.jingcai.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping(value = "/service")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("接收到来自微信服务器的认证消息");

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            response.getWriter().println("非法请求");
            return;
        }

        String echoStr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echoStr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(echoStr);
        }
    }
}
