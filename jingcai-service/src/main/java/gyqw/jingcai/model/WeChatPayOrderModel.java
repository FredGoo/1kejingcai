package gyqw.jingcai.model;

import java.io.Serializable;

/**
 * @author fred
 * 2018/9/13 上午12:08
 */
public class WeChatPayOrderModel implements Serializable {

    private String body;
    private String orderNo;
    private Integer totalAmount;
    private String openId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "WeChatPayOrderModel{" +
                "body='" + body + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", openId='" + openId + '\'' +
                '}';
    }
}
