package gyqw.jingcai.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {
    private Logger logger = LoggerFactory.getLogger(WechatConfig.class);

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Value("${wechat.token}")
    private String token;

    @Bean
    public WxMpService wxMpService() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        // 设置微信公众号的appId
        config.setAppId(this.appId);
        // 设置微信公众号的app corpSecret
        config.setSecret(this.appSecret);
        // 设置微信公众号的token
        config.setToken(this.token);
        // 设置微信公众号的EncodingAESKey
        config.setAesKey("...");

        // 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        return wxService;
    }
}
