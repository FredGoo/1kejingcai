package gyqw.jingcai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/wechatLogin")
    public String wechatLogin() {
        return "index";
    }

    @RequestMapping("/wechatLoginTest")
    public String wechatLoginTest() {
        return "wechatLogin";
    }
}
