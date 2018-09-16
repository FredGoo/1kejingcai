package gyqw.jingcai.controller;

import gyqw.jingcai.domain.User;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.UserService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${wechat.mall.redirect.url}")
    private String url;
    private WxMpService wxMpService;
    private UserService userService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/info")
    public BaseModel info(@RequestParam(value = "state", required = false) String state, HttpSession httpSession) {
        BaseModel baseModel = new BaseModel();

        // 检查登陆状态
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            baseModel.setErrorCode("401");
            baseModel.setErrorMessage(this.wxMpService.oauth2buildAuthorizationUrl(this.url, WxConsts.OAuth2Scope.SNSAPI_BASE, state));
        } else {
            User user = this.userService.findUserById(userId);
            user.setcOpenId("");
            user.setdCreate(null);
            baseModel.setResult(user);
        }

        return baseModel;
    }
}
