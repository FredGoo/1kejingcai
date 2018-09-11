package gyqw.jingcai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fred
 * 2018/9/12 上午7:29
 */
@ConfigurationProperties(prefix = "wechat.pay")
public class WxPayProperties {
    /**
     * 设置微信公众号的appId
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getKeyPath() {
        return this.keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    @Override
    public String toString() {
        return "WxPayProperties{" +
                "appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", mchKey='" + mchKey + '\'' +
                ", keyPath='" + keyPath + '\'' +
                '}';
    }
}
