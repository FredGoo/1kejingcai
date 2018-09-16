package gyqw.jingcai.controller;

import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.Config;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.ConfigService;
import gyqw.jingcai.service.UserService;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping("/wechat")
@RestController
public class WechatController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Value("${wechat.mall.home.url}#/")
    private String url;

    private WxMpService wxMpService;
    private UserService userService;
    private ConfigService configService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping("/redirect")
    public void redirect(@RequestParam("code") String code, HttpServletResponse response,
                         @RequestParam("state") String state,
                         HttpSession httpSession) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = this.wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();

            // 查找用户
            User user = this.userService.findUserByOpenId(openId);
            if (user == null) {
                // 新用户，创建用户
                user = new User();
                user.setcOpenId(openId);
                user.setdCreate(new Date());
                this.userService.createUser(user);
                user = this.userService.findUserByOpenId(openId);
            }

            // 用户自动登录
            httpSession.setAttribute("userId", user.getnId());
            response.sendRedirect(this.url + state);
        } catch (Exception e) {
            logger.error("redirect error", e);
        }
    }

    @RequestMapping("/hackIn")
    public String login(@RequestParam("hackLogin") int userId, HttpSession httpSession) {
        if (!org.springframework.util.StringUtils.isEmpty(userId)) {
            httpSession.setAttribute("userId", userId);
        }
        return "hackIn";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("userId", null);
        return "logout";
    }

    @RequestMapping("/service")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            return;
        }
    }

    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public BaseModel createMenu() {
        BaseModel baseModel = new BaseModel();
        try {
            List<Config> configList = this.configService.getConfigByCategory("wechat.menu");
            baseModel.setResult(this.wxMpService.getMenuService().menuCreate(configList.get(0).getcVal()));
        } catch (Exception e) {
            logger.error("createMenu error", e);
            baseModel.setResult(e.getMessage());
        }
        return baseModel;
    }
}
