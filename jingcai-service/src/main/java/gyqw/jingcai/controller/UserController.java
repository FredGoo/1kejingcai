package gyqw.jingcai.controller;

import gyqw.jingcai.model.BaseModel;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${wechat.mall.redirect.url}")
    private String url;
    private WxMpService wxMpService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @RequestMapping("/info")
    public BaseModel info(HttpSession httpSession) {
        BaseModel baseModel = new BaseModel();

        // 检查登陆状态
        Object userId = httpSession.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            baseModel.setErrorCode("401");
            baseModel.setErrorMessage(this.wxMpService.oauth2buildAuthorizationUrl(this.url, WxConsts.OAuth2Scope.SNSAPI_BASE, null));
        } else {
            baseModel.setResult(httpSession.getAttribute("userId"));
        }
        return baseModel;
    }
}
